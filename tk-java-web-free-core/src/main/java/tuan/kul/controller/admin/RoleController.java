package tuan.kul.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RoleController {

	@GetMapping(value ="/admin/role/list")
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("admin/role/list");
        return mav;
    }
}
