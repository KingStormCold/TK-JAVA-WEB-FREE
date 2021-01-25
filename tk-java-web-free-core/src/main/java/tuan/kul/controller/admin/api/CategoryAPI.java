package tuan.kul.controller.admin.api;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuan.kul.common.Constant;
import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.request.category.CreateCategory;
import tuan.kul.response.ObjectInfoResponse;
import tuan.kul.response.ResultResponse;
import tuan.kul.response.category.CategoryResponse;
import tuan.kul.response.category.ListCategoryInfo;
import tuan.kul.response.role.ListRoleInfo;
import tuan.kul.security.SecurityUtils;
import tuan.kul.service.CategoryService;

@RestController
public class CategoryAPI {
    
    private static final Logger log = Logger.getLogger(CategoryAPI.class);
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping(value = "/api/admin/category/find-all")
	public ObjectInfoResponse<ListCategoryInfo> findAll(@RequestParam("page_num") String pageNum, @RequestParam("page_size") String pageSize) {
		if (!SecurityUtils.incognito()) {
			try {
				if (!SecurityUtils.getAuthorities().contains(Constant.CATEGORY_ALL)
						&& !SecurityUtils.getAuthorities().contains(Constant.ADMIN)) {
					return new ObjectInfoResponse<>(HttpStatusCode._401.getCode(), HttpStatusCode._401.getText());
				}
				if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)) {
					return new ObjectInfoResponse<>(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
				}
				return categoryService.findAll(pageNum, pageSize);
			} catch (Exception e) {
				log.info("Exception--- find all role");
				log.info(e.toString());
				return new ObjectInfoResponse<>(HttpStatusCode._500.getCode(), HttpStatusCode._500.getText());
			}
		}
		return new ObjectInfoResponse<>(HttpStatusCode._403.getCode(), HttpStatusCode._403.getText());
	}

    @PostMapping(value = "/api/admin/category", consumes = "application/json")
    public ResultResponse createCategory(@RequestBody CreateCategory request) {
        if (request.validate() == null) {
            String userDetails = SecurityContextHolder.getContext().getAuthentication().getName();
            log.info(userDetails + " create category " + request.getCategoryName() + " " + new Date(System.currentTimeMillis()).toString());
            return categoryService.insertCategory(request, userDetails);
        }
        return request.validate();
    }
    
    @DeleteMapping(value = "/api/admin/category/{code}")
    public ResultResponse deleteCategory(@PathVariable("code") String code) {
        if (!StringUtils.isEmpty(code)) {
            return categoryService.deleteCategory(code);
        }
        return new ResultResponse(HttpStatusCode._400.getCode(), ErrorCodeEnum.ERROR_INPUT_EMPTY.getText());
    }
}
