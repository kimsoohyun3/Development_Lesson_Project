package project.lesson.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import project.lesson.dto.authmail.AuthMailRequestDto;
import project.lesson.dto.authmail.AuthMailResponseDto;
import project.lesson.exception.ExceptionMessage;
import project.lesson.exception.authmail.AuthMailException;
import project.lesson.service.AuthMailService;

@Api(tags = "인증 관련 API")
@RestController
public class AuthController {
	private AuthMailService authMailService;

	@Autowired
	public AuthController(AuthMailService authMailService) {
		this.authMailService = authMailService;
	}

	@ApiOperation(
			value = "이메일 인증",
			notes = "이메일 인증을 진행합니다."
	)
	@ApiResponses(
			{
					@ApiResponse(code = 200, message = "메일 인증번호", response = AuthMailResponseDto.class),
					@ApiResponse(code = 400, message = "올바른 이메일 형식이 아닙니다.", response = ExceptionMessage.class)
			}
	)
	@GetMapping("/auth/mail")
	public ResponseEntity<AuthMailResponseDto> sendAuthMail(@Valid AuthMailRequestDto authMailRequestDto) {
		try {
			return ResponseEntity.ok().body(authMailService.sendAuthMail(authMailRequestDto));
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new AuthMailException();
		}
	}
}
