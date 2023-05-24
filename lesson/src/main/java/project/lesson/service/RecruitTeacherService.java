package project.lesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.lesson.exception.common.CResourceNotExistException;
import project.lesson.dto.RecruitTeacher.RecruitTeacherDto;
import project.lesson.repository.RecruitTeacherRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitTeacherService {

    private final RecruitTeacherRepository recruitTeacherRepository;

    // 게시판 이름으로 게시물 리스트 조회
    public List<RecruitTeacherDto> findPosts() {
        return recruitTeacherRepository.findByPosts();
    }

    // 게시물 ID로 게시물 단건 조회
    public RecruitTeacherDto findPost(long postId) {
        return recruitTeacherRepository.findByPost(postId).orElseThrow(() -> new CResourceNotExistException());
    }
}
