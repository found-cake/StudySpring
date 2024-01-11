package kr.foundcake.todolist.repository;

import jakarta.transaction.Transactional;
import kr.foundcake.todolist.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<TodoItem, Long> {

	// id와 user를 만족하는 TodoItem을 찾음
	TodoItem findByIdAndUser(Long id, String user);

	// 특정 user의 todoitem들을 가져옴
	List<TodoItem> findByUser(String user);

	@Transactional
	// id와 user를 만족하는 TodoItem을 제거함
	void removeByIdAndUser(Long id, String user);
}
