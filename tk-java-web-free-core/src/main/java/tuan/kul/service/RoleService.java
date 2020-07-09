package tuan.kul.service;

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
import org.springframework.stereotype.Service;

import tuan.kul.common.Constant;
import tuan.kul.converter.RoleConverter;
import tuan.kul.dto.RoleDto;
import tuan.kul.entity.RoleEntity;
import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.repository.RoleRepository;
import tuan.kul.request.role.RoleRequest;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.Pagination;
import tuan.kul.response.ResultResponse;
import tuan.kul.response.role.ListRoleInfo;
import tuan.kul.response.role.RoleInfo;

@Service
@Transactional
public class RoleService {
	
    private static final Logger log = Logger.getLogger(RoleService.class);

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;

	public ObjectInfoResponse<ListRoleInfo> findAll(String pageNum, String pageSize) {
		Integer page = Integer.valueOf(pageNum);
		Integer size = Integer.valueOf(pageSize);
		Pageable pageable = new PageRequest(page - 1, size);
		Page<RoleEntity> pageNew = roleRepository.findAllByPaging(pageable);
		List<RoleInfo> result = pageNew.getContent().stream()
				.map(roleEntity -> new RoleInfo(roleEntity.getRoleId(), roleEntity.getDesciption()))
				.collect(Collectors.toList());
		Pagination pagination = new Pagination(page, size, pageNew.getTotalPages(), pageNew.getTotalElements());
		ListRoleInfo roleInfo = new ListRoleInfo(result, pagination, result.isEmpty());
		return new ObjectInfoResponse<ListRoleInfo>(HttpStatusCode._200.getCode(), HttpStatusCode._200.getText(),
				roleInfo);
	}
	
	public ResultResponse roles(RoleRequest request) {
		try {
			RoleDto roleDto = roleConverter.convertToDto(roleRepository.findOne(request.getRoleId()));
			switch (request.getCondition()) {
			case Constant.INSERT:
				if (roleDto != null) {
					return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_IS_EXIST.getText());
				}
				roleDto = new RoleDto(request.getRoleId(), request.getRoleName());
				roleRepository.save(roleConverter.convertToEntity(roleDto));
				return new ResultResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText());
			case Constant.UPDATE:
				if (roleDto == null) {
					return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_NOT_FOUND.getText());
				}
				roleDto.setDesciption(request.getRoleName());
				roleRepository.save(roleConverter.convertToEntity(roleDto));
				return new ResultResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText());
			case Constant.DELETE:
				if (roleDto == null) {
					return new ResultResponse(HttpStatusCode._500.getCode(), ErrorCodeEnum.ERROR_NOT_FOUND.getText());
				}
				roleRepository.delete(request.getRoleId());
				return new ResultResponse(HttpStatusCode._200.getCode(), ErrorCodeEnum.SUCCESS.getText());
			default:
				return new ResultResponse(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
			}
		} catch (Exception e) {
			log.info("Exception---" + request.getCondition() + "----");
			log.info(e.toString());
			return new ResultResponse(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
		}
	}
	
	public Set<RoleInfo> findAllRole() {
		List<RoleEntity> roleEntities = roleRepository.findAll();
		Set<RoleInfo> result = new HashSet<>();
		for(RoleEntity roleEntity : roleEntities) {
			RoleInfo roleInfo = new RoleInfo(roleEntity.getRoleId(), roleEntity.getDesciption());
			result.add(roleInfo);
		}
		return result;
	}
}
