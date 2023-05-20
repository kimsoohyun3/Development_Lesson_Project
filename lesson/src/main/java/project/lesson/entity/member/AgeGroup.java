package project.lesson.entity.member;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AgeGroup {
	TEENS, TWENTIES, THIRTIES,
	FORTIES, FIFTIES, SIXTIES,
	SEVENTIES, EIGHTIES, NINETIES,
	OVER_HUNDREDS;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static AgeGroup get(String ageGroupString) {
		return Arrays.stream(values())
				.filter(ageGroup -> ageGroup.name().equals(ageGroupString))
				.findAny()
				.orElse(null);
	}
}
