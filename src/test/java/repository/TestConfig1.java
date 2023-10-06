package repository;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.cybage.repository.ConferenceRepository;

@TestConfiguration
public class TestConfig1 {
	@Bean
    public ConferenceRepository conferenceRepository() {
        return Mockito.mock(ConferenceRepository.class);
    }
}
