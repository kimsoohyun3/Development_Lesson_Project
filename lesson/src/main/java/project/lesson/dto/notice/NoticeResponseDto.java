package project.lesson.dto.notice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.lesson.entity.notice.Notice;
import project.lesson.entity.studentPost.StudentPost;
import project.lesson.entity.commonEnum.OnOrOff;
import project.lesson.entity.commonEnum.Subject;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {

    @ApiModelProperty(example = "PK", value = "PK")
    private Long id;

    @ApiModelProperty(example = "제목", value = "제목")
    private String title;

    @ApiModelProperty(example = "내용", value = "내용")
    private String content;

    // entity 를 dto 변환
    public NoticeResponseDto(Notice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
