package in.sandeep.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sandeep.entity.Student;
import in.sandeep.service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

	@Autowired
	public StudentService sService;

	@PostMapping("/register")
	public ResponseEntity<Student> register(@Valid @RequestBody Student student) {
		return new ResponseEntity<Student>(sService.register(student), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> findById(@PathVariable String id) {
		return ResponseEntity.ok(sService.findByStudentId(id));
	}

	@GetMapping("/search/{input}")
	public ResponseEntity<List<Student>> findByName(@PathVariable String input) {

		if (input.startsWith("STD")) {
			List<Student> students = List.of(sService.findByStudentId(input));
			return ResponseEntity.ok(students);
		} else if (input.charAt(0) >= '1' && input.charAt(0) <= '9') {
			return ResponseEntity.ok(sService.findByMobileNumber(input));
		} else
			return ResponseEntity.ok(sService.findByName(input));
	}

	@GetMapping("/search")
	public ResponseEntity<List<Student>> findAllstudnts() {
		return ResponseEntity.ok(sService.findAllStudents());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable String id){
		sService.deleteStudent(id);
		return ResponseEntity.ok(Map.of("message","Student deleted successfully"));
	}
}
