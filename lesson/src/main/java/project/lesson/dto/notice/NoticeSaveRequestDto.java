package project.lesson.dto.notice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.lesson.entity.notice.Notice;
import project.lesson.entity.studentPost.StudentPost;
import project.lesson.entity.commonEnum.OnOrOff;
import project.lesson.entity.commonEnum.Subject;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class NoticeSaveRequestDto {

    @ApiModelProperty(example = "제목", value = "제목", required = true)
    @NotNull(message = "제목을 입력해주세요")
    @Max(value = 110, message = "제목의 Byte 길이가 110 이하여야 합니다")
    private String title;

    @ApiModelProperty(example = "내용", value = "내용", required = true)
    @NotNull(message = "내용을 입력해주세요")
    @Max(value = 1120, message = "내용의 Byte 길이가 1120 이하여야 합니다")
    private String content;

    @Builder
    public NoticeSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // dto 를 entity 변환
    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .content(content)
                .build();
    }
}
