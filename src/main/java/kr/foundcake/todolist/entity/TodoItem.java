package kr.foundcake.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="todos") // todos란 이름으로 table 생성
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoItem {

	@Id //table의 id로 지정
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성
	private Long id;

	@Column(name = "user", nullable = false)
	private String user;

	@Column(name = "task", nullable = false)
	private String task;

	@Column(name = "success", nullable = false)
	private boolean isSuccess;
}
