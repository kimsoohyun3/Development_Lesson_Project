package project.lesson.exception.authmail;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import project.lesson.exception.ExceptionEnum;
import project.lesson.exception.ExceptionMessage;

@RestControllerAdvice
public class AuthMailExceptionHandler {
	@ExceptionHandler(AuthMailException.class)
	public ResponseEntity<ExceptionMessage> handleAuthMailException(AuthMailException e) {
		ExceptionMessage exceptionMessage = new ExceptionMessage(
				ExceptionEnum.AUTH_MAIL_SEND_FAIL.getCode(), e.getMessage(),
				ExceptionEnum.AUTH_MAIL_SEND_FAIL.getMessage()
		);
		return ResponseEntity.badRequest().body(exceptionMessage);
	}
}
