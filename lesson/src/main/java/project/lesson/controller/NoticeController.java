package project.lesson.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.lesson.dto.message.MessageDto;
import project.lesson.dto.notice.NoticeResponseDto;
import project.lesson.dto.notice.NoticeSaveRequestDto;
import project.lesson.dto.notice.NoticeUpdateRequestDto;
import project.lesson.service.NoticeService;

import java.util.List;

@Api(tags = {"공지사항 관련 API"})
@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @ApiOperation(value = "공지사항 등록", notes = "공지사항 등록")
    @ApiResponses({@ApiResponse(code = 200, message = "등록한 공지사항 PK", response = Long.class)})
    @PostMapping(value = "/v1/notice")
    public Long saveNotice(@RequestBody NoticeSaveRequestDto requestDto) {
        return noticeService.saveNotice(requestDto);
    }

    @ApiOperation(value = "공지사항 수정", notes = "공지사항 수정")
    @ApiResponses({@ApiResponse(code = 200, message = "수정한 공지사항 PK", response = Long.class)})
    @PutMapping(value = "/v1/notice/{noticeId}")
    public Long updateNotice(@PathVariable Long noticeId, @RequestBody NoticeUpdateRequestDto requestDto) {
        return noticeService.updateNotice(noticeId, requestDto);
    }

    @ApiOperation(value = "공지사항 삭제", notes = "공지사항 삭제")
    @ApiResponses({@ApiResponse(code = 200, message = "삭제한 공지사항 PK", response = Long.class)})
    @DeleteMapping("v1/notice/{noticeId}")
    public Long deleteNotice(@PathVariable Long noticeId) {
        noticeService.deleteNotice(noticeId);

        return noticeId;
    }

    @ApiOperation(value = "공지사항 리스트 조회", notes = "공지사항 리스트 조회")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 리스트 조회", response = NoticeResponseDto.class, responseContainer = "List")})
    @GetMapping(value = "/v1/notices")
    public ResponseEntity<List<NoticeResponseDto>> findNotices() {
        return ResponseEntity.ok().body(noticeService.findNotices());
    }

    @ApiOperation(value = "공지사항 단건 조회", notes = "공지사항 단건 조회")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 단건 조회", response = NoticeResponseDto.class)})
    @GetMapping(value = "/v1/notice/{noticeId}")
    public ResponseEntity<NoticeResponseDto> findNotice(@PathVariable long noticeId) {
        return ResponseEntity.ok().body(noticeService.findNotice(noticeId));
    }
}
