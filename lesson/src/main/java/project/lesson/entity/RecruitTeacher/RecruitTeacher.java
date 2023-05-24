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

    private String title; // 제목

    private String content; // 내용

    private LocalDateTime postWriteDate; // 게시글 작성일자
}
