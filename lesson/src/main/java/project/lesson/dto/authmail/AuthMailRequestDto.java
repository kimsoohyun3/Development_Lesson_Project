package project.lesson.dto.authmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthMailRequestDto {
	@ApiModelProperty(value = "이메일", required = true)
	@NotEmpty(message = "이메일을 입력해주세요.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;
}
