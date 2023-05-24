package project.lesson.dto.RecruitTeacher;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecruitTeacherDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime postWriteDate;

    public RecruitTeacherDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postWriteDate = LocalDateTime.now();
    }
}
