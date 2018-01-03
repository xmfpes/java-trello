package codesquad.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import codesquad.annotation.SocialUser;
import codesquad.domain.Member;
import lombok.extern.java.Log;

@Controller
@Log
public class OAuth2HandlingController {
	@GetMapping("/oauth2/{val}")
	public String String(@SocialUser Member member, HttpSession session) {
		session.setAttribute("user", member);
		return "redirect:/";
	}
}
