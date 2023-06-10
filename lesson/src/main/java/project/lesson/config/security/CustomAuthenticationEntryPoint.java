package project.lesson.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.lesson.exception.ExceptionMessage;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		//ObjectMapper objectMapper = new ObjectMapper();
		/*ExceptionMessage message = new ExceptionMessage(
				authException.getClass().getSimpleName(),
				"올바르지 않은 토큰입니다. 인증에 실패하였습니다."
		);*/
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("올바르지 않은 토큰입니다. 인증에 실패하였습니다.");
	}
}
