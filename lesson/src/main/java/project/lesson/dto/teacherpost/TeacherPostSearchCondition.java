package project.lesson.dto.teacherpost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import project.lesson.entity.commonEnum.SearchType;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherPostSearchCondition {

    private SearchType searchType; // 검색 타입

    private String content; // 검색 내용
}
