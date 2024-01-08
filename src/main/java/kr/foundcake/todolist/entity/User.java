package kr.foundcake.todolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name ="users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@Column(name = "userid")
	private String userId;

	@JsonIgnore
	@Column(name = "password")
	private String password;
}
