package in.sandeep.service;

import org.springframework.stereotype.Service;

import in.sandeep.entity.FeeTransaction;
import in.sandeep.entity.Student;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ReceiptGenerator {

	public void generateReceipt(FeeTransaction txn, Student student) {
        try {
        	 String dirPath = "receipts/";
             Files.createDirectories(Paths.get(dirPath)); 
             
            String fileName = dirPath + student.getStudentId() + "_" + txn.getId() + ".pdf";
            Files.createDirectories(Paths.get("receipts"));

            OutputStream file = new FileOutputStream(fileName);
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();

            document.addTitle("Fee Receipt");
            document.add(new Paragraph("SCHOOL FEE RECEIPT", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Student ID: " + student.getStudentId()));
            document.add(new Paragraph("Name: " + student.getFirstName() + " " + student.getLastName()));
            document.add(new Paragraph("Mobile: " + student.getMobileNumber()));
            document.add(new Paragraph("Admission Date: " + student.getAdmissionDate()));
            document.add(new Paragraph("Fee Plan: " + student.getFeePlan()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Amount Paid: ₹" + txn.getAmountPaid()));
            document.add(new Paragraph("Total Paid: ₹" + txn.getTotalPaid()));
            document.add(new Paragraph("Balance: ₹" + txn.getBalance()));
            document.add(new Paragraph("Payment Date: " + txn.getPaymentDate()));
            document.add(new Paragraph("Next Due Date: " + txn.getNextDueDate()));
            document.close();

            System.out.println("✅ PDF receipt saved: " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
