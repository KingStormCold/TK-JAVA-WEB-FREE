package tuan.kul.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tuan.kul.entity.RoleEntity;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.repository.RoleRepository;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.Pagination;
import tuan.kul.response.role.ListRoleInfo;
import tuan.kul.response.role.RoleInfo;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

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
}
