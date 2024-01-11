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
	
	// 투두리스트를 관리하는 서비스
	private final TodolistService service;

	// 로그인 했는지 확인하기 위한 메소드
	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return false;
		}
		return authentication.isAuthenticated();
	}
	
	//  / 경로로 요청시 처리 됨
	// 메인 페이지
	@GetMapping("/")
	public String index(Principal principal, Model model) {
		if(isAuthenticated()) {
			// 로그인 되어 있은면 투두리스트를 가져오고 todolist를 랜더링함
			model.addAttribute("todoList", service.getTodoList(principal.getName()));
			return "todolist";
		}
		// home 랜더링
		return "home";
	}

	// /login 경로로 요청시 실행됨
	// 로그인 페이지
	@GetMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error) {
		// 이미 로그인 되어 있으면 / 으로 이동
		if(isAuthenticated()) {
			return "redirect:/";
		}
		// error 처리를 위함
		if(error != null && error.equals("error")) {
			model.addAttribute("error", error);
		}
		// login 랜더링
		return "login";
	}

	// /singup 경로로 요청시 실행됨
	// 회원가입 페이지
	@GetMapping("/signup")
	public String signup() {
		// 회원가입 되어 있을경우 / 으로 이동
		if(isAuthenticated()) {
			return "redirect:/";
		}
		return "signup";
	}

	// 비밀번호 변경 페이지
	@GetMapping("/setting")
	public String setting() {
		return "setting";
	}
}
