package kr.foundcake.todolist.controller;

import kr.foundcake.todolist.service.TodolistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/user/todo")
public class SuccessTodoController {

	private final TodolistService service;

	// /user/todo/success/{id}로 요청시 처리됨
	// 뒤에 마지막 경로에 id를 받도록 함
	// todolist의 완료 처리에 사용됨
	@PostMapping("/success/{id}")
	public String updateSuccess(Principal principal, @PathVariable Long id) {
		// 서비스를 호출하고 결과에 따라 전송
		if(service.setSuccess(id, principal.getName())) {
			return "ok";
		}
		return "failure";
	}
}
