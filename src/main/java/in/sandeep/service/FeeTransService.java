package in.sandeep.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sandeep.entity.FeeTransaction;
import in.sandeep.entity.Student;
import in.sandeep.exception.InvalidAmountException;
import in.sandeep.repository.FeeTransRepository;

@Service
public class FeeTransService {

	@Autowired
	private FeeTransRepository feeTransRepository;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ReceiptGenerator receiptGenerator;
	
	public List<FeeTransaction> history(String studentId){
		studentService.findByStudentId(studentId);
		return feeTransRepository.findByStudentIdOrderByPaymentDateAsc(studentId);
	}
	
	public FeeTransaction pay(String StudentId,double amount) {
		
	  Student s=studentService.findByStudentId(StudentId);
	  if(amount < 50) {
		  throw new InvalidAmountException("Minimum fee payment must be ₹50");
	  }
	  
	  double totalFee=s.getTotalFee();
	  List<FeeTransaction> transactions = feeTransRepository.findByStudentIdOrderByPaymentDateAsc(StudentId);
	  double totalPaid=0;
	  for(FeeTransaction trans:transactions) {
		  totalPaid+=trans.getAmountPaid();
	  }
	  if(totalPaid == s.getTotalFee()) {
		  throw new InvalidAmountException("Full fee already paid for this student.");
	  }
	  
	  totalPaid+=amount;
	  
	  if(totalPaid >s.getTotalFee()) {
		  throw new InvalidAmountException("Paid amount exceeds total fee. Remaining balance is ₹" + (s.getTotalFee() - (totalPaid - amount)));
	  }
	  double remainingFee=totalFee-totalPaid;
	  
	  FeeTransaction trans=new FeeTransaction();
	  trans.setStudentId(StudentId);
	  trans.setAmountPaid(amount);
	  trans.setPaymentDate(LocalDate.now());
	  trans.setTotalPaid(totalPaid);
	  trans.setBalance(remainingFee);
	  trans.setNextDueDate(LocalDate.now().plusDays(30));
	  
	  transactions.add(trans);
	  
	  FeeTransaction savedtrans=feeTransRepository.save(trans);
	  receiptGenerator.generateReceipt(savedtrans, s);
      return savedtrans;
	}
	
}
