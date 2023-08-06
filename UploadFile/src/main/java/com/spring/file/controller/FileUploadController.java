package com.spring.file.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.file.handler.FileUploadHandler;

@RestController
public class FileUploadController 
{
	@PostMapping("file/upload")
	public ResponseEntity<String> uploadFile(@RequestBody() MultipartFile file)
	{
		System.out.println("File Name " + file.getOriginalFilename());
		System.out.println("File Size " + file.getSize());
		
		//Validation
		if (file.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please Upload the file");
		}
		
		if (file.getContentType().equals("image/jpg"))
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only jpg files are allowed.");
		}
		
		boolean isFileUploaded = FileUploadHandler.uploadFile(file);
		
		if (isFileUploaded)
		{
			return ResponseEntity.ok("File Uploaded Successfully");
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Opps ! something went wrong, while uploading file");

	}
}
