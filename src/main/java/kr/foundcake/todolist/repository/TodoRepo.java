package kr.foundcake.todolist.repository;

import jakarta.transaction.Transactional;
import kr.foundcake.todolist.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<TodoItem, Long> {

	TodoItem findByIdAndUser(Long id, String user);

	List<TodoItem> findByUser(String user);

	@Transactional
	void removeByIdAndUser(Long id, String user);
}
