package project.lesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.lesson.dto.message.MessageDto;
import project.lesson.entity.member.Member;
import project.lesson.entity.message.Message;
import project.lesson.exception.common.CResourceNotExistException;
import project.lesson.repository.MemberRepository;
import project.lesson.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    // 쪽지 보내기
    public MessageDto write(MessageDto messageDto) {
        Member receiver = memberRepository.findByNickname(messageDto.getReceiverNickname());
        Member sender = memberRepository.findByNickname(messageDto.getSenderNickname());

        Message message = new Message(messageDto.getTitle(), messageDto.getContent(), sender, receiver, false, false);
        message = messageRepository.save(message);

        return new MessageDto(message);
    }

    // 받은 편지함 읽기
    @Transactional(readOnly = true)
    public List<MessageDto> receivedMessages(Member member) {
        List<Message> messageList = messageRepository.findAllByReceiver(member);
        List<MessageDto> messageDtoList = new ArrayList<>();

        for (Message message : messageList) {
            // 삭제하지 않은 받은 편지만 리스트에 담기
            if (!message.isDeletedByReceiver()) {
                messageDtoList.add(new MessageDto(message));
            }
        }
        return messageDtoList;
    }

    // 받은 편지 삭제
    @Transactional
    public Object deleteMessageByReceiver(Long messageId, Member member) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> { return new CResourceNotExistException(); });

        if (member == message.getReceiver()) {
            // 받은 사람의 편지만 삭제
            message.deleteByReceiver();

            // 받은 사람과 보낸 사람 모두 삭제했으면, 데이터베이스에서 삭제
            if (message.isDeleted()) {
                messageRepository.delete(message);

                return "받은 사람과 보낸 사람 모두 편지 삭제";
            }

            return "받은 사람의 편지만 삭제";
        } else {
            return new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }
    }

    // 보낸 편지함 읽기
    @Transactional(readOnly = true)
    public List<MessageDto> sentMessage(Member member) {
        List<Message> messages = messageRepository.findAllBySender(member);
        List<MessageDto> messageDtos = new ArrayList<>();

        for(Message message : messages) {
            // 삭제하지 않은 보낸 편지만 리스트에 담기
            if(!message.isDeletedBySender()) {
                messageDtos.add(new MessageDto(message));
            }
        }

        return messageDtos;
    }


    // 보낸 편지 삭제
    @Transactional
    public Object deleteMessageBySender(Long id, Member member) {
        Message message = messageRepository.findById(id).orElseThrow(() -> { return new IllegalArgumentException("메시지를 찾을 수 없습니다."); });

        if(member == message.getSender()) {
            // 보낸 사람의 편지만 삭제
            message.deleteBySender();

            if (message.isDeleted()) {
                // 보낸 사람과 받은 사람 모두 삭제했으면, 데이터베이스에서 삭제
                messageRepository.delete(message);

                return "보낸 사람과 받은 사람 모두 편지 삭제";
            }
            return "보낸 사람의 편지만 삭제";
        } else {
            return new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }
    }
}
