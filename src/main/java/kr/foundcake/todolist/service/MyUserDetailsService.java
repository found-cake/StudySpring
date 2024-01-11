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

	// 기본 역할
	private static final String ROLE = "user";

	// 유저 데이터를 저장하는 레포 주입
	private final UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 유저를 찾고 예외 처리
		User user = userRepo.findById(username).orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다."));
		// Spring Security 처리에 필요한 UserDetails 객체를 생성
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUserId())
				.password(user.getPassword())
				.roles(ROLE)
				.build();
	}
}
