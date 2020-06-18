package tuan.kul.controller.admin.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.role.ListRoleInfo;

@RestController
public class RoleApi {
	
	@GetMapping(value = "/admin/role/find-all")
	public ObjectInfoResponse<ListRoleInfo> findAll(@RequestParam("page_num") String pageNum, @RequestParam("page_size") String pageSize) {
		if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)) {
			return new ObjectInfoResponse<ListRoleInfo>(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
		}
		return null;
	}
}
