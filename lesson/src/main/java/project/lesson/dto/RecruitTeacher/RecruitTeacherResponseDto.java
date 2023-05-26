package project.lesson.dto.RecruitTeacher;

import lombok.Data;
import project.lesson.entity.RecruitTeacher.OnOrOff;
import project.lesson.entity.RecruitTeacher.RecruitTeacher;
import project.lesson.entity.RecruitTeacher.Subject;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class RecruitTeacherResponseDto {

    private Long id;

    private String title;

    private String content;

    private Subject subject;

    private String area;

    private OnOrOff onOrOff;

    private LocalDateTime postWriteDate;

    // repository 를 통해 조회한 entity 를 dto 변환
    public RecruitTeacherResponseDto(RecruitTeacher entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.subject = entity.getSubject();
        this.area = entity.getArea();
        this.onOrOff = entity.getOnOrOff();
        this.postWriteDate = LocalDateTime.now();
    }
}
