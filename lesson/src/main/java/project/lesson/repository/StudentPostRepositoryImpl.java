package project.lesson.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.entity.commonEnum.SearchType;
import project.lesson.entity.commonEnum.Subject;
import project.lesson.entity.studentPost.StudentPost;

import static project.lesson.entity.studentPost.QStudentPost.studentPost;

public class StudentPostRepositoryImpl implements StudentPostCustomRepository{

    private final JPAQueryFactory queryFactory;

    public StudentPostRepositoryImpl(EntityManager em) {this.queryFactory = new JPAQueryFactory(em);}

    @Override
    public Page<StudentPost> searchPage(SearchCondition searchCondition, Pageable pageable) {
        List<StudentPost> content = queryFactory
                .selectFrom(studentPost)
                .where(isSearchable(searchCondition.getSearchType(), searchCondition.getContent()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(studentPost.createdDate.desc())
                .fetch();

        Long count = queryFactory
                .select(studentPost.count())
                .from(studentPost)
                .where(isSearchable(searchCondition.getSearchType(), searchCondition.getContent()))
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    private BooleanExpression titleCt(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return studentPost.title.contains(content);
    }
    private BooleanExpression contentCt(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return studentPost.content.contains(content);
    }
    private BooleanExpression subjectEq(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return studentPost.subject.eq(Subject.valueOf(content));
    }

    private BooleanExpression isSearchable(SearchType searchType, String content) {
        if (searchType == SearchType.TITLE) {
            return titleCt(content);
        }
        else if(searchType == SearchType.TITLECONTENT) {
            return titleCt(content).or(contentCt(content));
        }
        else {
            return subjectEq(content);
        }
    }
}
