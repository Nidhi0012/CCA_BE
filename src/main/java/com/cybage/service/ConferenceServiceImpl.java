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
   public class ConferenceServiceImpl implements ConferenceService{
	
	@Autowired
	private ConferenceRepository conferenceRepo;
	
	@Override
	public Conference saveConference(Conference conference) {
		return conferenceRepo.save(conference);
	}
	
	@Override
	    public List<Conference> getAllConference(String field) {
	        Sort sort = field.equals("date") ? Sort.by(Direction.DESC, field) : null;
	        return conferenceRepo.findAll(sort);
	    }
    
	@Override
	public Conference getConferenceById(Integer id) {
    	if(conferenceRepo.findById(id).isEmpty())
    	throw new ConfNotFoundException("Requested Conference does not exist");
		return conferenceRepo.findById(id).get();
	}
	
    @Override
	public String deleteConference(Integer id) {
	Conference conference =	conferenceRepo.findById(id).get();
	
	if(conference!=null)
	{
		conferenceRepo.delete(conference);
		return "Conference Deleted Successfully";
	}
	
	return "Something wrong on server";}
	
    @Override
	public Conference editConference(Conference c, Integer id) {
	Conference oldConference =	conferenceRepo.findById(id).get();
	oldConference.setPlace(c.getPlace());
	oldConference.setDate(c.getDate());
	oldConference.setName(c.getName());
	oldConference.setStatus(c.getStatus());
	oldConference.setLink(c.getLink());
	return conferenceRepo.save(oldConference);
	}

	@Override
	public ResponseEntity<List<Conference>> findAll(Sort by) {
		return null;
	}

	public List<Conference> getAllConference() {
		return null;
	}

	@Override
	public List<Conference> getConferenceByStatus(String status) {
		
		return conferenceRepo.findByStatus(status);
	}
    }
	
	

	
	
    
	
    
   

	


