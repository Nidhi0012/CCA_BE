package com.cybage.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cybage.exception.ConfNotFoundException;
import com.cybage.model.Conference;
import com.cybage.repository.ConferenceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepo;

    public Conference saveConference(Conference conference) {
        return conferenceRepo.save(conference);
    }
    
    

    @Override
    public Page<Conference> getAllConference(String field, Pageable pageable) {
        Sort sort = Sort.by(Direction.DESC, "date");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        // Exclude past conferences
        Date currentDate = new Date();
        return conferenceRepo.findByDateAfter(currentDate, pageable);
    }

    @Override
    public List<Conference> getAllConferenceByField(String field) {
        if ("past".equalsIgnoreCase(field)) {
            // Fetch only past conferences
            Sort sort = Sort.by(Direction.DESC, "date");
            return conferenceRepo.findByDateBefore(new Date(), sort);
        } else if ("date".equalsIgnoreCase(field)) {
            // Fetch all conferences and sort by date, excluding past conferences
            Sort sort = Sort.by(Direction.DESC, "date");
            return conferenceRepo.findByDateAfter(new Date(), sort);
        } else {
            // Handle other cases or return an empty list
            return Collections.emptyList();
        }
    }

    public Conference getConferenceById(Integer id) {
        if (conferenceRepo.findById(id).isEmpty()) {
            throw new ConfNotFoundException("Requested Conference does not exist");
        }
        return conferenceRepo.findById(id).get();
    }

//   public String deleteConference(Integer id) {
//       Conference conference = conferenceRepo.findById(id).get();
//
//       if (conference != null) {
//            conferenceRepo.delete(conference);
//            return "Conference Deleted Successfully";
//        }
//
//        return "Something wrong on the server";
//    }
    
    public String deleteConference(Integer id) {
        Optional<Conference> conferenceOptional = conferenceRepo.findById(id);

        if (conferenceOptional.isPresent()) {
            Conference conference = conferenceOptional.get();
            conferenceRepo.delete(conference);
            return "Conference Deleted Successfully";
        } else {
            throw new ConfNotFoundException("Conference with ID " + id + " not found");
        }
    }


//    public Conference editConference(Conference c, Integer id) {
//        Conference oldConference = conferenceRepo.findById(id).get();
//        oldConference.setPlace(c.getPlace());
//        oldConference.setDate(c.getDate());
//        oldConference.setName(c.getName());
//        oldConference.setStatus(c.getStatus());
//        oldConference.setLink(c.getLink());
//        return conferenceRepo.save(oldConference);
//    }
    
    public Conference editConference(Conference c, Integer id) {
        Optional<Conference> optionalOldConference = conferenceRepo.findById(id);

        if (optionalOldConference.isPresent()) {
            Conference oldConference = optionalOldConference.get();
            oldConference.setPlace(c.getPlace());
            oldConference.setDate(c.getDate());
            oldConference.setName(c.getName());
            oldConference.setStatus(c.getStatus());
            oldConference.setLink(c.getLink());
            return conferenceRepo.save(oldConference);
        } else {
            throw new ConfNotFoundException("Conference with ID " + id + " not found");
        }
    }


    @Override
    public ResponseEntity<List<Conference>> findAll(Sort by) {
        return null;
    }

    public Page<Conference> findAll(Sort by, Pageable pageable) {
        return conferenceRepo.findAll(pageable);
    }

    public List<Conference> getConferenceByStatus(String status) {
        List<Conference> conferences;

        if ("online".equalsIgnoreCase(status) || "in-person".equalsIgnoreCase(status) || "online and in-person".equalsIgnoreCase(status)) {
            // Fetch conferences by status
            conferences = conferenceRepo.findByStatus(status);
        } else {
            // Handle unknown status or return an empty list
            conferences = Collections.emptyList();
        }

        // Exclude past conferences
        Date currentDate = new Date();
        conferences.removeIf(conference -> conference.getDate().before(currentDate));

        return conferences;
    }

    public void setConferenceRepo(ConferenceRepository conferenceRepo) {
    }

    public boolean isDuplicateConference(Conference conference) {
        List<Conference> conferencesWithSameName = conferenceRepo.findByName(conference.getName());
        if (!conferencesWithSameName.isEmpty()) {
            return true;
        }

        List<Conference> conferencesWithSameDate = conferenceRepo.findByDate(conference.getDate());
        if (!conferencesWithSameDate.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public List<Conference> getAllConference(String field) {
        return null;
    }
}
