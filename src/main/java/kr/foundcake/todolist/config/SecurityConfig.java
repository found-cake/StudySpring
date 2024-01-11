package kr.foundcake.todolist.config;

import kr.foundcake.todolist.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// lombok에 의하여 생성자의 파라미터에 추가됨
	// 생성자에 Bean 객체가 있을 경우 자동으로 주입됨
	private final MyUserDetailsService myUserDetailsService;

	// 비밀번호를 암호화 하는데 사용
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 로그아웃시 SessionInfomation을 삭제하여 나중에 로그인이 가능하도록함
	@Bean
	public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
	}

	// Spring Security의 필터 체인을 설정
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
		return security
				// 권한 설정
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/user/**", "/setting", "/auth/logout").authenticated() // 로그인만 되어 있으면 허용
						.anyRequest().permitAll() // 모듀 허용
				)
				// 로그인 설정
				.formLogin(loginConfig -> loginConfig
						.loginPage("/login")
						.usernameParameter("username")
						.passwordParameter("password")
						.loginProcessingUrl("/auth/login") // 로그인 처리 url
						.failureUrl("/login?error=error") // 로그인 실패시 이동되는 url
						.defaultSuccessUrl("/", true) // 로그인 성공 시 이동할 URL
				)
				// 로그아웃 설정
				.logout(logoutConfig -> logoutConfig
						.logoutUrl("/auth/logout") // 해당 url로 post로 전송시 로그아웃됨
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true) // 세션 무효화 여부
				)
				// 세션 설정
				.sessionManagement(session -> session
						.sessionConcurrency(concurrency -> concurrency
								.maximumSessions(1) // 최대 한개만 생성 가능
								.maxSessionsPreventsLogin(true) // 최대 세션 초과 불가
								.expiredUrl("/login") // 세션이 만료될 경우 이동될 url
						)
				)
				.userDetailsService(myUserDetailsService) // 사용자 정보를 로드할 서비스 설정 로그인 기능을 위해 필요
				.build();
	}
}
