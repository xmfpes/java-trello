package codesquad.domain.repository;

import java.util.Optional;

import codesquad.domain.FacebookMember;
import codesquad.domain.Member;

public interface FacebookMemberRepository extends MemberRepository<FacebookMember> {
	public Optional<Member> findByUemail(String email);
}
