package project.lesson.dto.RecruitTeacher;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecruitTeacherDto {

    private Long id;

    private String title;

    private String content;

    private String subject;

    private String residence;

    private String onOrOff;

    private LocalDateTime postWriteDate;

    public RecruitTeacherDto(Long id, String title, String content, String subject, String residence, String onOrOff) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.subject = subject;
        this.residence = residence;
        this.onOrOff = onOrOff;
        this.postWriteDate = LocalDateTime.now();
    }
}
