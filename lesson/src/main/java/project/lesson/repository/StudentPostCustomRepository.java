package project.lesson.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.entity.studentPost.StudentPost;
import project.lesson.entity.teacherPost.TeacherPost;

public interface StudentPostCustomRepository {

    Page<StudentPost> searchPage(SearchCondition condition, Pageable pageable);
}
