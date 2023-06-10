package project.lesson.service;

import static org.junit.jupiter.api.Assertions.*;
import static project.lesson.entity.commonEnum.SearchType.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import project.lesson.dto.studentPost.StudentPostResponseDto;
import project.lesson.dto.studentPost.StudentPostSaveRequestDto;
import project.lesson.dto.studentPost.StudentPostUpdateRequestDto;
import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.entity.commonEnum.OnOrOff;
import project.lesson.entity.commonEnum.Subject;
import project.lesson.entity.studentpost.StudentPost;
import project.lesson.repository.StudentPostRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StudentPostServiceTest {

    @Autowired
    StudentPostService studentPostService;
    @Autowired
    StudentPostRepository studentPostRepository;

    @AfterEach
    public void cleanup() {
        studentPostRepository.deleteAll();
    }

    public static StudentPostSaveRequestDto dtoBuilder() {
        String saveTitle = "saveTitle";

        return StudentPostSaveRequestDto.builder()
                .title(saveTitle)
                .content("내용")
                .subject(Subject.valueOf("JAVASCRIPT"))
                .area("광주")
                .onOrOff(OnOrOff.valueOf("ONLINE"))
                .build();
    }

    // 게시물 등록 테스트
    @Test
    void savePost() {
        // given
        StudentPostSaveRequestDto saveRequestDto = dtoBuilder();

        // when
        studentPostService.savePost(saveRequestDto);

        List<StudentPost> studentPostList = studentPostRepository.findAll();

        // then
        assertEquals(studentPostList.get(0).getTitle(), saveRequestDto.getTitle());
    }

    // 게시물 수정 테스트
    @Test
    void updatePost() {
        // given
        StudentPostSaveRequestDto saveRequestDto = dtoBuilder();

        String updateTitle = "updateTitle";

        Long postId = studentPostService.savePost(saveRequestDto);

        // when
        StudentPostUpdateRequestDto updateRequestDto = StudentPostUpdateRequestDto.builder()
                .title(updateTitle)
                .content("내용")
                .subject(Subject.valueOf("JAVASCRIPT"))
                .area("광주")
                .onOrOff(OnOrOff.valueOf("ONLINE"))
                .build();

        studentPostService.updatePost(postId, updateRequestDto);

        List<StudentPost> studentPostList = studentPostRepository.findAll();

        // then
        assertEquals(studentPostList.get(0).getTitle(), updateTitle);
    }

    // 게시물 삭제 테스트
    @Test
    void deletePost() {
        // given
        StudentPostSaveRequestDto saveRequestDto = dtoBuilder();

        Long postId = studentPostService.savePost(saveRequestDto);

        // when
        studentPostService.deletePost(postId);

        List<StudentPost> studentPostList = studentPostRepository.findAll();

        // then
        assertEquals(studentPostList.size(), 0);
    }

    // 게시물 리스트 조회 테스트
    @Test
    void findPosts() {
        // given
        StudentPostSaveRequestDto saveRequestDto = dtoBuilder();

        StudentPostSaveRequestDto saveRequestDto2 = StudentPostSaveRequestDto.builder()
                .title("제목")
                .content("내용")
                .subject(Subject.valueOf("JAVASCRIPT"))
                .area("광주")
                .onOrOff(OnOrOff.valueOf("ONLINE"))
                .build();

        studentPostService.savePost(saveRequestDto);
        studentPostService.savePost(saveRequestDto2);

        // when
        List<StudentPostResponseDto> studentPostDtoList = studentPostService.findPosts(new SearchCondition(TITLE, "제목"), Pageable.ofSize(10));

        // then
        assertEquals(studentPostDtoList.size(), 1);
    }

    // 게시물 ID로 게시물 단건 조회 테스트
    @Test
    void findPost() {
        // given
        StudentPostSaveRequestDto saveRequestDto = dtoBuilder();

        Long postId = studentPostService.savePost(saveRequestDto);

        // when
        List<StudentPost> studentPostList = studentPostRepository.findAll();

        // then
        assertEquals(studentPostList.get(0).getTitle(), dtoBuilder().getTitle());
    }
}