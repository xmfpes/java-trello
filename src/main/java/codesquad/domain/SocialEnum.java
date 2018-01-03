package codesquad.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum SocialEnum {
	FACEBOOK("facebook", "facebookMemberRepository"),
	GITHUB("github", "githubMemberRepository");
	String provider;
	String repository;
	SocialEnum(String provider, String repository) {
		this.provider = provider;
		this.repository = repository;
	}
}
