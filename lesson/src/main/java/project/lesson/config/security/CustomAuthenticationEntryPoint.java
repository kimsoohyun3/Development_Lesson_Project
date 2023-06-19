package project.lesson.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ExceptionMessage exceptionMessage = new ExceptionMessage(
				authException.getClass().getSimpleName(),
				"유효하지 않은 토큰입니다."
		);
		response.setContentType("json/application");
		response.setCharacterEncoding("utf-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(objectMapper.writeValueAsString(exceptionMessage));
	}

	class ExceptionMessage {
		private String exception;
		private String message;

		public ExceptionMessage(String exception, String message) {
			this.exception = exception;
			this.message = message;
		}

		public String getException() {
			return exception;
		}

		public String getMessage() {
			return message;
		}
	}
}
