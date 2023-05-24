package project.lesson.entity.member;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AgeGroup {
	TEENS, TWENTIES, THIRTIES, FORTIES, FIFTIES, SIXTIES, SEVENTIES, EIGHTIES, NINTIES, OVER_NINETY;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static AgeGroup from(String value) {
		return Arrays.stream(values())
				.filter(v -> v.name().equals(value))
				.findAny()
				.orElse(null);
	}
}
