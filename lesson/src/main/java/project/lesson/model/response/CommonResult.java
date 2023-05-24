package project.lesson.model.response;

import lombok.Data;

@Data
public class CommonResult {

    private boolean successOrFailure; // 응답 성공여부

    private int code; // 응답 코드번호

    private String message; // 응답 메시지
}
