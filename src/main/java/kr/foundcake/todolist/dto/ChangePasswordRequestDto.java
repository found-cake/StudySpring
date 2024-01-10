package kr.foundcake.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordRequestDto {

	private String beforePassword;

	private String newPassword;

	private String newCheckPassword;
}
