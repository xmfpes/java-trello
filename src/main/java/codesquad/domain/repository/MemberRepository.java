package codesquad.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import codesquad.domain.Member;

public interface MemberRepository <T extends Member> extends CrudRepository<T, Long> {
	public Optional<Member> findByUemail(String email);	
}
