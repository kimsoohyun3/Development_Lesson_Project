package project.lesson.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		ObjectMapper objectMapper = new ObjectMapper();
		CustomAccessDeniedHandler.ExceptionMessage exceptionMessage = new CustomAccessDeniedHandler.ExceptionMessage(
				accessDeniedException.getClass().getSimpleName(),
				"권한이 없습니다."
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
