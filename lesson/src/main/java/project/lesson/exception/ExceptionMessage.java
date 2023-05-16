package project.lesson.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionMessage {
	private int code;
	private String exceptionSimpleName;
	private String msg;
	private LocalDateTime time = LocalDateTime.now();

	public ExceptionMessage(int code, String exceptionSimpleName, String msg) {
		this.code = code;
		this.exceptionSimpleName = exceptionSimpleName;
		this.msg = msg;
	}
}
