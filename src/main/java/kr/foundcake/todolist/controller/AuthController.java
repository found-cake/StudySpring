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

	// /auth/signup 경로로 요청할시 처리 됨. 기본 매핑과 메소드에 있는 매핑 어노테이션을 포함한 경로
	// 회원 가입을 처리하는 메서드
	@PostMapping("/signup")
	public String signup(Model model, @ModelAttribute SignupRequestDto requestDto) {
		try {
			// 회원 가입 서비스를 호출하여 사용자를 등록
			service.signupUser(requestDto);
		} catch(UserSignupException e){
			// 예외가 발생한 경우 메시지를 모델에 추가하고 AlertPage 페이지 렌더
			model.addAttribute("message", e.getMessage());
			return "AlertPage";
		}
		// 회원가입이 완료 되면 로그인 페이지로 이동
		return "redirect:/login";
	}
}
