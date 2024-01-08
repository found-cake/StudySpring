package kr.foundcake.todolist.repository;

import kr.foundcake.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
}
