package in.sandeep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sandeep.entity.FeeTransaction;

@Repository
public interface FeeTransRepository extends JpaRepository<FeeTransaction, Long> {
	
   List<FeeTransaction> findByStudentIdOrderByPaymentDateAsc(String studentId);
}
