package project.lesson.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import project.lesson.dto.authmail.AuthMailRequestDto;
import project.lesson.dto.authmail.AuthMailResponseDto;
import project.lesson.dto.signin.SignInRequestDto;
import project.lesson.dto.signin.SignInResponseDto;
import project.lesson.exception.authmail.AuthMailException;
import project.lesson.service.AuthMailService;
import project.lesson.service.AuthService;
import project.lesson.service.OAuthService;

@Api(tags = {"인증 관련 API"})
@RestController
public class AuthController {
	private AuthMailService authMailService;
	private AuthService authService;
	private OAuthService oAuthService;

	@Autowired
	public AuthController(
			AuthMailService authMailService,
			AuthService authService,
			OAuthService oAuthService
	) {
		this.authMailService = authMailService;
		this.authService = authService;
		this.oAuthService = oAuthService;
	}

	@ApiOperation(
			value = "이메일 인증",
			notes = "이메일 인증을 진행합니다."
	)
	@ApiResponses(
			{
					@ApiResponse(code = 200, message = "메일 인증번호", response = AuthMailResponseDto.class)
			}
	)
	@GetMapping("/auth/mail")
	public ResponseEntity<AuthMailResponseDto> sendAuthMail(@Valid AuthMailRequestDto authMailRequestDto) {
		try {
			return ResponseEntity.ok().body(authMailService.sendAuthMail(authMailRequestDto));
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new AuthMailException("메일 인증 실패");
		}
	}

	@ApiOperation(
			value = "로그인",
			notes = "로그인을 진행합니다."
	)
	@ApiResponses(
			{
					@ApiResponse(code = 200, message = "JWT", response = SignInResponseDto.class)
			}
	)
	@PostMapping("/auth/sign-in")
	public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
		return ResponseEntity.ok().body(authService.signIn(signInRequestDto));
	}

	@ApiOperation(
			value = "카카오 소셜 로그인",
			notes = "카카오 소셜 로그인을 진행합니다."
	)
	@ApiResponses(
			{
					@ApiResponse(code = 200, message = "ACCESS_TOKEN", response = SignInResponseDto.class)
			}
	)
	@GetMapping("/oauth/kakao/{access-token}")
	public ResponseEntity<SignInResponseDto> oauthKakao(@PathVariable(value = "access-token") String accessToken) {
		return ResponseEntity.ok().body(oAuthService.kakaoSingIn(accessToken));
	}
}
