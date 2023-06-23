package project.lesson.entity.member;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import project.lesson.dto.signin.OAuthKakaoSignInRequestDto;
import project.lesson.entity.base.BaseEntity;
import project.lesson.entity.teacherPost.TeacherPost;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor
@ToString
public class Member extends BaseEntity {
	@Id
	@Column(name = "ID")
	String id;

	@Column(name = "PASSWORD")
	String password;

	@Column(name = "EMAIL", nullable = false, unique = true)
	String email;

	@Column(name = "NICKNAME", unique = true)
	String nickname;

	@Column(name = "GENDER", nullable = false)
	@Enumerated(EnumType.STRING)
	Gender gender;

	@Column(name = "USER_CLASSIFICATION")
	@Enumerated(EnumType.STRING)
	UserClassification userClassification;

	@Column(name = "AGE_GROUP", nullable = false)
	@Enumerated(EnumType.STRING)
	AgeGroup ageGroup;

	@Column(name = "CAREER")
	@Enumerated(EnumType.STRING)
	Career career;

	@Column(name = "ROLE")
	@Enumerated(EnumType.STRING)
	Role role = Role.ROLE_USER;

	@Builder
	public Member(
			String id, String password, String email,
			String nickname, Gender gender,
			UserClassification userClassification,
			AgeGroup ageGroup,
			Career career
	) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.gender = gender;
		this.userClassification = userClassification;
		this.ageGroup = ageGroup;
		this.career = career;
	}

	public String modifyPassword(String password) {
		this.password = password;
		return this.id;
	}

	public String modifyMemberInfoAfterOauthKakaoSignIn(
			OAuthKakaoSignInRequestDto oAuthKakaoSignInRequestDto

	) {
		this.nickname = oAuthKakaoSignInRequestDto.getNickname();
		this.userClassification = oAuthKakaoSignInRequestDto.getUserClassification();
		this.ageGroup = oAuthKakaoSignInRequestDto.getAgeGroup();
		this.career = oAuthKakaoSignInRequestDto.getCareer();
		return this.id;
	}

}

