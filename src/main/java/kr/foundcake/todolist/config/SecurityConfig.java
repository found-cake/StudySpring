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

	private final MyUserDetailsService myUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
		return security
				.csrf(csrf -> csrf
						.ignoringRequestMatchers(
								"/auth/**"
						)
				)
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/user/**").authenticated()
						.anyRequest().permitAll()
				)
				// 로그인 설정
				.formLogin(loginConfig -> loginConfig
						.loginPage("/login")
						.usernameParameter("username")
						.passwordParameter("password")
						.loginProcessingUrl("/auth/login")
						.failureUrl("/login?error=error")
						.defaultSuccessUrl("/", true)
				)
				// 로그아웃 설정
				.logout(logoutConfig -> logoutConfig
						.logoutUrl("/auth/logout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true)
				)
				// 세션 설정
				.sessionManagement(session -> session
						.sessionConcurrency(concurrency -> concurrency
								.maximumSessions(1) // 최대 한개만 생성 가능
								.maxSessionsPreventsLogin(true) // 최대 세션 초과 불가
								.expiredUrl("/login")
						)
				)
				.userDetailsService(myUserDetailsService)
				.build();
	}
}
