package codesquad.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import codesquad.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {
	 public Optional<Member> findByUemail(String email);
}
