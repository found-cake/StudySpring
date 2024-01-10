package kr.foundcake.todolist.controller;

import kr.foundcake.todolist.service.TodolistService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class IndexController {

	private final TodolistService service;

	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return false;
		}
		return authentication.isAuthenticated();
	}

	@GetMapping("/")
	public String index(Principal principal, Model model) {
		if(isAuthenticated()) {
			model.addAttribute("todoList", service.getTodoList(principal.getName()));
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
			model.addAttribute("error", error);
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

	@GetMapping("/setting")
	public String setting() {
		return "setting";
	}
}
