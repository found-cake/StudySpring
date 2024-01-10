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

	@PostMapping("/add")
	public String add(Model model, Principal principal, @RequestParam String task) {
		if(service.addTodo(principal.getName(), task)) {
			return "redirect:/";
		}
		model.addAttribute("message", "공백은 추가 할 수 없습니다.");
		return "AlertPage";
	}

	@PostMapping("/delete")
	public String removeTodo(Principal principal, @RequestParam Long id) {
		service.removeTodo(id, principal.getName());
		return "redirect:/";
	}
}
