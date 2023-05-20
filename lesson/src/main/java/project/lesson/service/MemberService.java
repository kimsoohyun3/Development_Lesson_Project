package project.lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.lesson.dto.member.MemberSaveRequestDto;
import project.lesson.dto.member.MemberSaveResponseDto;
import project.lesson.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Transactional
	public MemberSaveResponseDto saveMember(MemberSaveRequestDto memberSaveRequestDto) {
		return MemberSaveResponseDto.of(memberRepository.save(MemberSaveRequestDto.toEntity(memberSaveRequestDto)));
	}

}