package project.lesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.lesson.dto.studentPost.StudentPostResponseDto;
import project.lesson.dto.studentPost.StudentPostSaveRequestDto;
import project.lesson.dto.studentPost.StudentPostUpdateRequestDto;
import project.lesson.dto.teacherPost.TeacherPostResponseDto;
import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.entity.studentPost.StudentPost;
import project.lesson.entity.teacherPost.TeacherPost;
import project.lesson.exception.common.CResourceNotExistException;
import project.lesson.repository.StudentPostRepository;
import project.lesson.repository.StudentPostRepositoryImpl;
import project.lesson.repository.TeacherPostRepositoryImpl;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentPostService {

    private final StudentPostRepository studentPostRepository;
    private final StudentPostRepositoryImpl studentPostRepositoryImpl;

    // 게시물 등록
    public Long savePost(StudentPostSaveRequestDto requestDto) {
        return studentPostRepository.save(requestDto.toEntity()).getId();
    }

    // 게시물 수정
    public Long updatePost(Long postId, StudentPostUpdateRequestDto requestDto) {
        StudentPost studentPost = studentPostRepository.findById(postId).orElseThrow(() -> new CResourceNotExistException());

        studentPost.updatePost(requestDto.getTitle(), requestDto.getContent(), requestDto.getSubject(), requestDto.getArea(), requestDto.getOnOrOff());

        return postId;
    }

    // 게시물 삭제
    public void deletePost(Long postId) {
        StudentPost studentPost = studentPostRepository.findById(postId).orElseThrow(() -> new CResourceNotExistException());

        studentPostRepository.delete(studentPost);
    }

    // 게시물 리스트 조회(검색)
    public List<StudentPostResponseDto> findPosts(SearchCondition searchCondition, Pageable pageable) {
        Page<StudentPost> entityList = studentPostRepositoryImpl.searchPage(searchCondition, pageable);

        List<StudentPostResponseDto> dtoList = entityList.stream()
                .map(m -> new StudentPostResponseDto(m))
                .collect(Collectors.toList());

        return dtoList;
    }

    // 게시물 ID로 게시물 단건 조회
    public StudentPostResponseDto findPost(long postId) {
        StudentPost entity = studentPostRepository.findById(postId).orElseThrow(() -> new CResourceNotExistException());;

        return new StudentPostResponseDto(entity);
    }
}
