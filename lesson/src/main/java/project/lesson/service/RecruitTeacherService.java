package project.lesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.lesson.dto.RecruitTeacher.RecruitTeacherResponseDto;
import project.lesson.entity.RecruitTeacher.RecruitTeacher;
import project.lesson.exception.common.CResourceNotExistException;
import project.lesson.dto.RecruitTeacher.RecruitTeacherRequestDto;
import project.lesson.repository.RecruitTeacherRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitTeacherService {

    private final RecruitTeacherRepository recruitTeacherRepository;

    // 게시판 이름으로 게시물 리스트 조회
    public List<RecruitTeacherResponseDto> findPosts() {
        List<RecruitTeacher> entityList = recruitTeacherRepository.findAll();
        List<RecruitTeacherResponseDto> dtoList = entityList.stream()
                .map(m -> new RecruitTeacherResponseDto(m))
                .collect(Collectors.toList());
        return dtoList;
    }

    // 게시물 ID로 게시물 단건 조회
    public RecruitTeacherResponseDto findById(long postId) {
        RecruitTeacher entity = recruitTeacherRepository.findById(postId).orElseThrow(() -> new CResourceNotExistException());;
        return new RecruitTeacherResponseDto(entity);
    }
}
