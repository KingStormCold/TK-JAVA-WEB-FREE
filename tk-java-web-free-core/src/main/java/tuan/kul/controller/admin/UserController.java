package tuan.kul.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @GetMapping(value = "/admin/users/list")
    public ModelAndView getAllUser() {
        ModelAndView mav = new ModelAndView("/admin/user/list");
        return mav;
    }
}
