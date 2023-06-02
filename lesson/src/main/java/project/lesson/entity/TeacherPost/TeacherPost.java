package project.lesson.entity.TeacherPost;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.lesson.entity.Base.BaseEntity;
import project.lesson.entity.commonEnum.OnOrOff;
import project.lesson.entity.commonEnum.Subject;
import project.lesson.entity.member.Member;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class TeacherPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacherPost_id")
    private Long id;

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String content; // 내용

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Subject subject; // 과목

    @Column(nullable = false)
    private String area; // 지역

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OnOrOff onOrOff; // 과외 온 / 오프 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private Member member; // 회원

    @Builder
    public TeacherPost(String title, String content, Subject subject, String area, OnOrOff onOrOff) {
        this.title = title;
        this.content = content;
        this.subject = subject;
        this.area = area;
        this.onOrOff = onOrOff;
    }

    public void updatePost(String title, String content, Subject subject, String area, OnOrOff onOrOff) {
        this.title = title;
        this.content = content;
        this.subject = subject;
        this.area = area;
        this.onOrOff = onOrOff;
    }
}
