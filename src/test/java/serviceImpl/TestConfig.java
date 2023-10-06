package serviceImpl;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.mockito.Mockito;
import com.cybage.repository.ConferenceRepository;

@TestConfiguration
public class TestConfig {
    @Bean
    public ConferenceRepository conferenceRepository() {
        return Mockito.mock(ConferenceRepository.class);
    }
}
