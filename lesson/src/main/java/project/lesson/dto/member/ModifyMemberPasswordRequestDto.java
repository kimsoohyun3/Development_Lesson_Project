package project.lesson.dto.member;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ModifyMemberPasswordRequestDto {
	@ApiModelProperty(example = "비밀번호", value = "비밀번호", required = true)
	@Pattern(
			regexp = "^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
			message = "비밀번호는 8자 이상의 영문 소문자, 숫자, 특수문자가 포함되어야 합니다."
	)
	private String password;
}
