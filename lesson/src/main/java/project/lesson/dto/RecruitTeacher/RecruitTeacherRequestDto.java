package project.lesson.dto.RecruitTeacher;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class RecruitTeacherRequestDto {


    private Long id;

    @NotNull(message = "제목을 입력해주세요")
    @Max(value = 110, message = "제목의 Byte 길이가 110 이하여야 합니다")
    private String title;

    @NotNull(message = "내용을 입력해주세요")
    @Max(value = 1120, message = "내용의 Byte 길이가 1120 이하여야 합니다")
    private String content;

    @NotNull(message = "과목을 선택해주세요")
    private String subject;

    @NotNull(message = "지역을 입력해주세요")
    private String area;

    @NotNull(message = "온 / 오프")
    private String onOrOff;

    private LocalDateTime postWriteDate;
    }
