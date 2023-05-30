package project.lesson.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.lesson.dto.StudentPost.StudentPostResponseDto;
import project.lesson.dto.StudentPost.StudentPostSaveRequestDto;
import project.lesson.dto.StudentPost.StudentPostUpdateRequestDto;
import project.lesson.dto.TeacherPost.TeacherPostResponseDto;
import project.lesson.dto.TeacherPost.TeacherPostSaveRequestDto;
import project.lesson.dto.TeacherPost.TeacherPostUpdateRequestDto;
import project.lesson.service.StudentPostService;
import project.lesson.service.TeacherPostService;

import java.util.List;

@Api(tags = {"과외 선생님 구인 관련 API"})
@RestController
@RequiredArgsConstructor
public class StudentPostController {

    private final StudentPostService studentPostService;

    // 게시물 등록
    @PostMapping(value = "/v1/studentPost/post")
    public Long savePost(@RequestBody StudentPostSaveRequestDto requestDto) {
        return studentPostService.savePost(requestDto);
    }

    // 게시물 수정
    @PutMapping(value = "/v1/studentPost/post/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody StudentPostUpdateRequestDto requestDto) {
        return studentPostService.updatePost(postId, requestDto);
    }

    // 게시물 삭제
    @DeleteMapping("v1/studentPost/post/{postId}")
    public Long delete(@PathVariable Long postId) {
        studentPostService.deletePost(postId);

        return postId;
    }

    // 게시물 리스트 조회
    @GetMapping(value = "/v1/studentPost/posts")
    public ResponseEntity<List<StudentPostResponseDto>> findPosts() {
        return ResponseEntity.ok().body(studentPostService.findPosts());
    }

    // 게시물 ID로 게시물 단건 조회
    @GetMapping(value = "/v1/studentPost/post/{postId}")
    public ResponseEntity<StudentPostResponseDto> findPost(@PathVariable long postId) {
        return ResponseEntity.ok().body(studentPostService.findPost(postId));
    }
}
