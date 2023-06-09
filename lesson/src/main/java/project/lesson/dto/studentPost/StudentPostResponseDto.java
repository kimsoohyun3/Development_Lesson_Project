package project.lesson.dto.studentPost;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import project.lesson.entity.studentPost.StudentPost;
import project.lesson.entity.commonEnum.OnOrOff;
import project.lesson.entity.commonEnum.Subject;

@Data
public class StudentPostResponseDto {

    @ApiModelProperty(example = "PK", value = "PK")
    private Long id;

    @ApiModelProperty(example = "제목", value = "제목")
    private String title;

    @ApiModelProperty(example = "내용", value = "내용")
    private String content;

    @ApiModelProperty(example = "과목", value = "과목")
    private Subject subject;

    @ApiModelProperty(example = "지역", value = "지역")
    private String area;

    @ApiModelProperty(example = "과외 온 / 오프 여부", value = "과외 온 / 오프 여부")
    private OnOrOff onOrOff;

    // entity 를 dto 변환
    public StudentPostResponseDto(StudentPost entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.subject = entity.getSubject();
        this.area = entity.getArea();
        this.onOrOff = entity.getOnOrOff();
    }
}
