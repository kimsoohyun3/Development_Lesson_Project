package project.lesson.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.lesson.entity.commonClass.SearchCondition;
import project.lesson.entity.commonEnum.SearchType;
import project.lesson.entity.commonEnum.Subject;
import project.lesson.entity.teacherPost.TeacherPost;

import javax.persistence.EntityManager;
import java.util.List;

import static project.lesson.entity.teacherPost.QTeacherPost.teacherPost;

public class TeacherPostRepositoryImpl implements TeacherPostCustomRepository{

    private final JPAQueryFactory queryFactory;

    public TeacherPostRepositoryImpl(EntityManager em) {this.queryFactory = new JPAQueryFactory(em);}

    @Override
    public Page<TeacherPost> searchPage(SearchCondition searchCondition, Pageable pageable) {
        List<TeacherPost> content = queryFactory
                .selectFrom(teacherPost)
                .where(isSearchable(searchCondition.getSearchType(), searchCondition.getContent()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(teacherPost.createdDate.desc())
                .fetch();

        Long count = queryFactory
                .select(teacherPost.count())
                .from(teacherPost)
                .where(isSearchable(searchCondition.getSearchType(), searchCondition.getContent()))
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    private BooleanExpression titleCt(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return teacherPost.title.contains(content);
    }
    private BooleanExpression contentCt(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return teacherPost.content.contains(content);
    }
    private BooleanExpression subjectEq(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return teacherPost.subject.eq(Subject.valueOf(content));
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
