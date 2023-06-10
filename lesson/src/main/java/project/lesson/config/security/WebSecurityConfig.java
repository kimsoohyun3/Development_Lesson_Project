package project.lesson.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.AllArgsConstructor;
import project.lesson.filter.JwtAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors()
				.and()
				.csrf()
				.disable()
				.httpBasic()
				.disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(
						"/member/join",
						"/auth/sign-in",
						"/v2/api-docs",
						"/swagger-resources/**",
						"/swagger-ui.html",
						"/webjars/**",
						"/swagger/**"
				)
				.permitAll()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(new CustomAuthenticationEntryPoint());

		http.addFilterAfter(
				jwtAuthenticationFilter,
				CorsFilter.class
		);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/v2/api-docs",
				"/swagger-resources/**",
				"/swagger-ui.html",
				"/webjars/**",
				"/swagger/**"
		);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
