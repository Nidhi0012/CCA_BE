package com.cybage.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cybage.model.Conference;
import com.cybage.service.ConferenceService;

@CrossOrigin
@RestController

@RequestMapping("/api")
    public class ConferenceController {
	
	@Autowired
	private ConferenceService conferenceService;
	
	@PostMapping("/saveConference")
	public ResponseEntity<Conference> saveConference( @Valid @RequestBody Conference conference)
	{
		return new ResponseEntity<Conference>(conferenceService.saveConference(conference),HttpStatus.CREATED);
	}
	  
	@GetMapping("/")
	public ResponseEntity<List<Conference>> getAllConference()
	{
		return new ResponseEntity<List<Conference>>(conferenceService.getAllConference(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")   
	public ResponseEntity<Conference>getConferenceById(@PathVariable Integer id)
	{
		return new ResponseEntity<Conference>(conferenceService.getConferenceById(id),HttpStatus.OK);
	}
	@GetMapping("/conferences/delete/{id}")
	public ResponseEntity<String> deleteConference(@PathVariable Integer id)
	{
		
		return new ResponseEntity<String>(conferenceService.deleteConference(id),HttpStatus.OK);
	}
	
	@PutMapping("/conferences/edit/{id}")
	public ResponseEntity<Conference> editConference( @Valid @RequestBody Conference conference, @PathVariable Integer id)
	{
		return new ResponseEntity<Conference>(conferenceService.editConference(conference,id),HttpStatus.CREATED);
	}
	}
	
	
	
	
	
	
	
	
	
	


