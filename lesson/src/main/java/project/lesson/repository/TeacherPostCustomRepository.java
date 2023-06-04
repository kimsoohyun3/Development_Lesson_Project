package project.lesson.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.lesson.dto.teacherPost.TeacherPostSearchCondition;
import project.lesson.entity.teacherPost.TeacherPost;

import java.util.List;

public interface TeacherPostCustomRepository {

    // 게시물 리스트 조회(검색)
//    List<TeacherPost> search(TeacherPostSearchCondition searchCondition);

    Page<TeacherPost> searchPage(TeacherPostSearchCondition condition, Pageable pageable);
}
