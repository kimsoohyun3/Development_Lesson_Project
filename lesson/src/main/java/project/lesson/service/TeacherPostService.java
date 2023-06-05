package project.lesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.lesson.dto.teacherPost.*;
import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.entity.teacherPost.TeacherPost;
import project.lesson.entity.member.Member;
import project.lesson.exception.common.CResourceNotExistException;
import project.lesson.repository.MemberRepository;
import project.lesson.repository.TeacherPostRepository;
import project.lesson.repository.TeacherPostRepositoryImpl;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherPostService {

	private final TeacherPostRepository teacherPostRepository;
	private final TeacherPostRepositoryImpl teacherPostRepositoryImpl;
	private final MemberRepository memberRepository;

	// 게시물 등록
	public Long savePost(TeacherPostSaveRequestDto requestDto) {
		return teacherPostRepository.save(requestDto.toEntity()).getId();
	}

	// 게시물 수정
	public Long updatePost(Long postId, TeacherPostUpdateRequestDto requestDto) {
		TeacherPost teacherPost = teacherPostRepository.findById(postId)
				.orElseThrow(() -> new CResourceNotExistException());

		teacherPost.updatePost(requestDto.getTitle(), requestDto.getContent(), requestDto.getSubject(),
				requestDto.getArea(), requestDto.getOnOrOff());

		return postId;
	}

	// 게시물 삭제
	public void deletePost(Long postId) {
		TeacherPost teacherPost = teacherPostRepository.findById(postId)
				.orElseThrow(() -> new CResourceNotExistException());

		teacherPostRepository.delete(teacherPost);
	}

	// 게시물 리스트 조회(검색)
	public List<TeacherPostResponseDto> findPosts(SearchCondition searchCondition, Pageable pageable) {
		Page<TeacherPost> entityList = teacherPostRepositoryImpl.searchPage(searchCondition, pageable);

		List<TeacherPostResponseDto> dtoList = entityList.stream()
				.map(m -> new TeacherPostResponseDto(m))
				.collect(Collectors.toList());

		return dtoList;
	}

	// 게시물 ID로 게시물 단건 조회
	public TeacherPostResponseDto findPost(long postId) {
		TeacherPost entity = teacherPostRepository.findById(postId).orElseThrow(() -> new CResourceNotExistException());
		;

		return new TeacherPostResponseDto(entity);
	}

	public MyTeacherPostResponseDto findMyPosts(String memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다."));

		List<TeacherPost> myPosts = teacherPostRepository.findByMember(member);

		return new MyTeacherPostResponseDto(myPosts);
	}
}
