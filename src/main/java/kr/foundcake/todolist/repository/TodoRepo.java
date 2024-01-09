package kr.foundcake.todolist.repository;

import kr.foundcake.todolist.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends JpaRepository<TodoItem, Long> {

	TodoItem findByIdAndUser(Long id, String user);
}
