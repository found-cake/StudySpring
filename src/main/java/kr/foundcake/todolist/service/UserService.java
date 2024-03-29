package kr.foundcake.todolist.service;

import kr.foundcake.todolist.dto.ChangePasswordRequestDto;
import kr.foundcake.todolist.dto.SignupRequestDto;
import kr.foundcake.todolist.entity.User;
import kr.foundcake.todolist.exception.ChangePasswordException;
import kr.foundcake.todolist.exception.UserSignupException;
import kr.foundcake.todolist.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {

	// id 정규식
	private final Pattern idPattern = Pattern.compile("^[a-zA-Z0-9\\-_]*$");

	// 비밀번호 정규식
	private final Pattern pwPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+\\\\|\\[\\]{};:'\",.<>/?]).*$");

	private final PasswordEncoder passwordEncoder;

	private final UserRepo userRepo;

	// 회원 가입 처리 로직
	public void signupUser(SignupRequestDto signupRequestDto) throws UserSignupException{
		String username = signupRequestDto.getUsername();
		String password = signupRequestDto.getPassword();
		// 길이 처리
		if(6 > username.length() || username.length() > 20) {
			throw new UserSignupException("잘못된 아이디 길이 입니다.");
		}
		if(8 > password.length() || password.length() > 30) {
			throw new UserSignupException("잘못된 비밀번호 길이 입니다.");
		}
		// 정규식 처리
		if(!idPattern.matcher(username).matches()) {
			throw new UserSignupException("아이디는 영어, 숫자, '-', '_'으로만 이루어져 있어야 합니다.");
		}
		if(!pwPattern.matcher(password).matches()) {
			throw new UserSignupException("비밀번호는 영어 대소문자, 숫자, 특수문자가 하나 이상 포함되어 있어야 합니다.");
		}
		// 비밀번호 확인 
		if(!password.equals(signupRequestDto.getCheckPassword())) {
			throw new UserSignupException("비밀번호가 일치하지 않습니다.");
		}
		// 아이디가 겹치는지 확인
		if(userRepo.existsById(signupRequestDto.getUsername())) {
			throw new UserSignupException("존재하는 아이디 입니다.");
		}
		// 계정 정보 저장
		userRepo.save(User.builder()
				.userId(username)
				.password(passwordEncoder.encode(password))
				.build()
		);
	}

	// 비밀번호변경 처리로직
	public void changePassword(String username, ChangePasswordRequestDto changePasswordDto) throws ChangePasswordException {
		String newPassword = changePasswordDto.getNewPassword();
		// 길이 체크
		if(8 > newPassword.length() || newPassword.length() > 30) {
			throw new UserSignupException("잘못된 새 비밀번호 길이 입니다.");
		}
		// 정규식 처리
		if(!pwPattern.matcher(newPassword).matches()) {
			throw new UserSignupException("비밀번호는 영어 대소문자, 숫자, 특수문자가 하나 이상 포함되어 있어야 합니다.");
		}
		// 비밀번호 확인
		if(!newPassword.equals(changePasswordDto.getNewCheckPassword())) {
			throw new UserSignupException("새로운 비밀번호와 일치하지 않습니다.");
		}
		// 기존 유저 정보 가져오기
		User user = userRepo.findById(username).orElseThrow();
		// 기존 비밀번호 확인
		if(!passwordEncoder.matches(changePasswordDto.getBeforePassword(), user.getPassword())) {
			throw new UserSignupException("비밀번호가 틀렸습니다.");
		}
		// 기존 비밀번호와 같은지 확인
		if(passwordEncoder.matches(changePasswordDto.getNewPassword(), user.getPassword())) {
			throw new UserSignupException("기존 비밀번호와 같습니다.");
		}
		// 계정 정보 저장
		userRepo.save(User.builder()
				.userId(username)
				.password(passwordEncoder.encode(newPassword))
				.build()
		);
	}
}
