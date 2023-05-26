package project.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.lesson.entity.RecruitTeacher.RecruitTeacher;
import project.lesson.dto.RecruitTeacher.RecruitTeacherRequestDto;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitTeacherRepository extends JpaRepository<RecruitTeacher, Long> {
}
