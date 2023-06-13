package project.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.lesson.entity.member.Member;
import project.lesson.entity.message.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByReceiver(Member member);

    List<Message> findAllBySender(Member member);
}
