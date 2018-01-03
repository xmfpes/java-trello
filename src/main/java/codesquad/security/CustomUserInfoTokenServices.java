package codesquad.security;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import codesquad.domain.SocialEnum;

public class CustomUserInfoTokenServices extends UserInfoTokenServices{
	
	public CustomUserInfoTokenServices(String userInfoEndpointUrl, String clientId, SocialEnum provider) {
		super(userInfoEndpointUrl, clientId);
		setAuthoritiesExtractor(new CustomOAuth2AuthoritiesExtractor(provider));
	}
	public static class CustomOAuth2AuthoritiesExtractor implements AuthoritiesExtractor
	{
		private SocialEnum provider;
		public CustomOAuth2AuthoritiesExtractor(SocialEnum provider) {
			this.provider = provider;
		}
		
		@Override
		public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
			return AuthorityUtils.createAuthorityList(provider.getProvider());
		}
	}
	

}
