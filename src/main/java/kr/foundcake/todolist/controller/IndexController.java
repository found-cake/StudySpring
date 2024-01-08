package kr.foundcake.todolist.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return false;
		}
		return authentication.isAuthenticated();
	}

	@GetMapping("/")
	public String index() {
		if(isAuthenticated()) {
			return "todolist";
		}
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error) {
		if(isAuthenticated()) {
			return "redirect:/";
		}
		if(error != null && error.equals("error")) {
			model.addAttribute(error, error);
		}
		return "login";
	}

	@GetMapping("/signup")
	public String signup() {
		if(isAuthenticated()) {
			return "redirect:/";
		}
		return "signup";
	}
}
