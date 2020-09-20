package tuan.kul.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import tuan.kul.common.Constant;
import tuan.kul.converter.UserConverter;
import tuan.kul.dto.RoleDto;
import tuan.kul.dto.UserDto;
import tuan.kul.entity.UserEntity;
import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.repository.UserRepository;
import tuan.kul.request.user.UserRequest;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.Pagination;
import tuan.kul.response.ResultResponse;
import tuan.kul.response.role.RoleInfo;
import tuan.kul.response.user.ListUserInfo;
import tuan.kul.response.user.UserInfo;

@Service
@Transactional
@Slf4j
public class UserService {
	
	private static final Logger log = Logger.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
    private FileService fileService;

	public ObjectInfoResponse<ListUserInfo> findAllUser(String pageNum, String pageSize) {
		try {
			Integer page = Integer.valueOf(pageNum);
			Integer size = Integer.valueOf(pageSize);
			Sort sort = new Sort(Direction.DESC, "online");
			Pageable pageable = new PageRequest(page - 1, size, sort);
			Page<UserEntity> pageUser = userRepository.findAll(pageable);
			List<UserInfo> result = pageUser.getContent().stream()
					.map(userEntity -> UserInfo.of(userEntity))
					.collect(Collectors.toList());
			Pagination pagination = new Pagination(page, size, pageUser.getTotalPages(), pageUser.getTotalElements());
			ListUserInfo listUserInfo = new ListUserInfo(result, pagination, result.isEmpty());
			return new ObjectInfoResponse<>(HttpStatusCode._200.getCode(), HttpStatusCode._200.getText(),
					listUserInfo);
		} catch (Exception e) {
			log.info("Exception find all user----");
			log.info(e.toString(), e);
			return new ObjectInfoResponse<>(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
		}
		
	}
	
	public ObjectInfoResponse<UserInfo> findOne(String userName) {
		try {
			UserDto userDto = userConverter.convertToDto(userRepository.findOne(userName));
			if (StringUtils.isEmpty(userDto)) {
				return new ObjectInfoResponse<>(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_IS_EXIST.getText());
			}
			Set<RoleInfo> nothaveRoles = roleService.findAllRole();
			Set<RoleInfo> haveRoles = new HashSet<>();
			for (RoleDto roleDto : userDto.getRolesOauth()) {
				haveRoles.add(new RoleInfo(roleDto.getRoleId(), roleDto.getDesciption()));
			}
			nothaveRoles.removeAll(haveRoles);
			UserInfo result = UserInfo.of(userDto);
			result.setHaveRoles(haveRoles);
			result.setListRole(nothaveRoles);
			return new ObjectInfoResponse<>(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText(), result);
		} catch (Exception e) {
			log.info("Exception find all user----");
			log.info(e.toString(), e);
			return new ObjectInfoResponse<>(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
		}
	}
	
	public ResultResponse users(UserRequest request) {
		try {
			UserDto userDto = userConverter.convertToDto(userRepository.findOne(request.getUserName()));
			int index;
			String fileImage;
			String image;
			switch (request.getCondition()) {
			case Constant.INSERT:
				if (!StringUtils.isEmpty(userDto)) {
					return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_IS_EXIST.getText());
				}
				index = request.getFile().indexOf(",");
	        	fileImage = request.getFile().substring(index + 1);
				image = fileService.uploadFile(fileImage, request.getImage(), "users");
				userDto = UserDto.insert(request);
				userDto.setImage(image);
				saveUser(userDto);
				for (String roleId : request.getAddRole()) {
					if (countUserRole(roleId, userDto.getUserName()) != 0) {
						continue;
					}
					insertUserRole(roleId, userDto.getUserName());
				}
				return new ResultResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText());
			case Constant.UPDATE:
				if (StringUtils.isEmpty(userDto)) {
					return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_NOT_FOUND.getText());
				}
				for (String roleId : request.getAddRole()) {
					if (countUserRole(roleId, userDto.getUserName()) != 0) {
						continue;
					}
					insertUserRole(roleId, userDto.getUserName());
				}
				for (String roleId : request.getRemoveRole()) {
					if (countUserRole(roleId, userDto.getUserName()) == 0) {
						continue;
					}
					deleteUserRole(roleId, userDto.getUserName());
				}
				if (!StringUtils.isEmpty(request.getImage())) {
					index = request.getFile().indexOf(",");
		        	fileImage = request.getFile().substring(index + 1);
					image = fileService.uploadFile(fileImage, request.getImage(), "users");
					userDto.setImage(image);
				}
				UserDto.update(request, userDto);
				saveUser(userDto);
				return new ResultResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText());
			case Constant.DELETE:
				if (StringUtils.isEmpty(userDto)) {
					return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_NOT_FOUND.getText());
				}
				if ("admin".equals(userDto.getUserName())) {
					return new ResultResponse(HttpStatusCode._401.getCode(), ErrorCodeEnum.ERROR_REMOVE_ADMIN.getText());
				}
				for (RoleDto roleId : userDto.getRolesOauth()) {
					if (countUserRole(roleId.getRoleId(), userDto.getUserName()) == 0) {
						continue;
					}
					deleteUserRole(roleId.getRoleId(), userDto.getUserName());
				}
				userDto.setOnline(false);
				saveUser(userDto);
				return new ResultResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText());
			case Constant.UNLOCK:
				if (StringUtils.isEmpty(userDto)) {
					return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_NOT_FOUND.getText());
				}
				userDto.setOnline(true);
				saveUser(userDto);
				return new ResultResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText());
			default:
				return new ResultResponse(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
			}
		} catch (Exception e) {
			log.info("Exception users---" + request.getCondition() + "----");
			log.info(e.toString(), e);
			return new ResultResponse(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
		}
	}
	
	public void insertUserRole(final String roleId, final String userName) {
		userRepository.insertUserRole(roleId, userName);
	}
	
	public int countUserRole(final String roleId, final String userName) {
		return userRepository.countUserRole(roleId, userName);
	}
	
	public void deleteUserRole(final String roleId, final String userName) {
		userRepository.deleteUserRole(roleId, userName);
	}
	
	public void saveUser(final UserDto dto) {
		userRepository.save(userConverter.convertToEntity(dto));
	}
}
