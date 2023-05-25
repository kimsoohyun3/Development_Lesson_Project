package project.lesson.exception.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import project.lesson.exception.ExceptionMessage;
import project.lesson.exception.authmail.AuthMailException;

public class MemberExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionMessage> handleIllegalArgumentException(IllegalArgumentException e) {
		ExceptionMessage exceptionMessage = new ExceptionMessage(
				e.getClass().getSimpleName(),
				e.getMessage()
		);
		return ResponseEntity.badRequest().body(exceptionMessage);
	}
}
