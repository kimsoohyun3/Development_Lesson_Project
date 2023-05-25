package project.lesson.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import project.lesson.dto.member.MemberSaveRequestDto;
import project.lesson.dto.member.MemberSaveResponseDto;
import project.lesson.service.MemberService;

@Api(tags = {"회원 관련 API"})
@RestController
public class MemberController {
	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
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
}
