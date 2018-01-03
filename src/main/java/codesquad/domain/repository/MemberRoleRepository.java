package codesquad.domain.repository;

import org.springframework.data.repository.CrudRepository;

import codesquad.domain.MemberRole;

public interface MemberRoleRepository extends CrudRepository<MemberRole, Long> {
	MemberRole findByRoleName(String roleName);
}
