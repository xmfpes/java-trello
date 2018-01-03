package codesquad.security;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import codesquad.domain.FacebookMember;
import codesquad.domain.GithubMember;
import codesquad.domain.Member;
import codesquad.domain.MemberBuilder;
import codesquad.domain.repository.MemberRepository;
import codesquad.domain.repository.MemberRoleRepository;
import lombok.Getter;
import lombok.extern.java.Log;

@Service
@Log
public class OAuth2SocialUserHandler <T extends Member> {

	@Getter
	private enum SocialEnum {
		FACEBOOK("/oauth2/facebook", "facebookMemberRepository", "id", new FacebookSocialStrategy()), 
		GITHUB("/oauth2/github", "githubMemberRepository", "id",new GithubSocialStrategy()),
		EMPTY("no", "no", "no", new FacebookSocialStrategy());
		private String socialRequestUrl;
		private String socialRepositoryBeanName;
		private String socialUniqueType;
		private SocialStrategy socialStrategy;

		private SocialEnum(String url, String repository, String socialUniqueKey, SocialStrategy socialStrategy) {
			this.socialRequestUrl = url;
			this.socialRepositoryBeanName = repository;
			this.socialUniqueType = socialUniqueKey;
			this.socialStrategy = socialStrategy;
		}

		public static SocialEnum findSocialEnum(String url) {
			return Arrays.stream(SocialEnum.values()).filter(social -> SocialEnum.hasSocialRequestUrl(url)).findAny()
					.orElse(EMPTY);
		}

		public static boolean hasSocialRequestUrl(String url) {
			return Arrays.stream(SocialEnum.values()).anyMatch(social -> social.getSocialRequestUrl().equals(url));
		}

	}

	@Autowired
	Map<String, MemberRepository<T>> socialRepositories;

	@Autowired
	MemberRoleRepository roleRepo;
	private static MemberRoleRepository reporepo;

	@PostConstruct
	private void initStaticDao() {
		reporepo = this.roleRepo;
	}

	public Member loadUserByUsername(Map<String, String> map, String socialRequestType)
			throws UsernameNotFoundException {
		log.info("socialRequestType " + socialRequestType);
		SocialEnum socialEum = SocialEnum.findSocialEnum(socialRequestType);
		log.info("hello1 " + socialEum);

		MemberRepository<T> memberRepository = socialRepositories.get(socialEum.getSocialRepositoryBeanName());

		String socialUniqueKey = map.get(socialEum.getSocialUniqueType());
		Optional<Member> member = memberRepository.findByUemail(socialUniqueKey);
		if (member.isPresent())
			return member.get();
		return socialEum.getSocialStrategy().saveSocialMember(map, socialUniqueKey, memberRepository);

	}

	private interface SocialStrategy <T extends Member> {
		Member saveSocialMember(Map<String, String> map, String socialUniqueKey, MemberRepository<T> repo);
	}

	private static class FacebookSocialStrategy implements SocialStrategy<FacebookMember> {

		@Override
		public Member saveSocialMember(Map<String, String> map, String socialUniqueKey, 
				MemberRepository<FacebookMember> repo) {
			FacebookMember member = new FacebookMember();
			log.info("repo " + reporepo);
			member.setRegdate(new Date());
			member.setRoles(Arrays.asList(reporepo.findByRoleName("BASIC")));
			member.setUemail(socialUniqueKey);
			member.setUid(socialUniqueKey);
			member.setUpdatedate(new Date());
			member.setUpw("");
			
			repo.save(member);
			return member;
		}

	}

	private static class GithubSocialStrategy implements SocialStrategy<GithubMember> {

		@Override
		public Member saveSocialMember(Map<String, String> map, String socialUniqueKey,
				MemberRepository<GithubMember> repo) {
			MemberBuilder builder = new MemberBuilder();
			return (GithubMember) builder.setUid(socialUniqueKey).setUpw("").setUemail(socialUniqueKey)
					.setUpdatedate(new Date()).setUpdatedate(new Date()).build();
		}

	}

}
