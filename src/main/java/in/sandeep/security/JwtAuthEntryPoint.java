package in.sandeep.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		   response.setContentType("application/json");//MIME Type
		   response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		   PrintWriter pw=response.getWriter();
		   pw.write("{\"error\":\"Unautherized:Invalid or missing token\"}");
		
	}

}
