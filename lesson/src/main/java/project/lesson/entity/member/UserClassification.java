package project.lesson.entity.member;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserClassification {
	STUDENT, TEACHER;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static UserClassification get(String userClassificationString) {
		return Arrays.stream(values())
				.filter(userClassification -> userClassification.name().equals(userClassificationString))
				.findAny()
				.orElse(null);
	}
}
