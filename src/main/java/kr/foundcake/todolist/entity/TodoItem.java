package kr.foundcake.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="todos")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoItem {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user", nullable = false)
	private String user;

	@Column(name = "task", nullable = false)
	private String task;

	@Column(name = "success", nullable = false)
	private boolean isSuccess;
}
