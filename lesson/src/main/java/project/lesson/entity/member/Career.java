package project.lesson.entity.member;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Career {
	FIRST_TO_THIRD_GRADE,
	THIRD_TO_FIFTH_GRADE,
	FIFTH_TO_SEVENTH_GRADE,
	OVER_SEVENTH_GRADE;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static Career from(String value) {
		return Arrays.stream(values())
				.filter(v -> v.name().equals(value))
				.findAny()
				.orElse(null);
	}
}
