package project.lesson.entity.Base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @ApiModelProperty(example = "생성일자", value = "생성일자")
    @CreatedDate
    private LocalDateTime createdDate; // 최초 생성일자

    @ApiModelProperty(example = "수정일자", value = "수정일자")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate; // 마지막 수정일자
}
