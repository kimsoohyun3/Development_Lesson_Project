package project.lesson.dto.studentPost;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import project.lesson.entity.commonEnum.OnOrOff;
import project.lesson.entity.commonEnum.Subject;
import project.lesson.entity.studentPost.StudentPost;

@Data
public class StudentPostSaveRequestDto {

    @ApiModelProperty(example = "제목", value = "제목", required = true)
    @NotNull(message = "제목을 입력해주세요")
    @Max(value = 110, message = "제목의 Byte 길이가 110 이하여야 합니다")
    private String title;

    @ApiModelProperty(example = "내용", value = "내용", required = true)
    @NotNull(message = "내용을 입력해주세요")
    @Max(value = 1120, message = "내용의 Byte 길이가 1120 이하여야 합니다")
    private String content;

    @ApiModelProperty(example = "과목", value = "과목", required = true)
    @NotNull(message = "과목을 선택해주세요")
    private Subject subject;

    @ApiModelProperty(example = "지역", value = "지역", required = true)
    @NotNull(message = "지역을 입력해주세요")
    private String area;

    @ApiModelProperty(example = "과외 온 / 오프 여부", value = "과외 온 / 오프 여부", required = true)
    @NotNull(message = "과외 온 / 오프 여부를 입력해주세요")
    private OnOrOff onOrOff;

    @Builder
    public StudentPostSaveRequestDto(String title, String content, Subject subject, String area, OnOrOff onOrOff) {
        this.title = title;
        this.content = content;
        this.subject = subject;
        this.area = area;
        this.onOrOff = onOrOff;
    }

    // dto 를 entity 변환
    public StudentPost toEntity() {
        return StudentPost.builder()
                .title(title)
                .content(content)
                .subject(subject)
                .area(area)
                .onOrOff(onOrOff)
                .build();
    }
}
