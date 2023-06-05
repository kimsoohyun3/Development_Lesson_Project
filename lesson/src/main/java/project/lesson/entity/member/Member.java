package project.lesson.entity.member;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.lesson.entity.base.BaseEntity;
import project.lesson.entity.teacherPost.TeacherPost;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor
public class Member extends BaseEntity {
	@Id
	@Column(name = "ID")
	String id;

	@Column(name = "PASSWORD", nullable = false)
	String password;

	@Column(name = "EMAIL", nullable = false)
	String email;

	@Column(name = "NICKNAME", nullable = false)
	String nickname;

	@Column(name = "GENDER", nullable = false)
	@Enumerated(EnumType.STRING)
	Gender gender;

	@Column(name = "USER_CLASSIFICATION", nullable = false)
	@Enumerated(EnumType.STRING)
	UserClassification userClassification;

	@Column(name = "AGE_GROUP", nullable = false)
	@Enumerated(EnumType.STRING)
	AgeGroup ageGroup;

	@OneToMany(mappedBy = "member")
	private List<TeacherPost> teacherPosts = new ArrayList<>();

	@Builder
	public Member(
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

	public String modifyPassword(String password) {
		this.password = password;
		return this.id;
	}
}

