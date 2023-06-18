package project.lesson.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import project.lesson.dto.studentPost.StudentPostResponseDto;
import project.lesson.dto.teacherpost.MyTeacherPostResponseDto;
import project.lesson.dto.teacherpost.TeacherPostResponseDto;
import project.lesson.dto.teacherpost.TeacherPostSaveRequestDto;
import project.lesson.dto.teacherpost.TeacherPostUpdateRequestDto;
import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.entity.member.Member;
import project.lesson.repository.MemberRepository;
import project.lesson.service.TeacherPostService;
import project.lesson.service.TokenProvider;

@Api(tags = {"과외 학생 구인 관련 API"})
@RestController
@RequiredArgsConstructor
public class TeacherPostController {

	private final TeacherPostService teacherPostService;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;


    @ApiOperation(value = "게시물 등록", notes = "게시물 등록")
    @ApiResponses({@ApiResponse(code = 200, message = "등록한 게시물 PK", response = Long.class)})
    @PostMapping(value = "/v1/teacherPost")
    public Long savePost(@RequestHeader("Authorization") String token, @RequestBody TeacherPostSaveRequestDto requestDto) {
        Member member = memberRepository.findById(tokenProvider.validateAndGetUserId(token)).orElseThrow(() -> { return new IllegalArgumentException("유저를 찾을 수 없습니다."); });
        requestDto.setMember(member);

        return teacherPostService.savePost(requestDto);
    }

    @ApiOperation(value = "게시물 수정", notes = "게시물 수정")
    @ApiResponses({@ApiResponse(code = 200, message = "수정한 공지사항 PK", response = Long.class)})
    @PutMapping(value = "/v1/teacherPost/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody TeacherPostUpdateRequestDto requestDto) {
        return teacherPostService.updatePost(postId, requestDto);
    }

    @ApiOperation(value = "게시물 삭제", notes = "게시물 삭제")
    @ApiResponses({@ApiResponse(code = 200, message = "삭제한 공지사항 PK", response = Long.class)})
    @DeleteMapping("/v1/teacherPost/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        teacherPostService.deletePost(postId);

		return postId;
	}

    // 게시물 리스트 조회(검색)
    @ApiOperation(value = "게시물 리스트 조회(검색)", notes = "게시물 리스트 조회(검색)")
    @ApiResponses({@ApiResponse(code = 200, message = "게시물 리스트 조회(검색)", response = TeacherPostResponseDto.class, responseContainer = "List")})
    @GetMapping(value = "/v1/teacherPosts")
    public ResponseEntity<List<TeacherPostResponseDto>> findPosts(SearchCondition searchCondition, Pageable pageable) {
        return ResponseEntity.ok().body(teacherPostService.findPosts(searchCondition, pageable));
    }

    @ApiOperation(value = "게시물 단건 조회", notes = "게시물 단건 조회")
    @ApiResponses({@ApiResponse(code = 200, message = "게시물 단건 조회", response = TeacherPostResponseDto.class)})
    @GetMapping(value = "/v1/teacherPost/{postId}")
    public ResponseEntity<TeacherPostResponseDto> findPost(@PathVariable long postId) {
        return ResponseEntity.ok().body(teacherPostService.findPost(postId));
    }

	@GetMapping("teacherPost/myPosts/{memberId}")
	public ResponseEntity<MyTeacherPostResponseDto> findMyPosts(@PathVariable("memberId") String memberId) {
		return ResponseEntity.ok().body(teacherPostService.findMyPosts(memberId));
	}
}
