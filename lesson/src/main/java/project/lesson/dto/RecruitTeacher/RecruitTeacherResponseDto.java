package project.lesson.dto.RecruitTeacher;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import project.lesson.entity.RecruitTeacher.OnOrOff;
import project.lesson.entity.RecruitTeacher.RecruitTeacher;
import project.lesson.entity.RecruitTeacher.Subject;

import java.time.LocalDateTime;

@Data
public class RecruitTeacherResponseDto {

    @ApiModelProperty(example = "PK", value = "PK")
    private Long id;

    @ApiModelProperty(example = "PK", value = "PK")
    private String title;

    @ApiModelProperty(example = "내용", value = "내용")
    private String content;

    @ApiModelProperty(example = "과목", value = "과목")
    private Subject subject;

    @ApiModelProperty(example = "지역", value = "지역")
    private String area;

    @ApiModelProperty(example = "과외 온 / 오프 여부", value = "과외 온 / 오프 여부")
    private OnOrOff onOrOff;

    @ApiModelProperty(example = "게시물 작성일자", value = "게시물 작성일자")
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
