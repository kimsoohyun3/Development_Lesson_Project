package project.lesson.dto.teacherPost;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import project.lesson.entity.teacherPost.TeacherPost;

@Getter
public class MyTeacherPostResponseDto {
	List<TeacherPostResponseDto> myPostList;

	public MyTeacherPostResponseDto(List<TeacherPost> teacherPosts) {
		this.myPostList = teacherPosts.stream()
				.map(TeacherPostResponseDto::new)
				.collect(Collectors.toList());
	}
}
