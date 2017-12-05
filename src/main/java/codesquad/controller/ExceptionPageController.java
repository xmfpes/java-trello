package codesquad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/exception")
@Controller
public class ExceptionPageController {
	@GetMapping("/403ErrorPage")
	public String unauthorize() {
		return "exception/403ErrorPage";
	}
}
