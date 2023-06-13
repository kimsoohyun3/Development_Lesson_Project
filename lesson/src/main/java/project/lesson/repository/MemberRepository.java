package project.lesson.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.lesson.entity.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	Optional<Member> findById(String id);

	Member findByNickname(String nickname);
}
