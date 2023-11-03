package com.cybage.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cybage.exception.ConfNotFoundException;
import com.cybage.model.Conference;
import com.cybage.repository.ConferenceRepository;
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

    public List<Conference> getAllConference(String field) {
        Sort sort = field.equals("date") ? Sort.by(Direction.DESC, field) : null;
        return conferenceRepo.findAll(sort);
    }

    public Conference getConferenceById(Integer id) {
        if (conferenceRepo.findById(id).isEmpty()) {
            throw new ConfNotFoundException("Requested Conference does not exist");
        }
        return conferenceRepo.findById(id).get();
    }

    public String deleteConference(Integer id) {
        Conference conference = conferenceRepo.findById(id).get();

        if (conference != null) {
            conferenceRepo.delete(conference);
            return "Conference Deleted Successfully";
        }

        return "Something wrong on the server";
    }

    public Conference editConference(Conference c, Integer id) {
        Conference oldConference = conferenceRepo.findById(id).get();
        oldConference.setPlace(c.getPlace());
        oldConference.setDate(c.getDate());
        oldConference.setName(c.getName());
        oldConference.setStatus(c.getStatus());
        oldConference.setLink(c.getLink());
        return conferenceRepo.save(oldConference);
    }

    public ResponseEntity<List<Conference>> findAll(Sort by) {
        return null;
    }

    public List<Conference> getAllConference() {
        return null;
    }

    public List<Conference> getConferenceByStatus(String status) {
        return conferenceRepo.findByStatus(status);
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
}
