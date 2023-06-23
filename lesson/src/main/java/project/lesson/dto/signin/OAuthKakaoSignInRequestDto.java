package project.lesson.dto.signin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import project.lesson.dto.member.MemberSaveRequestDto;
import project.lesson.entity.member.AgeGroup;
import project.lesson.entity.member.Career;
import project.lesson.entity.member.Gender;
import project.lesson.entity.member.Member;
import project.lesson.entity.member.UserClassification;

@Getter
@AllArgsConstructor
public class OAuthKakaoSignInRequestDto {

	@ApiModelProperty(example = "닉네임", value = "닉네임", required = true)
	@Pattern(
			regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$",
			message = "닉네임은 2~16자의 영문 소문자, 숫자와 한글만 사용 가능합니다."
	)
	private String nickname;

	@ApiModelProperty(example = "회원구분", value = "회원구분", required = true)
	@NotNull(message = "올바르지 않은 회원타입입니다.")
	private UserClassification userClassification;

	@ApiModelProperty(example = "연령대", value = "연령대", required = true)
	@NotNull(message = "올바르지 않은 연령대 타입입니다.")
	private AgeGroup ageGroup;

	@ApiModelProperty(example = "경력", value = "경력", required = true)
	@NotNull(message = "올바르지 않은 경력 타입입니다.")
	private Career career;

}
