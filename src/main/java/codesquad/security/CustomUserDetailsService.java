package codesquad.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import codesquad.domain.repository.NormalMemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	

	@Autowired
	NormalMemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return
				memberRepository.findByUemail(email)
				.filter(m -> m!= null)
				.map(m -> new SecurityMember(m)).get();
	}

}
