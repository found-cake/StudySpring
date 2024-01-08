package kr.foundcake.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequestDto {

	private String username;

	private String password;

	private String checkPassword;
}
