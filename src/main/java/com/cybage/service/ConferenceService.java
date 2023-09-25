package com.cybage.service;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import com.cybage.model.Conference;

   public interface ConferenceService {
   
   public Conference saveConference(Conference conference);
   
   public List<Conference> getAllConference(String field);

   public Conference getConferenceById(Integer id );
  
   public String deleteConference(Integer id);
  
   public Conference editConference(Conference conference, Integer id);

   public ResponseEntity<List<Conference>> findAll(Sort by);

   public List<Conference> getConferenceByStatus(String status);


   


 
   }

	
	




    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    