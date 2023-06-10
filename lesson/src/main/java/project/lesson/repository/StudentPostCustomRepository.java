package project.lesson.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.lesson.entity.studentpost.StudentPost;
import project.lesson.entity.commonClass.SearchCondition;

public interface StudentPostCustomRepository {

    Page<StudentPost> searchPage(SearchCondition condition, Pageable pageable);
}
