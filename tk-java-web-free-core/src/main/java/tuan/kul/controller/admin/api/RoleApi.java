package tuan.kul.controller.admin.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.request.role.RoleRequest;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.ResultResponse;
import tuan.kul.response.role.ListRoleInfo;
import tuan.kul.service.RoleService;

@RestController
@Slf4j
public class RoleApi {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping(value = "/admin/role/find-all")
	public ObjectInfoResponse<ListRoleInfo> findAll(@RequestParam("page_num") String pageNum, @RequestParam("page_size") String pageSize) {
		if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)) {
			return new ObjectInfoResponse<ListRoleInfo>(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
		}
		return roleService.findAll(pageNum, pageSize);
	}
	
	@PostMapping(value = "/admin/role")
	public ResultResponse roles(@RequestBody RoleRequest request) {
		try {
			if (request.validate() == null) {
				return roleService.roles(request);
			}
			return request.validate();
		} catch (Exception e) {
			log.error("Exception--- " + request.getCondition() + "----" ,e.toString(), e);
			return new ResultResponse(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
		}
	}
}
