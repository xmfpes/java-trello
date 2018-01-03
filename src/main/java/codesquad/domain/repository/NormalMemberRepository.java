package codesquad.domain.repository;

import java.util.Optional;

import codesquad.domain.Member;
import codesquad.domain.NormalMember;

public interface NormalMemberRepository extends MemberRepository<NormalMember> {
	public Optional<Member> findByUemail(String email);
}
