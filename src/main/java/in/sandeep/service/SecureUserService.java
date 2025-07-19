package in.sandeep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.sandeep.repository.UserRepository;

@Service
public class SecureUserService implements UserDetailsService {

	@Autowired
	private UserRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		in.sandeep.entity.User user = customerRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
		return User.withUsername(user.getPassword()).password(user.getPassword()).roles(user.getRole()).build();
	}
}
