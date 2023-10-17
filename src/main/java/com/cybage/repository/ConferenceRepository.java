package com.cybage.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.sql.Date;
import com.cybage.model.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer>
{
	List<Conference> findByStatus(String status);

	List<Conference> findByName(String name);

	List<Conference> findByDate(Date date);
}


