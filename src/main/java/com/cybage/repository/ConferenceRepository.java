package com.cybage.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;
import com.cybage.model.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer>
{
	 List<Conference> findByStatus(String status);

	 List<Conference> findByName(String name);

	 List<Conference> findByDate(Date date);
	
	 Page<Conference> findByStatus(String status, Pageable pageable);

	 Page<Conference> findByName(String name, Pageable pageable);

	 Page<Conference> findByDate(Date date, Pageable pageable);

	Page<Conference> findByDateAfter(Date currentDate, Pageable pageable);

	List<Conference> findByDateBefore(Date date, Sort sort);

	List<Conference> findByDateAfter(Date currentDate, Sort sort);

	
}


	 
	



