package project.lesson.entity.commonEnum;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum OnOrOff {

    ONLINE, OFFLINE, ALL;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static OnOrOff from(String value) {
        return Arrays.stream(values())
                .filter(v -> v.name().equals(value))
                .findAny()
                .orElse(null);
    }
}
