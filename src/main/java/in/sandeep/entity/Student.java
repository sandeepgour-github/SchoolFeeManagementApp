package in.sandeep.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Student {

	    @Id 
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(unique = true) 
	    private String studentId;
	    
	    @NotNull(message="First name is required")
	    private String firstName;
	    
	    @NotNull(message = "Last name is required")
	    private String lastName; 
	    
	    @NotNull(message = "Mobile number is required")
	    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
	    private String mobileNumber;
	    
	    @NotNull(message = "Admission date is required")
	    private LocalDate admissionDate;
	    
	    @NotNull(message = "Fee plan must be selected")
	    private String feePlan;
	    
	    @Min(value = 1000, message = "Total fee must be at least 1000")
	    private double totalFee;
	    
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
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public LocalDate getAdmissionDate() {
			return admissionDate;
		}
		public void setAdmissionDate(LocalDate admissionDate) {
			this.admissionDate = admissionDate;
		}
		public String getFeePlan() {
			return feePlan;
		}
		public void setFeePlan(String feePlan) {
			this.feePlan = feePlan;
		}
		public double getTotalFee() {
			return totalFee;
		}
		public void setTotalFee(double totalFee) {
			this.totalFee = totalFee;
		}
	    
}
