package in.sandeep.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sandeep.entity.User;
import in.sandeep.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authMang;
	
	@PostMapping("/register")
	public ResponseEntity<User> regiter(@RequestBody User user){
		return new ResponseEntity<User>(authService.registerUser(user),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map){
		String email=map.get("email");
		String password=map.get("password");
		authMang.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		
		String token=authService.generateToken(email);
		return new ResponseEntity(Map.of("token",token),HttpStatus.OK);
	}
	
}



