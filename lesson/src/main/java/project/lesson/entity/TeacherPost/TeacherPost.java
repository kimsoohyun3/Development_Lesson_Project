package project.lesson.entity.TeacherPost;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.lesson.entity.Base.BaseEntity;
import project.lesson.entity.member.Member;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class TeacherPost extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "recruitTeacher_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer; // 작성자

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
