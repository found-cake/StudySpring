package kr.foundcake.todolist.exception;

// 비밀번호 변경 예외 처리
public class ChangePasswordException extends RuntimeException {

	public ChangePasswordException(String message) {
		super(message);
	}
}
