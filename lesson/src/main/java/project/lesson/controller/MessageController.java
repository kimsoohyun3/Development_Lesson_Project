package project.lesson.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.lesson.dto.message.MessageDto;
import project.lesson.entity.member.Member;
import project.lesson.repository.MemberRepository;
import project.lesson.service.MessageService;
import project.lesson.service.TokenProvider;

import java.util.List;

@Api(tags = {"쪽지 관련 API"})
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @ApiOperation(value = "쪽지 보내기", notes = "쪽지 보내기")
    @ApiResponses({@ApiResponse(code = 200, message = "보낸 쪽지 정보", response = MessageDto.class)})
    @PostMapping("/v1/message")
    public ResponseEntity<MessageDto> sendMessage(@RequestHeader("Authorization") String token, @RequestBody MessageDto messageDto) {
        Member member = memberRepository.findById(tokenProvider.validateAndGetUserId(token)).orElseThrow(() -> { return new IllegalArgumentException("유저를 찾을 수 없습니다."); });
        messageDto.setSenderName(member.getNickname());

        return ResponseEntity.ok().body(messageService.write(messageDto));
    }

    @ApiOperation(value = "받은 편지함 읽기", notes = "받은 편지함 읽기")
    @ApiResponses({@ApiResponse(code = 200, message = "받은 편지함", response = MessageDto.class, responseContainer = "List")})
    @GetMapping("/v1/messages/received")
    public ResponseEntity<List<MessageDto>> getReceivedMessages(@RequestHeader("Authorization") String token) {
        Member member = memberRepository.findById(tokenProvider.validateAndGetUserId(token)).orElseThrow(() -> { return new IllegalArgumentException("유저를 찾을 수 없습니다."); });

        return ResponseEntity.ok().body(messageService.receivedMessages(member));
    }

    @ApiOperation(value = "받은 쪽지 삭제하기", notes = "받은 쪽지 삭제하기")
    @ApiResponses({@ApiResponse(code = 200, message = "받은 편지 삭제 결과", response = Object.class)})
    @DeleteMapping("/v1/message/received/{messageId}")
    public Object deleteReceivedMessage(@RequestHeader("Authorization") String token, @PathVariable Long messageId) {
        Member member = memberRepository.findById(tokenProvider.validateAndGetUserId(token)).orElseThrow(() -> { return new IllegalArgumentException("유저를 찾을 수 없습니다."); });

        return ResponseEntity.ok().body(messageService.deleteMessageByReceiver(messageId, member));
    }





    @ApiOperation(value = "보낸 편지함 읽기", notes = "보낸 편지함 읽기")
    @ApiResponses({@ApiResponse(code = 200, message = "보낸 편지함", response = MessageDto.class, responseContainer = "List")})
    @GetMapping("/v1/messages/sent")
    public List<MessageDto> getSentMessages(@RequestHeader("Authorization") String token) {
        Member member = memberRepository.findById(tokenProvider.validateAndGetUserId(token)).orElseThrow(() -> { return new IllegalArgumentException("유저를 찾을 수 없습니다."); });

        return messageService.sentMessage(member);
    }



    @ApiOperation(value = "보낸 쪽지 삭제하기", notes = "보낸 쪽지 삭제하기")
    @ApiResponses({@ApiResponse(code = 200, message = "보낸 편지 삭제 결과", response = Object.class)})
    @DeleteMapping("/v1/message/sent/{messageId}")
    public Object deleteSentMessage(@RequestHeader("Authorization") String token, @PathVariable Long messageId) {
        Member member = memberRepository.findById(tokenProvider.validateAndGetUserId(token)).orElseThrow(() -> { return new IllegalArgumentException("유저를 찾을 수 없습니다."); });

        return messageService.deleteMessageBySender(messageId, member);
    }


}