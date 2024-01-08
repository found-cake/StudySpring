package kr.foundcake.todolist.service;

import kr.foundcake.todolist.entity.User;
import kr.foundcake.todolist.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

	private static final String ROLE = "user";

	private final UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findById(username).orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다."));
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUserId())
				.password(user.getPassword())
				.roles(ROLE)
				.build();
	}
}
