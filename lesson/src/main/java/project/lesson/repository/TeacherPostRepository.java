package project.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.lesson.entity.TeacherPost.TeacherPost;

@Repository
public interface TeacherPostRepository extends JpaRepository<TeacherPost, Long> {
}
