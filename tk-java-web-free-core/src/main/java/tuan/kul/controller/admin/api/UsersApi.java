package tuan.kul.controller.admin.api;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuan.kul.common.Constant;
import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.user.ListUserInfo;
import tuan.kul.response.user.UserInfo;
import tuan.kul.security.SecurityUtils;
import tuan.kul.service.UserService;

@RestController
public class UsersApi {
	
	private static final Logger log = Logger.getLogger(UsersApi.class);
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/admin/user/find-all")
	public ObjectInfoResponse<ListUserInfo> findAllUser(@RequestParam("page_num") String pageNum, @RequestParam("page_size") String pageSize) {
		if (!SecurityUtils.incognito()) {
			try {
				if (!SecurityUtils.getAuthorities().contains(Constant.USER_ALL) || !SecurityUtils.getAuthorities().contains(Constant.ADMIN)) {
					return new ObjectInfoResponse<ListUserInfo>(HttpStatusCode._401.getCode(), HttpStatusCode._401.getText());
				}
				if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)) {
					return new ObjectInfoResponse<ListUserInfo>(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
				}
				return userService.findAllUser(pageNum, pageSize);
			} catch (Exception e) {
				log.info("Exception--- find all user");
				log.info(e.toString());
				return new ObjectInfoResponse<ListUserInfo>(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
			}
		}
		return new ObjectInfoResponse<ListUserInfo>(HttpStatusCode._403.getCode(), HttpStatusCode._403.getText());
	}
	
	@GetMapping(value = "/admin/user/find-one")
	public ObjectInfoResponse<UserInfo> findOne(@RequestParam("user_name") String userName) {
		if (!SecurityUtils.incognito()) {
			try {
				if (!SecurityUtils.getAuthorities().contains(Constant.USER_ALL) || !SecurityUtils.getAuthorities().contains(Constant.ADMIN)) {
					return new ObjectInfoResponse<UserInfo>(HttpStatusCode._401.getCode(), HttpStatusCode._401.getText());
				}
				if (StringUtils.isEmpty(userName)) {
					return new ObjectInfoResponse<UserInfo>(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
				}
				return userService.findOne(userName);
			} catch (Exception e) {
				log.info("Exception--- find user : " + userName);
				log.info(e.toString());
				return new ObjectInfoResponse<UserInfo>(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
			}
		}
		return new ObjectInfoResponse<UserInfo>(HttpStatusCode._403.getCode(), HttpStatusCode._403.getText());
	}
}
