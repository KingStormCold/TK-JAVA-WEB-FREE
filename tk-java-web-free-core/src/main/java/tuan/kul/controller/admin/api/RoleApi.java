package tuan.kul.controller.admin.api;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuan.kul.common.Constant;
import tuan.kul.common.JsonUtils;
import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.request.role.RoleRequest;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.ResultResponse;
import tuan.kul.response.role.ListRoleInfo;
import tuan.kul.security.SecurityUtils;
import tuan.kul.service.RoleService;

@RestController
public class RoleApi {
	
    private static final Logger log = Logger.getLogger(RoleApi.class);
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping(value = "/api/admin/role/find-all")
	public ObjectInfoResponse<ListRoleInfo> findAll(@RequestParam("page_num") String pageNum, @RequestParam("page_size") String pageSize) {
		if (!SecurityUtils.incognito()) {
			try {
				if (!SecurityUtils.getAuthorities().contains(Constant.CATEGORY_ALL) && !SecurityUtils.getAuthorities().contains(Constant.ADMIN)) {
					return new ObjectInfoResponse<ListRoleInfo>(HttpStatusCode._401.getCode(), HttpStatusCode._401.getText());
				}
				if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)) {
					return new ObjectInfoResponse<ListRoleInfo>(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
				}
				return roleService.findAll(pageNum, pageSize);
			} catch (Exception e) {
				log.info("Exception--- find all role");
				log.info(e.toString());
				return new ObjectInfoResponse<ListRoleInfo>(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
			}
		}
		return new ObjectInfoResponse<ListRoleInfo>(HttpStatusCode._403.getCode(), HttpStatusCode._403.getText());
	}
	
	@PostMapping(value = "/api/admin/role", consumes = "application/json")
	public ResultResponse roles(@RequestBody RoleRequest request) {
		if (!SecurityUtils.incognito()) {
			try {
				if (!SecurityUtils.getAuthorities().contains(Constant.ROLE_ALL)) {
					return new ResultResponse(HttpStatusCode._401.getCode(), HttpStatusCode._401.getText());
				}
				String userName = SecurityContextHolder.getContext().getAuthentication().getName();
				String json = JsonUtils.convertObjectToString(request);
		        log.info(userName + " search ==== " + json);
				if (request.validate() == null) {
					return roleService.roles(request);
				}
				return request.validate();
			} catch (Exception e) {
				log.info("Exception--- " + request.getCondition() + "----");
				log.info(e.toString());
				return new ResultResponse(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
			}
		}
		return new ObjectInfoResponse<ListRoleInfo>(HttpStatusCode._403.getCode(), HttpStatusCode._403.getText());
	}
}
