package project.lesson.dto.RecruitTeacher;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecruitTeacherDto {

    private Long id;

    private String subject; // 과목

    private String residence; // 거주지

    private String onOrOff; // 과외 온 / 오프 여부

    private String title;

    private String content;

    private LocalDateTime postWriteDate;

    public RecruitTeacherDto(Long id, String title, String content, String subject, String residence, String onOrOff) {
        this.id = id;
        this.subject = subject;
        this.residence = residence;
        this.onOrOff = onOrOff;
        this.title = title;
        this.content = content;
        this.postWriteDate = LocalDateTime.now();
    }
}
