package com.spring.file.handler;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadHandler 
{
	public static String UploadDir = "D:\\Spring Boot\\Spring-Spring-Boot\\UploadFile\\src\\main\\resources\\static\\image\\";
	
	public static boolean uploadFile(MultipartFile file)
	{
		boolean isFileUploaded = false;
		
		try 
		{	/*
			// Way 1
			// Reading data from file			
			byte [] fileData = file.getBytes();
						
			// Storing that file data
			FileOutputStream outstream = new FileOutputStream(UploadDir + file.getOriginalFilename());
			outstream.write(fileData);
			
			// closing resources
			outstream.flush();
			
			isFileUploaded = true;
			*/
			// way 2 
			
			Files.copy(file.getInputStream(), Paths.get(UploadDir + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			isFileUploaded = true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return isFileUploaded;
	}
}
