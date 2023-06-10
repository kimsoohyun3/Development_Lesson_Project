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

import project.lesson.dto.teacherpost.TeacherPostResponseDto;
import project.lesson.dto.teacherpost.TeacherPostSaveRequestDto;
import project.lesson.dto.teacherpost.TeacherPostUpdateRequestDto;
import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.entity.commonEnum.OnOrOff;
import project.lesson.entity.commonEnum.Subject;
import project.lesson.entity.teacherPost.TeacherPost;
import project.lesson.repository.TeacherPostRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TeacherPostServiceTest {

    @Autowired
    TeacherPostService teacherPostService;
    @Autowired
    TeacherPostRepository teacherPostRepository;

    @AfterEach
    public void cleanup() {
        teacherPostRepository.deleteAll();
    }

    public static TeacherPostSaveRequestDto dtoBuilder() {
        String saveTitle = "saveTitle";

        return TeacherPostSaveRequestDto.builder()
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
        TeacherPostSaveRequestDto saveRequestDto = dtoBuilder();

        // when
        teacherPostService.savePost(saveRequestDto);

        List<TeacherPost> teacherPostList = teacherPostRepository.findAll();

        // then
        assertEquals(teacherPostList.get(0).getTitle(), saveRequestDto.getTitle());
    }

    // 게시물 수정 테스트
    @Test
    void updatePost() {
        // given
        TeacherPostSaveRequestDto saveRequestDto = dtoBuilder();

        String updateTitle = "updateTitle";

        Long postId = teacherPostService.savePost(saveRequestDto);

        // when
        TeacherPostUpdateRequestDto updateRequestDto = TeacherPostUpdateRequestDto.builder()
                .title(updateTitle)
                .content("내용")
                .subject(Subject.valueOf("JAVASCRIPT"))
                .area("광주")
                .onOrOff(OnOrOff.valueOf("ONLINE"))
                .build();

        teacherPostService.updatePost(postId, updateRequestDto);

        List<TeacherPost> teacherPostList = teacherPostRepository.findAll();

        // then
        assertEquals(teacherPostList.get(0).getTitle(), updateTitle);
    }

    // 게시물 삭제 테스트
    @Test
    void deletePost() {
        // given
        TeacherPostSaveRequestDto saveRequestDto = dtoBuilder();

        Long postId = teacherPostService.savePost(saveRequestDto);

        // when
        teacherPostService.deletePost(postId);

        List<TeacherPost> teacherPostList = teacherPostRepository.findAll();

        // then
        assertEquals(teacherPostList.size(), 0);
    }

    // 게시물 리스트 조회 테스트
    @Test
    void findPosts() {
        // given
        TeacherPostSaveRequestDto saveRequestDto = dtoBuilder();

        TeacherPostSaveRequestDto saveRequestDto2 = TeacherPostSaveRequestDto.builder()
                .title("제목")
                .content("내용")
                .subject(Subject.valueOf("JAVASCRIPT"))
                .area("광주")
                .onOrOff(OnOrOff.valueOf("ONLINE"))
                .build();

        teacherPostService.savePost(saveRequestDto);
        teacherPostService.savePost(saveRequestDto2);

        // when
        List<TeacherPostResponseDto> teacherPostDtoList = teacherPostService.findPosts(new SearchCondition(TITLE, "제목"), Pageable.ofSize(10));

        // then
        assertEquals(teacherPostDtoList.size(), 1);
    }

    // 게시물 ID로 게시물 단건 조회 테스트
    @Test
    void findPost() {
        // given
        TeacherPostSaveRequestDto saveRequestDto = dtoBuilder();

        Long postId = teacherPostService.savePost(saveRequestDto);

        // when
        List<TeacherPost> teacherPostList = teacherPostRepository.findAll();

        // then
        assertEquals(teacherPostList.get(0).getTitle(), dtoBuilder().getTitle());
    }
}