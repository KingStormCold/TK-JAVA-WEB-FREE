package tuan.kul.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import tuan.kul.response.category.CategoryResponse;
import tuan.kul.service.CategoryService;

@RestController
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping (value = "/admin/category/list")
    public ModelAndView getAllCategory() {
        ModelAndView mav = new ModelAndView("admin/category/list");
        CategoryResponse response = categoryService.findAll();
        mav.addObject("category", response);
        return mav;
    }
}
