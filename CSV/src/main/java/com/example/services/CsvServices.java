package com.example.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.example.appConstant.ApplicationConstant;
import com.example.entity.CsvEntity;

@Service
public class CsvServices{
	
public HashMap<String, String> createCSV(CsvEntity csventity){
	HashMap<String, String> status=new HashMap<String, String>();
	status.put("message",this.defaultCreateCSV(csventity));
	return status;
}
	
public String  defaultCreateCSV(CsvEntity csventity) {
	if(this.authCheck(csventity.getUserName(),csventity.getPassword())) {
	   return this.createCSVFile(csventity);
	}
	else return ApplicationConstant.UNAUTHORIZED_USER;
}

private String createCSVFile(CsvEntity csventity) {
	    File folder=new File(csventity.getUserName());
	    folder.mkdir();
		File file=new File(csventity.getUserName()+"/"+csventity.getUserName()+".csv");
		try {
			file.createNewFile();
			return this.writeCSV(csventity,file);
		}catch (IOException e) {
			return ApplicationConstant.UNABLE_TO_CREATE_CSV;
		}
}

private String writeCSV(CsvEntity csventity,File file) {
	try { 
        FileWriter outputfile = new FileWriter(file); 
        CSVWriter writer = new CSVWriter(outputfile); 
  
        String[] header = { "UserName", "Col1", "col2","col3","col4","col5" }; 
        writer.writeNext(header); 
 
        String[] data= { csventity.getUserName(), csventity.getCol1(), csventity.getCol2(),csventity.getCol3(),csventity.getCol4(),csventity.getCol3() }; 
        writer.writeNext(data); 
        writer.close(); 
    } 
    catch (IOException e) { 
       return ApplicationConstant.UNABLE_TO_WRITE_CSV; 
    } 
	return ApplicationConstant.CREATED;
}

public boolean authCheck(String userName, String password) {
	 Map<String,String> credentials=new HashMap<String,String>();
	 credentials.put("bharadwaj","Secure");
	 credentials.put("Nagendra","Welcome");
	try { 	
		if(credentials.get(userName).equals(password))
    	return true;
    }
	catch (Exception e) {
		return false;
		}
	return false;
}

public ResponseEntity<ByteArrayResource> download(String filename) throws IOException {
    File file = new File("./"+filename+"/"+filename+".csv");
    HttpHeaders header = new HttpHeaders();
    header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename+".csv");
    header.add("Cache-Control", "no-cache, no-store, must-revalidate");
    header.add("Pragma", "no-cache");
    header.add("Expires", "0");
    Path path = Paths.get(file.getAbsolutePath());
    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
    return ResponseEntity.ok()
            .headers(header)
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(resource);
}
private String ACCOUNT_SID = "AC2d7d9dcbc52e451a9e9eaca5a3fc74a2";
private String AUTH_TOKEN = "a89d48de1743c39bea0183b20a90d8dd";
public ResponseEntity<HashMap<String,String>> sendMessage(String mobile,String text) {
 
    
    Twilio.init(this.ACCOUNT_SID, this.AUTH_TOKEN);
    Message message = Message.creator(
            new com.twilio.type.PhoneNumber("whatsapp:+91"+mobile),
            new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
            text)
        .create();
    if(message.getSid()!=null)
	return new ResponseEntity<HashMap<String,String>>(HttpStatus.OK);
    return new ResponseEntity<HashMap<String,String>>(HttpStatus.NOT_ACCEPTABLE);
}
}
