package project.lesson.exception.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import project.lesson.exception.ExceptionMessage;

@RestControllerAdvice
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
