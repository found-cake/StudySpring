package kr.foundcake.todolist.exception;

// 회원가입 예외 처리
public class UserSignupException extends RuntimeException {

	public UserSignupException(String message) {
		super(message);
	}
}
