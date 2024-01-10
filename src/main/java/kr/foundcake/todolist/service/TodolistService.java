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

	private final TodoRepo todoRepo;

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

	public List<TodoItem> getTodoList(String username) {
		return todoRepo.findByUser(usernameEncode(username));
	}

	public boolean addTodo(String username, String task) {
		if(task.trim().isEmpty()) return false;
		todoRepo.save(TodoItem.builder()
				.user(usernameEncode(username))
				.task(task)
				.isSuccess(false)
				.build()
		);
		return true;
	}

	public void removeTodo(Long id, String username) {
		todoRepo.removeByIdAndUser(id, usernameEncode(username));
	}

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
