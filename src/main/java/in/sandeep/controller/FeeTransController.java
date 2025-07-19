package in.sandeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.sandeep.entity.FeeTransaction;
import in.sandeep.service.FeeTransService;

@RestController
@RequestMapping("/api/fees")
@CrossOrigin
public class FeeTransController {

	@Autowired
	private FeeTransService feeService;
	
	@PostMapping("/{studentId}")
	public ResponseEntity<FeeTransaction> pay(@PathVariable String studentId,@RequestParam double amount){
		return new ResponseEntity<FeeTransaction>(feeService.pay(studentId,amount),HttpStatus.CREATED);
	}
	
	@GetMapping("/history/{studentId}")
	public ResponseEntity<List<FeeTransaction>> history(@PathVariable String studentId){
		return ResponseEntity.ok(feeService.history(studentId));		
	}

}
