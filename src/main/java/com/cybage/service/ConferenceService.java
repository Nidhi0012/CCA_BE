   package com.cybage.service;
   import java.util.List;

  import javax.validation.Valid;

  import org.springframework.data.domain.Page;
  import org.springframework.data.domain.Pageable;
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

   public boolean isDuplicateConference(@Valid Conference conference);

   public Page<Conference> getAllConference(String field, Pageable pageable);

   public List<Conference> getAllConferenceByField(String field);


 
   }

	
	




    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    