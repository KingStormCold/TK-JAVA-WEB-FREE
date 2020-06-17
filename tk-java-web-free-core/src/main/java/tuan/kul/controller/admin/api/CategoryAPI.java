package tuan.kul.controller.admin.api;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tuan.kul.enums.ErrorCodeEnum;
import tuan.kul.enums.HttpStatusCode;
import tuan.kul.request.category.CreateCategory;
import tuan.kul.response.ResultResponse;
import tuan.kul.service.CategoryService;

@RestController
public class CategoryAPI {
    
    private static final Logger log = Logger.getLogger(CategoryAPI.class);
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/api/admin/category")
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
