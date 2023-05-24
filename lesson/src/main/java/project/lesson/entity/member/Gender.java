package project.lesson.entity.member;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
	MALE,FEMALE;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static Gender from(String value) {
		return Arrays.stream(values())
				.filter(v -> v.name().equals(value))
				.findAny()
				.orElse(null);
	}
}
