package in.sandeep.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FeeTransaction {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String studentId;
	    private double amountPaid;
	    private LocalDate paymentDate;
	    private double totalPaid;
	    private double balance;
	    private LocalDate nextDueDate;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getStudentId() {
			return studentId;
		}
		public void setStudentId(String studentId) {
			this.studentId = studentId;
		}
		public double getAmountPaid() {
			return amountPaid;
		}
		public void setAmountPaid(double amountPaid) {
			this.amountPaid = amountPaid;
		}
		public LocalDate getPaymentDate() {
			return paymentDate;
		}
		public void setPaymentDate(LocalDate paymentDate) {
			this.paymentDate = paymentDate;
		}
		public double getTotalPaid() {
			return totalPaid;
		}
		public void setTotalPaid(double totalPaid) {
			this.totalPaid = totalPaid;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
		public LocalDate getNextDueDate() {
			return nextDueDate;
		}
		public void setNextDueDate(LocalDate nextDueDate) {
			this.nextDueDate = nextDueDate;
		}  
	    
}
