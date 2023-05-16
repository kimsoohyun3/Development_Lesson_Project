package project.lesson.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BindException.class)
	public ResponseEntity<ExceptionMessage> handleBindException(BindException e) {
		String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		ExceptionMessage exceptionMessage = new ExceptionMessage(
				HttpStatus.BAD_REQUEST.value(),
				e.getClass().getSimpleName(),
				message
		);

		return ResponseEntity.badRequest()
				.body(exceptionMessage);
	}
}
