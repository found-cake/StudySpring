package kr.foundcake.todolist.controller;

import kr.foundcake.todolist.service.TodolistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/user/todo")
public class SuccessTodoController {

	private TodolistService service;

	@PostMapping("/success/{id}")
	public String updateSuccess(Principal principal, @PathVariable Long id) {
		if(service.setSuccess(id, principal.getName())) {
			return "ok";
		}
		return "failure";
	}
}
