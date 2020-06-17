package tuan.kul.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewController {
    
    @GetMapping(value ="/admin/news/list")
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("admin/new/list");
        return mav;
    }
    
    @GetMapping(value ="/admin/news")
    public ModelAndView getInfo() {
        ModelAndView mav = new ModelAndView("admin/new/insert_update_new");
        return mav;
    }
}
