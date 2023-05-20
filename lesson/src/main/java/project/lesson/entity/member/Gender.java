package project.lesson.entity.member;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
	MALE, FEMALE;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static Gender get(String genderString) {
		return Arrays.stream(values())
				.filter(gender -> gender.name().equals(genderString))
				.findAny()
				.orElse(null);
	}
}
