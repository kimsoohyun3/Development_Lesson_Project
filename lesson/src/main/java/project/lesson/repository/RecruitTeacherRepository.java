package project.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.lesson.entity.RecruitTeacher.RecruitTeacher;
import project.lesson.dto.RecruitTeacher.RecruitTeacherDto;

import java.util.List;
import java.util.Optional;

public interface RecruitTeacherRepository extends JpaRepository<RecruitTeacher, Long> {

    // 게시물 리스트 조회
    @Query("select new project.lesson.dto.RecruitTeacher.RecruitTeacherDto(rt.id, rt.title, rt.content, rt.subject, rt.residence, rt.onOrOff) from RecruitTeacher rt")
    List<RecruitTeacherDto> findByPosts();

    // 게시물 ID로 게시물 단건 조회
    @Query("select new project.lesson.dto.RecruitTeacher.RecruitTeacherDto(rt.id, rt.title, rt.content, rt.subject, rt.residence, rt.onOrOff) from RecruitTeacher rt where rt.id = :postId")
    Optional<RecruitTeacherDto> findByPost(@Param("postId") Long postId);
}
