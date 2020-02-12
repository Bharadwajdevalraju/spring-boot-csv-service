package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appConstant.ApplicationConstant;
import com.example.entity.CsvEntity;
import com.example.services.CsvServices;


@RestController
public class CsvController {

@Autowired
CsvServices csvservices;

	@PostMapping(path = "/entry", consumes = "application/json")
	ResponseEntity<HashMap<String, String>> createCSV(@RequestBody CsvEntity csventity) {
		return new ResponseEntity<HashMap<String, String>>(csvservices.createCSV(csventity),
				csvservices.createCSV(csventity).get("message").equals(ApplicationConstant.UNAUTHORIZED_USER)
						? HttpStatus.UNAUTHORIZED
						: HttpStatus.OK);
	}

@GetMapping(path="/download")
public ResponseEntity<ByteArrayResource> download(@RequestParam("filename") String filename) {
	try {
		return csvservices.download(filename);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		// what to do when exception occurs
		// log message
		return null; // TODO
	}
}


}

