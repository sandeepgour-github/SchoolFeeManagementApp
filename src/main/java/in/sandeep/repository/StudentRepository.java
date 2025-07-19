package in.sandeep.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sandeep.entity.Student;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	Optional<Student> findByStudentId(String StudentId);
	List<Student> findByFirstNameContainingOrLastNameContaining(String fName,String lName);
	List<Student> findByMobileNumber(String number);
}
