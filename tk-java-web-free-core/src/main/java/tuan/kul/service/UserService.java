package tuan.kul.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tuan.kul.converter.UserConverter;
import tuan.kul.dto.RoleDto;
import tuan.kul.dto.UserDto;
import tuan.kul.entity.UserEntity;
import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.repository.UserRepository;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.Pagination;
import tuan.kul.response.role.RoleInfo;
import tuan.kul.response.user.ListUserInfo;
import tuan.kul.response.user.UserInfo;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private RoleService roleService;

	public ObjectInfoResponse<ListUserInfo> findAllUser(String pageNum, String pageSize) {
		Integer page = Integer.valueOf(pageNum);
		Integer size = Integer.valueOf(pageSize);
		Pageable pageable = new PageRequest(page - 1, size);
		Page<UserEntity> pageUser = userRepository.findAll(pageable);
		List<UserInfo> result = pageUser.getContent().stream()
				.map(userEntity -> UserInfo.of(userEntity))
				.collect(Collectors.toList());
		Pagination pagination = new Pagination(page, size, pageUser.getTotalPages(), pageUser.getTotalElements());
		ListUserInfo listUserInfo = new ListUserInfo(result, pagination, result.isEmpty());
		return new ObjectInfoResponse<>(HttpStatusCode._200.getCode(), HttpStatusCode._200.getText(),
				listUserInfo);
	}
	
	public ObjectInfoResponse<UserInfo> findOne(String userName) {
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
	}
}
