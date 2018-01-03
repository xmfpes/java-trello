package codesquad;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import codesquad.domain.FacebookMember;
import codesquad.domain.Member;
import codesquad.domain.MemberRole;
import codesquad.domain.repository.FacebookMemberRepository;
import codesquad.domain.repository.MemberRepository;
import codesquad.domain.repository.NormalMemberRepository;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class InheritanceDomainTest {
	
	@Autowired
	MemberRepository<Member> memberRepository;
	
	@Autowired
	FacebookMemberRepository facebookMemberRepository;
	
	@Autowired
	NormalMemberRepository normalMemberRepository;
	
	@Test
	public void create() {
		FacebookMember member = new FacebookMember();
		member.setRegdate(new Date());
		MemberRole role = new MemberRole();
		role.setRoleName("BASIC");
		
		member.setRoles(Arrays.asList(role));
		member.setUemail("xmfpes@naver.com");
		member.setUid("xmfpes");
		member.setUpdatedate(new Date());
		member.setUpw("123123");
		
		Member normalMember = new Member();
		normalMember.setRegdate(new Date());
		role = new MemberRole();
		role.setRoleName("BASIC");
		normalMember.setRoles(Arrays.asList(role));
		normalMember.setUemail("xmfpes@naver.com");
		normalMember.setUid("hello");
		normalMember.setUpdatedate(new Date());
		normalMember.setUpw("123123");
		memberRepository.save(member);
		memberRepository.save(normalMember);
		assertEquals("xmfpes", facebookMemberRepository.findByUemail("xmfpes@naver.com").get().getUid());
		
		
		
	}
}
