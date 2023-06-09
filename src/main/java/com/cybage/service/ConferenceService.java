package com.cybage.service;
import java.util.List;
import com.cybage.model.Conference;

   public interface ConferenceService 
   {
   public Conference saveConference(Conference conference);
   public List<Conference> getAllConference();
   public Conference getConferenceById(Integer id );
   public String deleteConference(Integer id);
   public Conference editConference(Conference conference, Integer id);
   }

	
	




    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    