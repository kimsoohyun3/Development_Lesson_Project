package project.lesson.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.lesson.dto.notice.NoticeResponseDto;
import project.lesson.dto.notice.NoticeSaveRequestDto;
import project.lesson.dto.notice.NoticeUpdateRequestDto;
import project.lesson.entity.notice.Notice;
import project.lesson.exception.common.CResourceNotExistException;
import project.lesson.repository.NoticeRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    // 공지사항 등록
    public Long saveNotice(NoticeSaveRequestDto requestDto) {
        return noticeRepository.save(requestDto.toEntity()).getId();
    }

    // 공지사항 수정
    public Long updateNotice(Long noticeId, NoticeUpdateRequestDto requestDto) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new CResourceNotExistException());

        notice.updateNotice(requestDto.getTitle(), requestDto.getContent());

        return noticeId;
    }

    // 공지사항 삭제
    public void deleteNotice(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new CResourceNotExistException());

        noticeRepository.delete(notice);
    }

    // 공지사항 리스트 조회
    public List<NoticeResponseDto> findNotices() {
        List<Notice> entityList = noticeRepository.findAll();

        List<NoticeResponseDto> dtoList = entityList.stream()
                .map(m -> new NoticeResponseDto(m))
                .collect(Collectors.toList());

        return dtoList;
    }

    // 공지사항 ID로 공지사항 단건 조회
    public NoticeResponseDto findNotice(long noticeId) {
        Notice entity = noticeRepository.findById(noticeId).orElseThrow(() -> new CResourceNotExistException());;

        return new NoticeResponseDto(entity);
    }
}
