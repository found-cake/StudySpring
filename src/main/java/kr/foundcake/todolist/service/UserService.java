package kr.foundcake.todolist.service;

import kr.foundcake.todolist.dto.SignupRequestDto;
import kr.foundcake.todolist.entity.User;
import kr.foundcake.todolist.exception.UserSignupException;
import kr.foundcake.todolist.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {

	private final Pattern idPattern = Pattern.compile("^[a-zA-Z0-9\\-_]*$");

	private final Pattern pwPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+\\\\|\\[\\]{};:'\",.<>/?]).*$");

	private final PasswordEncoder passwordEncoder;

	private final UserRepo userRepo;

	public void signupUser(SignupRequestDto signupRequestDto) throws UserSignupException{
		String username = signupRequestDto.getUsername();
		String password = signupRequestDto.getPassword();
		if(6 > username.length() || username.length() > 20) {
			throw new UserSignupException("잘못된 아이디 길이 입니다.");
		}
		if(8 > password.length() || password.length() > 30) {
			throw new UserSignupException("잘못된 비밀번호 길이 입니다.");
		}
		if(!idPattern.matcher(username).matches()) {
			throw new UserSignupException("아이디는 영어, 숫자, '-', '_'으로만 이루어져 있어야 합니다.");
		}
		if(!pwPattern.matcher(password).matches()) {
			throw new UserSignupException("비밀번호는 영어 대소문자, 숫자, 특수문자가 하나 이상 포함되어 있어야 합니다.");
		}
		if(!password.equals(signupRequestDto.getCheckPassword())) {
			throw new UserSignupException("비밀번호가 다릅니다.");
		}
		if(userRepo.existsById(signupRequestDto.getUsername())) {
			throw new UserSignupException("존재하는 아이디 입니다.");
		}
		userRepo.save(User.builder()
				.userId(username)
				.password(passwordEncoder.encode(password))
				.build()
		);
	}
}
