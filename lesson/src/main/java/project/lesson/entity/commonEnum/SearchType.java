package project.lesson.entity.commonEnum;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum SearchType {

    TITLE, TITLECONTENT, SUBJECT;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static SearchType from(String value) {
        return Arrays.stream(values())
                .filter(v -> v.name().equals(value))
                .findAny()
                .orElse(null);
    }
}
