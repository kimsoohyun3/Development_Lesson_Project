package project.lesson.entity.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.lesson.entity.base.BaseEntity;
import project.lesson.entity.commonEnum.OnOrOff;
import project.lesson.entity.commonEnum.Subject;
import project.lesson.entity.member.Member;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String content; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private Member member; // 회원

    @Builder
    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateNotice(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
