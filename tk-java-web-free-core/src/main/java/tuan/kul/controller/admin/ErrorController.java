package tuan.kul.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorController {

	@GetMapping(value = "/error")
	public ModelAndView error500(HttpServletRequest request) {
		Integer httpErrorCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		switch (httpErrorCode) {
		case 404:
			return new ModelAndView("error/404");
		case 500:
			return new ModelAndView("error/500");
		default:
			break;
		}
		ModelAndView mav = new ModelAndView("");
		return mav;
	}
}
