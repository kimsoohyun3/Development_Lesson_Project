package project.lesson.exception.authmail;

public class AuthMailException extends RuntimeException {
	public AuthMailException() {
	}

	public AuthMailException(String message) {
		super(message);
	}
}
