package project.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.lesson.dto.RecruitTeacher.RecruitTeacherDto;
import project.lesson.model.response.ListResult;
import project.lesson.model.response.SingleResult;
import project.lesson.service.RecruitTeacherService;
import project.lesson.service.ResponseService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/RecruitTeacher")
public class RecruitTeacherController {

    private final RecruitTeacherService recruitTeacherService;
    private final ResponseService responseService;

    // 게시물 리스트 조회
    @GetMapping(value = "/posts")
    public ListResult<RecruitTeacherDto> posts() {
        return responseService.getListResult(recruitTeacherService.findPosts());
    }

    // 게시물 ID로 게시물 단건 조회
    @GetMapping(value = "/post/{postId}")
    public SingleResult<RecruitTeacherDto> post(@PathVariable long postId) {
        return responseService.getSingleResult(recruitTeacherService.findPost(postId));
    }
}
