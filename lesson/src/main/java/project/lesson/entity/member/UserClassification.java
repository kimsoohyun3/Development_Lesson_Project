package project.lesson.entity.member;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserClassification {
	STUDENT, TEACHER;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static UserClassification from(String value) {
		return Arrays.stream(values())
				.filter(v -> v.name().equals(value))
				.findAny()
				.orElse(null);
	}
}
