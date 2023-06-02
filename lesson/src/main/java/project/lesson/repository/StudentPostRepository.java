package project.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.lesson.entity.studentPost.StudentPost;

@Repository
public interface StudentPostRepository extends JpaRepository<StudentPost, Long> {
}
