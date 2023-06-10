package project.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.lesson.entity.notice.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
