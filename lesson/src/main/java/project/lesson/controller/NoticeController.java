package project.lesson.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.lesson.dto.notice.NoticeResponseDto;
import project.lesson.dto.notice.NoticeSaveRequestDto;
import project.lesson.dto.notice.NoticeUpdateRequestDto;
import project.lesson.service.NoticeService;

import java.util.List;

@Api(tags = {"공지사항 API"})
@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 등록
    @PostMapping(value = "/v1/notice")
    public Long saveNotice(@RequestBody NoticeSaveRequestDto requestDto) {
        return noticeService.saveNotice(requestDto);
    }

    // 공지사항 수정
    @PutMapping(value = "/v1/notice/{noticeId}")
    public Long updateNotice(@PathVariable Long noticeId, @RequestBody NoticeUpdateRequestDto requestDto) {
        return noticeService.updateNotice(noticeId, requestDto);
    }

    // 공지사항 삭제
    @DeleteMapping("v1/notice/{noticeId}")
    public Long deleteNotice(@PathVariable Long noticeId) {
        noticeService.deleteNotice(noticeId);

        return noticeId;
    }

    // 공지사항 리스트 조회
    @GetMapping(value = "/v1/notices")
    public ResponseEntity<List<NoticeResponseDto>> findNotices() {
        return ResponseEntity.ok().body(noticeService.findNotices());
    }

    // 공지사항 ID로 공지사항 단건 조회
    @GetMapping(value = "/v1/notice/{noticeId}")
    public ResponseEntity<NoticeResponseDto> findNotice(@PathVariable long noticeId) {
        return ResponseEntity.ok().body(noticeService.findNotice(noticeId));
    }
}
