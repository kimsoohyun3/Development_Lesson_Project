package project.lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public MemberSaveResponseDto joinMember(MemberSaveRequestDto memberSaveRequestDto) {
		memberRepository.findById(memberSaveRequestDto.getId()).ifPresent(member -> {
			throw new IllegalArgumentException("이미 존재하는 ID입니다.");
		});

		return MemberSaveResponseDto
				.of(memberRepository.save(MemberSaveRequestDto.toEntity(memberSaveRequestDto)));
	}

}
