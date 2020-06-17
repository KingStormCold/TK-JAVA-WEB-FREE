package tuan.kul.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerWeb {
    @GetMapping(value ="/web/home")
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("web/home");
        return mav;
    }
    
    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
    
    @GetMapping(value = "/access-denied")
    public String accessDenied() {
            return "redirect:/login?accessDenied";
    }
}
