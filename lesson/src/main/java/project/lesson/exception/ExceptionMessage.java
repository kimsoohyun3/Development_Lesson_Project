package project.lesson.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionMessage {
	private String exceptionSimpleName;
	private String msg;
	private LocalDateTime time = LocalDateTime.now();

	public ExceptionMessage(String exceptionSimpleName, String msg) {
		this.exceptionSimpleName = exceptionSimpleName;
		this.msg = msg;
	}
}
