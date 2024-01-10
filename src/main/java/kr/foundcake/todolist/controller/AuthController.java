package kr.foundcake.todolist.controller;

import kr.foundcake.todolist.dto.SignupRequestDto;
import kr.foundcake.todolist.exception.UserSignupException;
import kr.foundcake.todolist.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

	// 회원 가입에 필요한 서비스
	private final UserService service;

	@PostMapping("/signup")
	public String signup(Model model, @ModelAttribute SignupRequestDto requestDto) {
		try {
			service.signupUser(requestDto);
		} catch(UserSignupException e){
			model.addAttribute("message", e.getMessage());
			return "AlertPage";
		}
		return "redirect:/login";
	}
}
