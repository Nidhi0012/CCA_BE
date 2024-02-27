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

    @Operation(summary = "Get list of past conferences", description = "Get a list of past conferences in the system.")
    @GetMapping("/conferences/past")
    public ResponseEntity<List<Conference>> getAllPastConferences() {
        try {
            List<Conference> pastConferences = conferenceService.getAllConferenceByField("past");
            return new ResponseEntity<>(pastConferences, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching past conferences: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Add a conference", description = "Add a new conference to the system.")
    @PostMapping("/saveConference")
    public ResponseEntity<Conference> saveConference(@Valid @RequestBody Conference conference) {
        if (conferenceService.isDuplicateConference(conference)) {
            throw new CustomDuplicateConferenceException("Conference with the same name or date already exists.");
        }

        logger.info("Saving conference: {}", conference.getName());
        try {
            return new ResponseEntity<>(conferenceService.saveConference(conference), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving conference: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get all conference list", description = "Get a list of all conferences in the system.")
    @GetMapping("/")
    public ResponseEntity<List<Conference>> getAllConference(
            @RequestParam(required = false) String field,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size) {

        try {
            if ("date".equalsIgnoreCase(field)) {
                // Fetch all conferences and sort by date, excluding past conferences
                List<Conference> conferences = conferenceService.getAllConferenceByField("date");
                return new ResponseEntity<>(conferences, HttpStatus.OK);
            } else {
                // Fetch conferences based on the specified field
                List<Conference> conferences = conferenceService.getAllConferenceByField(field);
                return new ResponseEntity<>(conferences, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error fetching conferences: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get a conference by its id", description = "Retrieve a conference by its unique identifier.")
    @GetMapping("/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable Integer id) {
        return new ResponseEntity<>(conferenceService.getConferenceById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete a conference by its id", description = "Delete a conference by its unique identifier.")
    @GetMapping("/conferences/delete/{id}")
    public ResponseEntity<String> deleteConference(@PathVariable Integer id) {
        logger.info("Deleting conference with ID: {}", id);
        try {
            String result = conferenceService.deleteConference(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting conference: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Edit a conference by its id", description = "Edit a conference by its unique identifier.")
    @PutMapping("/conferences/edit/{id}")
    public ResponseEntity<Conference> editConference(@Valid @RequestBody Conference conference, @PathVariable Integer id) {
        logger.info("Editing conference with ID: {}", id);
        try {
            return new ResponseEntity<>(conferenceService.editConference(conference, id), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error editing conference: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get conferences by status", description = "Get a list of conferences filtered by status.")
    @GetMapping("/filterBy/{status}")
    public ResponseEntity<List<Conference>> getConferenceByStatus(@PathVariable String status) {
        return new ResponseEntity<>(conferenceService.getConferenceByStatus(status), HttpStatus.OK);
    }
}
