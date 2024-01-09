package kr.foundcake.todolist.controller;

import kr.foundcake.todolist.dto.AddTodoRequestDto;
import kr.foundcake.todolist.service.TodolistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/user/todo")
public class AddTodoController {

	private final TodolistService service;

	@PostMapping("/add")
	public String add(Principal principal, @ModelAttribute AddTodoRequestDto todoRequestDto) {
		service.addTodo(principal.getName(), todoRequestDto.getTask());
		return "redirect:/";
	}
}
