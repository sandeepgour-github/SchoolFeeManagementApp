package in.sandeep.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleStudentNotFound(ResourceNotFoundException ex) {
	  Map<String, Object> map = new HashMap<>();
	  map.put("message", ex.getMessage());
	  return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(value=InvalidAmountException.class)
	public ResponseEntity<Map<String,Object>> handleInvalidAmountException(InvalidAmountException ex){	
		Map<String, Object> map=new HashMap<>();
		map.put("message",ex.getMessage());
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=UserAlreadyExistException.class)
	public ResponseEntity<Map<String,Object>> handleUserAlreadyExistException(UserAlreadyExistException ex){	
		Map<String, Object> map=new HashMap<>();
		map.put("message",ex.getMessage());
		return new ResponseEntity<>(map,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value=BadCredentialsException.class)
	public ResponseEntity<Map<String,String>> handleBadCredentialsExcp(BadCredentialsException ex){	
		Map<String, String> map=new HashMap<>();
		map.put("message","Invalid email or password");
		return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	public ResponseEntity<Map<String, String>> handleMethodArgurmentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> errorsMap=new HashMap<>();
		BindingResult bResult=ex.getBindingResult();
		List<FieldError> fieldErrors = bResult.getFieldErrors();
		for(FieldError error:fieldErrors) {
			errorsMap.put(error.getField(),error.getDefaultMessage());
		}
		return new ResponseEntity<Map<String,String>>(errorsMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception ex){
		Map<String, Object> map=new HashMap<>();
		map.put("message",ex.getMessage());
		return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
