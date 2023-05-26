package project.lesson.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.lesson.dto.RecruitTeacher.RecruitTeacherResponseDto;
import project.lesson.service.RecruitTeacherService;

import java.util.List;

@Api(tags = {"과외 선생님 구인 관련 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/RecruitTeacher")
public class RecruitTeacherController {

    private final RecruitTeacherService recruitTeacherService;

    // 게시물 리스트 조회
    @GetMapping(value = "/posts")
    public ResponseEntity<List<RecruitTeacherResponseDto>> posts() {
        return ResponseEntity.ok().body(recruitTeacherService.findPosts());
    }

    // 게시물 ID로 게시물 단건 조회
    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<RecruitTeacherResponseDto> post(@PathVariable long postId) {
        return ResponseEntity.ok().body(recruitTeacherService.findById(postId));
    }
}
