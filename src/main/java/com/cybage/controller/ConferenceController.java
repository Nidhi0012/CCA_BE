package com.cybage.controller;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cybage.model.Conference;
import com.cybage.service.ConferenceService;


@CrossOrigin
@RestController
@RequestMapping("/api")
    public class ConferenceController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConferenceController.class);	
	
	@Autowired
	private ConferenceService conferenceService;
	
	
	@PostMapping("/saveConference")
	public ResponseEntity<Conference> saveConference(@Valid @RequestBody Conference conference) {
	    
		logger.info("Saving conference: {}", conference.getName());
	    try {
	        return new ResponseEntity<>(conferenceService.saveConference(conference), HttpStatus.CREATED);
	    } catch (Exception e) {
	        logger.error("Error saving conference: {}", e.getMessage());
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@GetMapping("/")
    public List<Conference> getAllConference(@RequestParam(required = false) String field) {
        if (field != null && !field.isEmpty()) {
            return conferenceService.getAllConference(field);
        } else {
            throw new IllegalArgumentException("The 'field' parameter is required.");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<String>("Bad Request: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

	
	@GetMapping("/{id}")   
	public ResponseEntity<Conference>getConferenceById(@PathVariable Integer id)
	{
		return new ResponseEntity<Conference>(conferenceService.getConferenceById(id),HttpStatus.OK);
	}
	
	@GetMapping("/conferences/delete/{id}")
	public ResponseEntity<String> deleteConference(@PathVariable Integer id) {
	    
		logger.info("Deleting conference with ID: {}", id);
	    
	    try {
	        String result = conferenceService.deleteConference(id);
	        return new ResponseEntity<String>(result, HttpStatus.OK);
	    } catch (Exception e) {
	        logger.error("Error deleting conference: {}", e.getMessage());
	        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PutMapping("/conferences/edit/{id}")
	public ResponseEntity<Conference> editConference(@Valid @RequestBody Conference conference, @PathVariable Integer id) {
	    logger.info("Editing conference with ID: {}", id);
	    
	    try {
	        return new ResponseEntity<Conference>(conferenceService.editConference(conference, id), HttpStatus.CREATED);
	    } catch (Exception e) {
	        logger.error("Error editing conference: {}", e.getMessage());
	        return new ResponseEntity<Conference>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/filterBy/{status}")
	public ResponseEntity<List<Conference>>getConferenceByStatus(@PathVariable String status)
	{
		return new ResponseEntity<List<Conference>>(conferenceService.getConferenceByStatus(status),HttpStatus.OK);
	}}
	
	
	
	
	
	
	
	
	
	


