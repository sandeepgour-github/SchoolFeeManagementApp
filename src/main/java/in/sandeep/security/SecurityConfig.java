package in.sandeep.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		
		http.authorizeHttpRequests(request->
		request.requestMatchers("/auth/**","admin-register.html","admin-login.html").permitAll()
		.anyRequest().authenticated())
		.exceptionHandling(ex->ex.authenticationEntryPoint(jwtAuthEntryPoint))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.csrf(csrf->csrf.disable());
		http.cors();
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager AuthenticationManager(AuthenticationConfiguration config)throws Exception { 
		return config.getAuthenticationManager();
	}
}
