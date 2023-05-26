package project.lesson.entity.RecruitTeacher;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.lesson.entity.Base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class RecruitTeacher extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "recruitTeacher_id")
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

    @Column(nullable = false)
    private LocalDateTime postWriteDate; // 게시글 작성일자
}
