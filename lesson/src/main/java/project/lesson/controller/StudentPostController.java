package project.lesson.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import project.lesson.dto.studentPost.StudentPostResponseDto;
import project.lesson.dto.studentPost.StudentPostSaveRequestDto;
import project.lesson.dto.studentPost.StudentPostUpdateRequestDto;
import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.service.StudentPostService;

@Api(tags = {"과외 선생님 구인 관련 API"})
@RestController
@RequiredArgsConstructor
public class StudentPostController {

    private final StudentPostService studentPostService;

    // 게시물 등록
    @PostMapping(value = "/v1/studentPost")
    public Long savePost(@RequestBody StudentPostSaveRequestDto requestDto) {
        return studentPostService.savePost(requestDto);
    }

    // 게시물 수정
    @PutMapping(value = "/v1/studentPost/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody StudentPostUpdateRequestDto requestDto) {
        return studentPostService.updatePost(postId, requestDto);
    }

    // 게시물 삭제
    @DeleteMapping("v1/studentPost/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        studentPostService.deletePost(postId);

        return postId;
    }

    // 게시물 리스트 조회(검색)
    @GetMapping(value = "/v1/studentPosts")
    public ResponseEntity<List<StudentPostResponseDto>> findPosts(SearchCondition searchCondition, Pageable pageable) {
        return ResponseEntity.ok().body(studentPostService.findPosts(searchCondition, pageable));
    }

    // 게시물 ID로 게시물 단건 조회
    @GetMapping(value = "/v1/studentPost/{postId}")
    public ResponseEntity<StudentPostResponseDto> findPost(@PathVariable long postId) {
        return ResponseEntity.ok().body(studentPostService.findPost(postId));
    }
}
