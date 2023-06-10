package project.lesson.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		//ObjectMapper objectMapper = new ObjectMapper();
		/*ExceptionMessage message = new ExceptionMessage(
				authException.getClass().getSimpleName(),
				"올바르지 않은 토큰입니다. 인증에 실패하였습니다."
		);*/
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "올바르지 않은 토큰입니다. 인증에 실패하였습니다.");
	}
}
