package project.lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.lesson.dto.member.MemberInfoResponseDto;
import project.lesson.dto.member.MemberSaveRequestDto;
import project.lesson.dto.member.MemberSaveResponseDto;
import project.lesson.dto.member.ModifyMemberPasswordRequestDto;
import project.lesson.entity.member.Member;
import project.lesson.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
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

		return MemberInfoResponseDto.of(findMember);
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
