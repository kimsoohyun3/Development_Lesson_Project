package project.lesson.dto.authmail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthMailResponseDto {
	@ApiModelProperty(value = "메일 인증 코드")
	private String certificationCode;
}
