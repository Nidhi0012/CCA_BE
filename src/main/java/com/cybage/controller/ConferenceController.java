package com.cybage.controller;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cybage.model.Conference;
import com.cybage.service.ConferenceService;
import io.swagger.v3.oas.annotations.Operation;
import com.cybage.exception.CustomDuplicateConferenceException;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ConferenceController {
	
    private static final Logger logger = LoggerFactory.getLogger(ConferenceController.class);	
	
    @Autowired
    private ConferenceService conferenceService;
	
    @Operation(summary = "Add a conference", description = "Add a new conference to the system.")
    @PostMapping("/saveConference")
    public ResponseEntity<Conference> saveConference(@Valid @RequestBody Conference conference) {
        if (conferenceService.isDuplicateConference(conference)) {
            throw new CustomDuplicateConferenceException("Conference with the same name or date already exists.");
        }
      
        logger.info("Saving conference: {}", conference.getName());
        try {
            return new ResponseEntity<Conference>(conferenceService.saveConference(conference), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving conference: {}", e.getMessage());
            return new ResponseEntity<Conference>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
    @Operation(summary = "Get all conference list", description = "Get a list of all conferences in the system.")
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

    @Operation(summary = "Get a conference by its id", description = "Retrieve a conference by its unique identifier.")
    @GetMapping("/{id}")   
    public ResponseEntity<Conference> getConferenceById(@PathVariable Integer id) {
        return new ResponseEntity<Conference>(conferenceService.getConferenceById(id), HttpStatus.OK);
    }
	
    @Operation(summary = "Delete a conference by its id", description = "Delete a conference by its unique identifier.")
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
	
    @Operation(summary = "Edit a conference by its id", description = "Edit a conference by its unique identifier.")
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
	
    @Operation(summary = "Get conferences by status", description = "Get a list of conferences filtered by status.")
    @GetMapping("/filterBy/{status}")
    public ResponseEntity<List<Conference>> getConferenceByStatus(@PathVariable String status) {
        return new ResponseEntity<List<Conference>>(conferenceService.getConferenceByStatus(status), HttpStatus.OK);
    }
}
