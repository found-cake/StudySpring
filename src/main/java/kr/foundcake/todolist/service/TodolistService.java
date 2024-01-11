package kr.foundcake.todolist.service;

import kr.foundcake.todolist.entity.TodoItem;
import kr.foundcake.todolist.repository.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TodolistService {

	// 투두리스트를 관리하는 레포 주입
	private final TodoRepo todoRepo;

	// 유저 이름을 쉽게 보지 못하도록 하기 위함
	// md5로 암호화 하고 base64 인코딩
	private String usernameEncode(String username) {
		MessageDigest md;
		try{
			md = MessageDigest.getInstance("MD5");
		}catch(NoSuchAlgorithmException e){
			return username;
		}
		md.update(username.getBytes());
		return Base64.getEncoder().encodeToString(md.digest());
	}

	// 특정 유저의 투두리스트를 가져오는 메소드
	public List<TodoItem> getTodoList(String username) {
		return todoRepo.findByUser(usernameEncode(username));
	}

	// 할 일을 추가하는 메소드
	public boolean addTodo(String username, String task) {
		if(task.trim().isEmpty()) return false;
		todoRepo.save(TodoItem.builder() // id는 자동 생성 해주기 때문에 지정할 필요 없음
				.user(usernameEncode(username))
				.task(task)
				.isSuccess(false)
				.build()
		);
		return true;
	}

	// 할일을 지워주는 메소드
	public void removeTodo(Long id, String username) {
		todoRepo.removeByIdAndUser(id, usernameEncode(username));
	}

	// 할일을 완료했다고 저장하는 메소드
	public boolean setSuccess(Long id, String username) {
		TodoItem todo = todoRepo.findByIdAndUser(id, usernameEncode(username));
		if(todo == null || todo.isSuccess()) return false;
		todoRepo.save(TodoItem.builder()
				.id(todo.getId())
				.user(todo.getUser())
				.task(todo.getTask())
				.isSuccess(true)
				.build()
		);
		return true;
	}
}
