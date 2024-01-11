package kr.foundcake.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 회원가입 요청에 사용
public class SignupRequestDto {

	private String username;

	private String password;

	private String checkPassword;
}
