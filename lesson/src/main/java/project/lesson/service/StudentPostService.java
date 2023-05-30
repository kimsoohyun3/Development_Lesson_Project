package project.lesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.lesson.dto.StudentPost.StudentPostResponseDto;
import project.lesson.dto.StudentPost.StudentPostSaveRequestDto;
import project.lesson.dto.StudentPost.StudentPostUpdateRequestDto;
import project.lesson.dto.TeacherPost.TeacherPostResponseDto;
import project.lesson.dto.TeacherPost.TeacherPostSaveRequestDto;
import project.lesson.dto.TeacherPost.TeacherPostUpdateRequestDto;
import project.lesson.entity.StudentPost.StudentPost;
import project.lesson.entity.TeacherPost.TeacherPost;
import project.lesson.exception.common.CResourceNotExistException;
import project.lesson.repository.StudentPostRepository;
import project.lesson.repository.TeacherPostRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentPostService {

    private final StudentPostRepository studentPostRepository;

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

    // 게시물 리스트 조회
    public List<StudentPostResponseDto> findPosts() {
        List<StudentPost> entityList = studentPostRepository.findAll();

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
