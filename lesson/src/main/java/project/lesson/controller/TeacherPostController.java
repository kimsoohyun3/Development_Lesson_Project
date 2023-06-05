package project.lesson.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.lesson.dto.teacherPost.*;
import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.service.TeacherPostService;

import java.util.List;

@Api(tags = {"과외 학생 구인 관련 API"})
@RestController
@RequiredArgsConstructor
public class TeacherPostController {

	private final TeacherPostService teacherPostService;


    // 게시물 등록
    @PostMapping(value = "/v1/teacherPost")
    public Long savePost(@RequestBody TeacherPostSaveRequestDto requestDto) {
        return teacherPostService.savePost(requestDto);
    }

    // 게시물 수정
    @PutMapping(value = "/v1/teacherPost/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody TeacherPostUpdateRequestDto requestDto) {
        return teacherPostService.updatePost(postId, requestDto);
    }

    // 게시물 삭제
    @DeleteMapping("v1/teacherPost/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        teacherPostService.deletePost(postId);

		return postId;
	}

    // 게시물 리스트 조회(검색)
    @GetMapping(value = "/v1/teacherPosts")
    public ResponseEntity<List<TeacherPostResponseDto>> findPosts(SearchCondition searchCondition, Pageable pageable) {
        return ResponseEntity.ok().body(teacherPostService.findPosts(searchCondition, pageable));
    }

    // 게시물 ID로 게시물 단건 조회
    @GetMapping(value = "/v1/teacherPost/{postId}")
    public ResponseEntity<TeacherPostResponseDto> findPost(@PathVariable long postId) {
        return ResponseEntity.ok().body(teacherPostService.findPost(postId));
    }

	@GetMapping("teacherPost/myPosts/{memberId}")
	public ResponseEntity<MyTeacherPostResponseDto> findMyPosts(@PathVariable("memberId") String memberId) {
		return ResponseEntity.ok().body(teacherPostService.findMyPosts(memberId));
	}
}
