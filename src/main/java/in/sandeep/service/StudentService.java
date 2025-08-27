package in.sandeep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sandeep.entity.Student;
import in.sandeep.exception.ResourceNotFoundException;
import in.sandeep.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Student register(Student s) {
		if(s.getStudentId()==null || s.getStudentId().isBlank()) {
			long count=studentRepository.count()+1;
			String generatedId=String.format("STD%04d", count);
			s.setStudentId(generatedId);
		}
		s.getFirstName().trim().toLowerCase();
		s.getLastName().trim().toLowerCase();
		return studentRepository.save(s);
	}
	
	public Student findByStudentId(String id) {
		Student s=studentRepository.findByStudentId(id).orElseThrow(()-> new ResourceNotFoundException("Student not found"));
		return s;
	}
	
	public List<Student> findByName(String name) {
		return studentRepository.findByFirstNameContainingOrLastNameContaining(name.toLowerCase(), name.toLowerCase());
	}
	
	public List<Student> findByMobileNumber(String number) {
		return studentRepository.findByMobileNumber(number);
	}
	
	public List<Student> findAllStudents(){
		return studentRepository.findAll();
	}
	
	public void deleteStudent(String id) {
		Student s=findByStudentId(id);
		s.setStatus("DELETED");
	    studentRepository.save(s);
	}
}
