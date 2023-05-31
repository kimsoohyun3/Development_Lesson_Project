package project.lesson.dto.TeacherPost;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import project.lesson.entity.TeacherPost.TeacherPost;

@Getter
public class MyTeacherPostResponseDto {
	List<TeacherPostResponseDto> myPostList;

	public MyTeacherPostResponseDto(List<TeacherPost> teacherPosts) {
		this.myPostList = teacherPosts.stream()
				.map(TeacherPostResponseDto::new)
				.collect(Collectors.toList());
	}
}
