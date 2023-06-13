package project.lesson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.lesson.dto.member.MemberInfoResponseDto;
import project.lesson.dto.member.MemberSaveRequestDto;
import project.lesson.dto.member.MemberSaveResponseDto;
import project.lesson.dto.member.ModifyMemberPasswordRequestDto;
import project.lesson.entity.studentPost.StudentPost;
import project.lesson.entity.member.Member;
import project.lesson.entity.member.UserClassification;
import project.lesson.entity.teacherPost.TeacherPost;
import project.lesson.repository.MemberRepository;
import project.lesson.repository.StudentPostRepository;
import project.lesson.repository.TeacherPostRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final StudentPostRepository studentPostRepository;
	private final TeacherPostRepository teacherPostRepository;

	@Autowired
	public MemberService(
			MemberRepository memberRepository,
			StudentPostRepository studentPostRepository,
			TeacherPostRepository teacherPostRepository
	) {
		this.memberRepository = memberRepository;
		this.studentPostRepository = studentPostRepository;
		this.teacherPostRepository = teacherPostRepository;
	}

	@Transactional
	public MemberSaveResponseDto joinMember(MemberSaveRequestDto memberSaveRequestDto) {
		memberRepository.findById(memberSaveRequestDto.getId()).ifPresent(member -> {
			throw new IllegalArgumentException("이미 존재하는 ID입니다.");
		});

		return MemberSaveResponseDto
				.of(memberRepository.save(MemberSaveRequestDto.toEntity(memberSaveRequestDto)));
	}

	public MemberInfoResponseDto findMemberInfo(String memberId) {
		Member findMember = memberRepository.findById(memberId).orElseThrow(() -> {
			throw new IllegalArgumentException("존재하지 않는 ID입니다.");
		});

		UserClassification userClassification = findMember.getUserClassification();
		List<StudentPost> studentPosts = new ArrayList<>();
		List<TeacherPost> teacherPosts = new ArrayList<>();

		if (userClassification == UserClassification.STUDENT) {
			studentPosts = studentPostRepository.findByWriter(findMember);
		} else {
			teacherPosts = teacherPostRepository.findByWriter(findMember);
		}

		return MemberInfoResponseDto.of(findMember, studentPosts, teacherPosts);
	}

	@Transactional
	public String modifyMemberPassword(String memberId, ModifyMemberPasswordRequestDto modifyMemberPasswordRequestDto) {
		Member findMember = memberRepository
				.findById(memberId)
				.orElseThrow(() -> {
					throw new IllegalArgumentException("존재하지 않는 ID입니다.");
				});

		return findMember.modifyPassword(modifyMemberPasswordRequestDto.getPassword());
	}

}
