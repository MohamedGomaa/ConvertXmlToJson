package com.agile.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.agile.home.model.File;
import com.agile.home.model.Note;
import com.agile.home.service.Decoding;


@RestController
@RequestMapping("/api")
public class HomeController {

	
	@Autowired
	Decoding theDecodingService;
	
	
	@PostMapping("/decode")
	public ResponseEntity<Note> decode(@RequestBody File theFile){
		
		Note decodedString = theDecodingService.decode64Base(theFile.getEncodedString());
		
		return new ResponseEntity<>(decodedString,HttpStatus.OK);
	
	}
	
}
