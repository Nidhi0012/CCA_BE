package com.cybage.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cybage.model.Conference;
import com.cybage.repository.ConferenceRepository;

   @Service
   public class ConferenceServiceImpl implements ConferenceService{
	
	@Autowired
	private ConferenceRepository conferenceRepo;
	
	@Override
	public Conference saveConference(Conference conference) {
		return conferenceRepo.save(conference);
	}

	@Override
	public List<Conference> getAllConference() {
		return conferenceRepo.findAll();
	}
    @Override
	public Conference getConferenceById(Integer id) {
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
	}}

	


