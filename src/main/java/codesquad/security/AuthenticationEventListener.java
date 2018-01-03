package codesquad.security;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationEventListener {
    private final PrincipalExtractor principalExtractor = new FixedPrincipalExtractor();

    @Resource(name = "customUserDetailsService")
    private UserDetailsService userDetailsService;

    @EventListener
    public void handleAuthenticationSuccess(InteractiveAuthenticationSuccessEvent event) {
        if (!(event.getAuthentication() instanceof OAuth2Authentication)) {
            return;
        }

        OAuth2Authentication authentication = (OAuth2Authentication) event.getAuthentication();
        authentication.getPrincipal();
        
        
        Map<String, Object> map = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        log.debug("authentication details : {}", map);
        log.debug("authentication authorites : {}", authentication.getAuthorities());
        ;
        UserDetails userDetails = getUser(map);
    }

    private UserDetails getUser(Map<String, Object> map) {
    	
        String username = principalExtractor.extractPrincipal(map).toString();
        log.debug("loaded username : {}", username);

        UserDetails user = null;
        try {
            // userDetailsService를 활용해 DB에서 사용자를 조회
        } catch (UsernameNotFoundException e) {
            // 새로운 사용자를 등록한다.
        }
        return user;
    }
}
