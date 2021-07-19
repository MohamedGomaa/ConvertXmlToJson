package com.agile.home.service.implementation;

import java.io.StringReader;
import java.util.Base64;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agile.home.model.Note;
import com.agile.home.service.Decoding;


@Service
public class DecodingImplementation implements Decoding {
	
	private static final Logger theLogger = LoggerFactory.getLogger(DecodingImplementation.class);

	@Override
	public Note decode64Base(String text) {
		
		byte[] decodedBytes = Base64.getDecoder().decode(text);
		String decodedString = new String(decodedBytes);
		return convertXmlToPOJO(decodedString);
	}
	
	private Note convertXmlToPOJO(String theStr) {
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Note.class);
			
			Unmarshaller theUnmarcheller = jaxbContext.createUnmarshaller();
			
			return (Note) theUnmarcheller.unmarshal(new StringReader(theStr));
			
		} catch (JAXBException e) {
			theLogger.error(theStr, e);
		}	
		return null;
	}

}
