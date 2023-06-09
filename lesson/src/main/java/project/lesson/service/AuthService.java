package project.lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.lesson.dto.signin.SignInRequestDto;
import project.lesson.dto.signin.SignInResponseDto;
import project.lesson.entity.member.Member;
import project.lesson.repository.MemberRepository;

@Service
public class AuthService {
	private final MemberRepository memberRepository;
	private final TokenProvider tokenProvider;

	@Autowired
	public AuthService(MemberRepository memberRepository, TokenProvider tokenProvider) {
		this.memberRepository = memberRepository;
		this.tokenProvider = tokenProvider;
	}

	public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
		Member findMember = memberRepository.findById(signInRequestDto.getId())
				.orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

		if (!(findMember.getPassword().equals(signInRequestDto.getPassword()))) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		return new SignInResponseDto(tokenProvider.create(findMember));
	}
}
