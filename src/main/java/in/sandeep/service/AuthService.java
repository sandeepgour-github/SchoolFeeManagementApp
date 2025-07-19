package in.sandeep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.sandeep.entity.User;
import in.sandeep.repository.UserRepository;
import in.sandeep.util.JWTUtil;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	public User registerUser(User user) {
		if(userRepo.findByEmail(user.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email already Exists");
		}
		user.setRole("ADMIN");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	public String generateToken(String email) {
		return jwtUtil.generateToken(email);
	}
}
