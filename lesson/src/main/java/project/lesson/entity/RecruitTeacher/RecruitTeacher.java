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

    private String subject; // 과목

    private String residence; // 거주지

    private String onOrOff; // 과외 온 / 오프 여부

    private String title; // 제목

    private String content; // 내용

    private LocalDateTime postWriteDate; // 게시글 작성일자
}
