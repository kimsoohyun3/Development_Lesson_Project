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
import project.lesson.dto.notice.NoticeResponseDto;
import project.lesson.dto.studentPost.StudentPostResponseDto;
import project.lesson.dto.studentPost.StudentPostSaveRequestDto;
import project.lesson.dto.studentPost.StudentPostUpdateRequestDto;
import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.entity.member.Member;
import project.lesson.repository.MemberRepository;
import project.lesson.service.StudentPostService;
import project.lesson.service.TokenProvider;

@Api(tags = {"과외 선생님 구인 관련 API"})
@RestController
@RequiredArgsConstructor
public class StudentPostController {

    private final StudentPostService studentPostService;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @ApiOperation(value = "게시물 등록", notes = "게시물 등록")
    @ApiResponses({@ApiResponse(code = 200, message = "등록한 게시물 PK", response = Long.class)})
    @PostMapping(value = "/v1/studentPost")
    public Long savePost(@RequestHeader("Authorization") String token, @RequestBody StudentPostSaveRequestDto requestDto) {
        Member member = memberRepository.findById(tokenProvider.validateAndGetUserId(token.substring(7))).orElseThrow(() -> { return new IllegalArgumentException("유저를 찾을 수 없습니다."); });
        requestDto.setMember(member);

        return studentPostService.savePost(requestDto);
    }

    @ApiOperation(value = "게시물 수정", notes = "게시물 수정")
    @ApiResponses({@ApiResponse(code = 200, message = "수정한 공지사항 PK", response = Long.class)})
    @PutMapping(value = "/v1/studentPost/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody StudentPostUpdateRequestDto requestDto) {
        return studentPostService.updatePost(postId, requestDto);
    }

    @ApiOperation(value = "게시물 삭제", notes = "게시물 삭제")
    @ApiResponses({@ApiResponse(code = 200, message = "삭제한 공지사항 PK", response = Long.class)})
    @DeleteMapping("v1/studentPost/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        studentPostService.deletePost(postId);

        return postId;
    }

    @ApiOperation(value = "게시물 리스트 조회(검색)", notes = "게시물 리스트 조회(검색)")
    @ApiResponses({@ApiResponse(code = 200, message = "게시물 리스트 조회(검색)", response = StudentPostResponseDto.class, responseContainer = "List")})
    @GetMapping(value = "/v1/studentPosts")
    public ResponseEntity<List<StudentPostResponseDto>> findPosts(SearchCondition searchCondition, Pageable pageable) {
        return ResponseEntity.ok().body(studentPostService.findPosts(searchCondition, pageable));
    }

    @ApiOperation(value = "게시물 단건 조회", notes = "게시물 단건 조회")
    @ApiResponses({@ApiResponse(code = 200, message = "게시물 단건 조회", response = StudentPostResponseDto.class)})
    @GetMapping(value = "/v1/studentPost/{postId}")
    public ResponseEntity<StudentPostResponseDto> findPost(@PathVariable long postId) {
        return ResponseEntity.ok().body(studentPostService.findPost(postId));
    }
}
