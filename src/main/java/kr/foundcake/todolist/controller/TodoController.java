package kr.foundcake.todolist.controller;

import kr.foundcake.todolist.service.TodolistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/user/todo")
public class TodoController {

	private final TodolistService service;

	// todolist에 추가 할 때 요청 됨
	// /user/todo/add로 요청시 처리 됨
	@PostMapping("/add")
	public String add(Model model, Principal principal, @RequestParam String task) {
		// 서비스를 통해 todolist에 추가
		if(service.addTodo(principal.getName(), task)) {
			return "redirect:/";
		}
		// 실패시 AlertPage
		model.addAttribute("message", "공백은 추가 할 수 없습니다.");
		return "AlertPage";
	}

	// todoitem을 삭제할때 요청 됨
	// /user/todo/delete로 요청시 처리 됨
	@PostMapping("/delete")
	public String removeTodo(Principal principal, @RequestParam Long id) {
		// 서비스로 삭제 처리
		service.removeTodo(id, principal.getName());
		return "redirect:/";
	}
}
