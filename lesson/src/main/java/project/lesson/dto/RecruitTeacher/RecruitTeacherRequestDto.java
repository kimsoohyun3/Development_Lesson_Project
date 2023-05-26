package project.lesson.dto.RecruitTeacher;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class RecruitTeacherRequestDto {

    @ApiModelProperty(example = "PK", value = "PK", required = true)
    private Long id;

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
    private String subject;

    @ApiModelProperty(example = "지역", value = "지역", required = true)
    @NotNull(message = "지역을 입력해주세요")
    private String area;

    @ApiModelProperty(example = "과외 온 / 오프 여부", value = "과외 온 / 오프 여부", required = true)
    @NotNull(message = "과외 온 / 오프 여부를 입력해주세요")
    private String onOrOff;

    @ApiModelProperty(example = "게시물 작성일자", value = "게시물 작성일자", required = true)
    private LocalDateTime postWriteDate;
    }
