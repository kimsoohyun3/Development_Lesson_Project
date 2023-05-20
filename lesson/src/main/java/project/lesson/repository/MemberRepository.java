package project.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.lesson.entity.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

}
