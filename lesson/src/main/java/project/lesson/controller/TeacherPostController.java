package project.lesson.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.lesson.dto.TeacherPost.TeacherPostResponseDto;
import project.lesson.dto.TeacherPost.TeacherPostSaveRequestDto;
import project.lesson.dto.TeacherPost.TeacherPostUpdateRequestDto;
import project.lesson.service.TeacherPostService;

import java.util.List;

@Api(tags = {"과외 선생님 구인 관련 API"})
@RestController
@RequiredArgsConstructor
public class TeacherPostController {

    private final TeacherPostService teacherPostService;

    // 게시물 등록
    @PostMapping(value = "/v1/teacherPost/post")
    public Long savePost(@RequestBody TeacherPostSaveRequestDto requestDto) {
        return teacherPostService.savePost(requestDto);
    }

    // 게시물 수정
    @PutMapping(value = "/v1/teacherPost/post/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody TeacherPostUpdateRequestDto requestDto) {
        return teacherPostService.updatePost(postId, requestDto);
    }

    // 게시물 삭제
    @DeleteMapping("v1/teacherPost/post/{postId}")
    public Long delete(@PathVariable Long postId) {
        teacherPostService.deletePost(postId);

        return postId;
    }

    // 게시물 리스트 조회
    @GetMapping(value = "/v1/teacherPost/posts")
    public ResponseEntity<List<TeacherPostResponseDto>> findPosts() {
        return ResponseEntity.ok().body(teacherPostService.findPosts());
    }

    // 게시물 ID로 게시물 단건 조회
    @GetMapping(value = "/v1/teacherPost/post/{postId}")
    public ResponseEntity<TeacherPostResponseDto> findPost(@PathVariable long postId) {
        return ResponseEntity.ok().body(teacherPostService.findPost(postId));
    }
}
