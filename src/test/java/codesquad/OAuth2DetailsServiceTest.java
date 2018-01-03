package codesquad;

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
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class OAuth2DetailsServiceTest {

	@Autowired
	FacebookMemberRepository facebookMemberRepository;
	
	@Autowired
	MemberRepository<Member> memberRepository;
	
//	@Autowired
//	OAuth2UserDetailsService oauth2DetailsService;
	
	@Test
	public void getSocialMemberTest() {
		FacebookMember member = new FacebookMember();
		member.setRegdate(new Date());
		MemberRole role = new MemberRole();
		role.setRoleName("BASIC");
		
		member.setRoles(Arrays.asList(role));
		member.setUemail("xmfpes@naver.com");
		member.setUid("xmfpes");
		member.setUpdatedate(new Date());
		member.setUpw("123123");
		memberRepository.save(member);
		 
		log.info("member2 " + facebookMemberRepository.findByUemail("xmfpes@naver.com"));
//		log.info("member " + oauth2DetailsService.loadUserByUsername("xmfpes@naver.com", "facebookMemberRepository"));
	}
}
