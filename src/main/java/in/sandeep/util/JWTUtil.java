package in.sandeep.util;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws; 
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	private SecretKey getSigningKey() {
		SecretKey key=Keys.hmacShaKeyFor(secret.getBytes());//ye method secret key ko bytes ke array me convert karega then secretKey ke object me rappup kr dega
		return key;
	}
	
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))  //token ka issue time
				.setExpiration(new Date(System.currentTimeMillis()+60*60*1000)) //1 hour 
				.signWith(getSigningKey()) //signwith method signature genereate then header,claims and signature ko combine karke token produce karegi
				.compact();//token to string me convert karna
	}
	
	public Claims extractClaims(String token) {
		JwtParser parser=Jwts.parserBuilder().setSigningKey(getSigningKey()).build();//parser ke pass wo object hain jo token se claims nikal kr dega
		Jws<Claims>jwsClaims=parser.parseClaimsJws(token); // actual token valid done here
		return jwsClaims.getBody();
	}
	
	public String extractEmail(String token) {
		Claims claims=extractClaims(token);
		return claims.getSubject();
	}
	
	public boolean isTokenExpired(String token) {
		Claims claims=extractClaims(token);
		return claims.getExpiration().before(new Date());
	}
	
	public boolean validateToken(String token,String email) {
		return extractEmail(token).equals(email) && !isTokenExpired(token);
	}
}
