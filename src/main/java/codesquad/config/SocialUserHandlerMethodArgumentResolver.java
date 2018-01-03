package codesquad.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import codesquad.annotation.SocialUser;
import codesquad.domain.Member;
import codesquad.security.OAuth2SocialUserHandler;

public class SocialUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private OAuth2SocialUserHandler oauth2SocialUserHandler;
	
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(SocialUser.class);
	}

	@Override
	public Member resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        
		@SuppressWarnings("unchecked")
		Map<String, String> map = (HashMap<String, String>) authentication.getUserAuthentication().getDetails();
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		return oauth2SocialUserHandler.loadUserByUsername(map, request.getRequestURI());
	}
	

}
