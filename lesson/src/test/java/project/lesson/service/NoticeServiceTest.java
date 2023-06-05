package project.lesson.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import project.lesson.dto.notice.NoticeResponseDto;
import project.lesson.dto.notice.NoticeSaveRequestDto;
import project.lesson.dto.notice.NoticeUpdateRequestDto;
import project.lesson.dto.studentPost.StudentPostResponseDto;
import project.lesson.dto.studentPost.StudentPostSaveRequestDto;
import project.lesson.dto.studentPost.StudentPostUpdateRequestDto;
import project.lesson.entity.commonEnum.OnOrOff;
import project.lesson.entity.commonEnum.Subject;
import project.lesson.entity.notice.Notice;
import project.lesson.entity.studentPost.StudentPost;
import project.lesson.repository.NoticeRepository;
import project.lesson.repository.StudentPostRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NoticeServiceTest {

    @Autowired
    NoticeService noticeService;
    @Autowired
    NoticeRepository noticeRepository;

    @AfterEach
    public void cleanup() {
        noticeRepository.deleteAll();
    }

    public static NoticeSaveRequestDto dtoBuilder() {
        String saveTitle = "saveTitle";

        return NoticeSaveRequestDto.builder()
                .title(saveTitle)
                .content("내용")
                .build();
    }

    // 공지사항 등록 테스트
    @Test
    void saveNotice() {
        // given
        NoticeSaveRequestDto saveRequestDto = dtoBuilder();

        // when
        noticeService.saveNotice(saveRequestDto);

        List<Notice> noticeList = noticeRepository.findAll();

        // then
        assertEquals(noticeList.get(0).getTitle(), saveRequestDto.getTitle());
    }

    // 공지사항 수정 테스트
    @Test
    void updateNotice() {
        // given
        NoticeSaveRequestDto saveRequestDto = dtoBuilder();

        String updateTitle = "updateTitle";

        Long noticeId = noticeService.saveNotice(saveRequestDto);

        // when
        NoticeUpdateRequestDto updateRequestDto = NoticeUpdateRequestDto.builder()
                .title(updateTitle)
                .content("내용")
                .build();

        noticeService.updateNotice(noticeId, updateRequestDto);

        List<Notice> noticeList = noticeRepository.findAll();

        // then
        assertEquals(noticeList.get(0).getTitle(), updateTitle);
    }

    // 공지사항 삭제 테스트
    @Test
    void deleteNotice() {
        // given
        NoticeSaveRequestDto saveRequestDto = dtoBuilder();

        Long noticeId = noticeService.saveNotice(saveRequestDto);

        // when
        noticeService.deleteNotice(noticeId);

        List<Notice> noticeList = noticeRepository.findAll();

        // then
        assertEquals(noticeList.size(), 0);
    }

    // 게시물 리스트 조회 테스트
    @Test
    void findNotices() {
        // given
        NoticeSaveRequestDto saveRequestDto = dtoBuilder();

        NoticeSaveRequestDto saveRequestDto2 = NoticeSaveRequestDto.builder()
                .title("제목")
                .content("내용")
                .build();

        noticeService.saveNotice(saveRequestDto);
        noticeService.saveNotice(saveRequestDto2);

        // when
        List<NoticeResponseDto> noticeDtoList = noticeService.findNotices();

        // then
        assertEquals(noticeDtoList.size(), 2);
    }

    // 공지사항 ID로 공지사항 단건 조회 테스트
    @Test
    void findNotice() {
        // given
        NoticeSaveRequestDto saveRequestDto = dtoBuilder();

        Long noticeId = noticeService.saveNotice(saveRequestDto);

        // when
        List<Notice> noticeList = noticeRepository.findAll();

        // then
        assertEquals(noticeList.get(0).getTitle(), dtoBuilder().getTitle());
    }
}