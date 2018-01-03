package codesquad.domain.repository;

import java.util.Optional;

import codesquad.domain.GithubMember;
import codesquad.domain.Member;

public interface GithubMemberRepository extends MemberRepository<GithubMember> {
	public Optional<Member> findByUemail(String email);
}
