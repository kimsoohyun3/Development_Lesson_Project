package project.lesson.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import project.lesson.dto.member.MemberInfoResponseDto;
import project.lesson.dto.member.MemberSaveRequestDto;
import project.lesson.dto.member.MemberSaveResponseDto;
import project.lesson.dto.member.ModifyMemberPasswordRequestDto;
import project.lesson.service.MemberService;
import project.lesson.service.TokenProvider;

@Api(tags = {"회원 관련 API"})
@RestController
public class MemberController {
	private final MemberService memberService;
	private final TokenProvider tokenProvider;

	@Autowired
	public MemberController(MemberService memberService, TokenProvider tokenProvider) {
		this.memberService = memberService;
		this.tokenProvider = tokenProvider;
	}

	@ApiOperation(
			value = "회원가입",
			notes = "회원가입을 진행합니다."
	)
	@ApiResponses(
			{
					@ApiResponse(code = 200, message = "회원가입", response = MemberSaveResponseDto.class)
			}
	)
	@PostMapping("/member/join")
	public ResponseEntity<MemberSaveResponseDto> joinMember(
			@RequestBody @Valid MemberSaveRequestDto memberSaveRequestDto
	) {
		return ResponseEntity.ok().body(memberService.joinMember(memberSaveRequestDto));
	}

	@ApiOperation(
			value = "내정보 불러오기",
			notes = "내정보를 불러옵니다."
	)
	@ApiResponses(
			{
					@ApiResponse(code = 200, message = "내정보", response = MemberInfoResponseDto.class)
			}
	)
	@GetMapping("/member/info")
	public ResponseEntity<MemberInfoResponseDto> findMemberInfo(
			@RequestHeader("Authorization") String token) {
		return ResponseEntity.ok()
				.body(memberService.findMemberInfo(tokenProvider.validateAndGetUserId(token.substring(7))));
	}

	@ApiOperation(
			value = "아이디로 내정보 불러오기",
			notes = "아이디로 내정보를 불러옵니다."
	)
	@ApiResponses(
			{
					@ApiResponse(code = 200, message = "내정보", response = MemberInfoResponseDto.class)
			}
	)
	@GetMapping("/member/info/{memberId}")
	public ResponseEntity<MemberInfoResponseDto> findMemberInfoByMemberId(
			@PathVariable String memberId) {
		return ResponseEntity.ok()
				.body(memberService.findMemberInfo(memberId));
	}

	@ApiOperation(
			value = "비밀번호 변경",
			notes = "비밀번호를 변경합니다."
	)
	@ApiResponses(
			{
					@ApiResponse(code = 200, message = "변경된 비밀번호", response = String.class)
			}
	)
	@PutMapping("member/modify-password")
	public String modifyMemberPassword(
			@RequestHeader("Authorization") String token,
			@RequestBody @Valid ModifyMemberPasswordRequestDto modifyMemberPasswordRequestDto
	) {
		return memberService.modifyMemberPassword(tokenProvider.validateAndGetUserId(token.substring(7)),
				modifyMemberPasswordRequestDto);
	}

}
