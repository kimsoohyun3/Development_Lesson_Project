package project.lesson.dto.member;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import project.lesson.entity.member.AgeGroup;
import project.lesson.entity.member.Gender;
import project.lesson.entity.member.Member;
import project.lesson.entity.member.UserClassification;

@Getter
public class MemberSaveRequestDto {
	@ApiModelProperty(example = "아이디", value = "아이디", required = true)
	@Pattern(regexp = "^[a-z0-9_-]{4,20}$", message = "아이디는 4~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.")
	private String id;

	@ApiModelProperty(example = "비밀번호", value = "비밀번호", required = true)
	@Pattern(
			regexp = "^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
			message = "비밀번호는 8자 이상의 영문 소문자, 숫자, 특수문자가 포함되어야 합니다."
	)
	private String password;

	@ApiModelProperty(example = "이메일", value = "이메일", required = true)
	@Pattern(
			regexp = "^([0-9a-zA-Z_\\.-]+)@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,3}$",
			message = "이메일 형식이 올바르지 않습니다."
	)
	private String email;

	@ApiModelProperty(example = "닉네임", value = "닉네임", required = true)
	@Pattern(
			regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$",
			message = "닉네임은 2~16자의 영문 소문자, 숫자와 한글만 사용 가능합니다."
	)
	private String nickname;

	@ApiModelProperty(example = "성별", value = "성별", required = true)
	@NotNull(message = "올바르지 않은 성별 타입입니다.")
	private Gender gender;

	@ApiModelProperty(example = "회원구분", value = "회원구분", required = true)
	@NotNull(message = "올바르지 않은 회원타입입니다.")
	private UserClassification userClassification;

	@ApiModelProperty(example = "연령대", value = "연령대", required = true)
	@NotNull(message = "올바르지 않은 연령대 타입입니다.")
	private AgeGroup ageGroup;

	@Builder
	public MemberSaveRequestDto(
			String id, String password, String email,
			String nickname, Gender gender,
			UserClassification userClassification,
			AgeGroup ageGroup
	) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.gender = gender;
		this.userClassification = userClassification;
		this.ageGroup = ageGroup;
	}

	public static Member toEntity(MemberSaveRequestDto memberSaveRequestDto) {
		return Member.builder()
				.id(memberSaveRequestDto.getId())
				.password(memberSaveRequestDto.getPassword())
				.email(memberSaveRequestDto.getEmail())
				.nickname(memberSaveRequestDto.getNickname())
				.gender(memberSaveRequestDto.getGender())
				.userClassification(memberSaveRequestDto.getUserClassification())
				.ageGroup(memberSaveRequestDto.getAgeGroup())
				.build();
	}
}
