package project.lesson.entity.commonEnum;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum Subject {
    JAVASCRIPT, TYPESCRIPT, REACT, VUE, NODEJS, SPRING, JAVA, NEXTJS, NESTJS, GO, C, PYTHON, DJANGO, SWIFT, KOTLIN,
    MYSQL, MONGODB, PHP, GRAPHQL, FIREBASE, REACTNATIVE, UNITY, FLUTTER, AWS, KUBERNETES, DOCKER, GIT, FIGMA, ZEPLI;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Subject from(String value) {
        return Arrays.stream(values())
                .filter(v -> v.name().equals(value))
                .findAny()
                .orElse(null);
    }
}