package project.lesson.entity.message;

import lombok.*;
import project.lesson.entity.member.Member;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @Column(nullable = false)
    private boolean deletedBySender;

    @Column(nullable = false)
    private boolean deletedByReceiver;

    public Message(String title, String content, Member sender, Member receiver, boolean deletedBySender, boolean deletedByReceiver) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.deletedBySender = deletedBySender;
        this.deletedByReceiver = deletedByReceiver;
    }

    public void deleteBySender() {
        this.deletedBySender = true;
    }

    public void deleteByReceiver() {
        this.deletedByReceiver = true;
    }

    public boolean isDeleted() {
        return isDeletedBySender() && isDeletedByReceiver();
    }
}