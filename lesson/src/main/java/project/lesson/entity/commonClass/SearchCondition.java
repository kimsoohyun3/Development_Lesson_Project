package project.lesson.entity.commonClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.lesson.entity.commonEnum.SearchType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {

    private SearchType searchType; // 검색 타입

    private String content; // 검색 내용
}
