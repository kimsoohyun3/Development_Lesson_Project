package project.lesson.dto.member;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ModifyMemberPasswordRequestDto {
	@ApiModelProperty(example = "아이디", value = "아이디", required = true)
	@Pattern(regexp = "^[a-z0-9_-]{4,20}$", message = "아이디는 4~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.")
	private String id;

	@ApiModelProperty(example = "비밀번호", value = "비밀번호", required = true)
	@Pattern(
			regexp = "^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
			message = "비밀번호는 8자 이상의 영문 소문자, 숫자, 특수문자가 포함되어야 합니다."
	)
	private String password;
}
