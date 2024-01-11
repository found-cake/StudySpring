package kr.foundcake.todolist.controller;

import kr.foundcake.todolist.dto.ChangePasswordRequestDto;
import kr.foundcake.todolist.exception.UserSignupException;
import kr.foundcake.todolist.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService service;

	// 비밀번호 변경 요청
	// /user/changepw로 요청시 처리 됨
	@PostMapping("/changepw")
	public String changePassword(Principal principal, Model model, @ModelAttribute ChangePasswordRequestDto requestDto) {
		try {
			// 서비스로 비밀번호 변경 처리
			service.changePassword(principal.getName(), requestDto);
		} catch(UserSignupException e){
			// 예외 발생시 AlertPage
			model.addAttribute("message", e.getMessage());
			return "AlertPage";
		}
		return "redirect:/";
	}
}
