package in.sandeep.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReceiptController {

	@GetMapping("/receipts/{filename}")
	public ResponseEntity<Resource> getReceipt(@PathVariable String filename) throws IOException {
		 Path path = Paths.get("receipts/" + filename);
	        Resource resource = new UrlResource(path.toUri());

	        if (!resource.exists()) {
	            return ResponseEntity.notFound().build();
	        }

	        return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(resource);
	}
}
