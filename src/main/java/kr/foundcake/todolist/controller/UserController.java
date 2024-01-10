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

	@PostMapping("/changepw")
	public String changePassword(Principal principal, Model model, @ModelAttribute ChangePasswordRequestDto requestDto) {
		try {
			service.changePassword(principal.getName(), requestDto);
		} catch(UserSignupException e){
			model.addAttribute("message", e.getMessage());
			return "AlertPage";
		}
		return "redirect:/";
	}
}
