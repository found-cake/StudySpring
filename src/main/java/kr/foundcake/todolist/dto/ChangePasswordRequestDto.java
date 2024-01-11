package kr.foundcake.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 비밀번호 변경 요청에 사용
public class ChangePasswordRequestDto {

	private String beforePassword;

	private String newPassword;

	private String newCheckPassword;
}
