package repository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import com.cybage.model.Conference;
import com.cybage.repository.ConferenceRepository;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest(classes = TestConfig1.class)
public class ConferenceRepositoryTest {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Test
    public void testFindByStatus() {
        Conference onlineConference = new Conference();
        onlineConference.setStatus("Online");
        conferenceRepository.save(onlineConference);

        Conference inPersonConference = new Conference();
        inPersonConference.setStatus("In-Person");
        conferenceRepository.save(inPersonConference);

        Conference onlineAndInPersonConference = new Conference();
        onlineAndInPersonConference.setStatus("Online and In-Person");
        conferenceRepository.save(onlineAndInPersonConference);

        List<Conference> onlineConferences = conferenceRepository.findByStatus("Online");

        assertEquals(2, onlineConferences.size());
        assertEquals("Online", onlineConferences.get(0).getStatus());
        assertEquals("Online", onlineConferences.get(1).getStatus());

        List<Conference> inPersonConferences = conferenceRepository.findByStatus("In-Person");

        assertEquals(1, inPersonConferences.size());
        assertEquals("In-Person", inPersonConferences.get(0).getStatus());

        List<Conference> onlineAndInPersonConferences = conferenceRepository.findByStatus("Online and In-Person");

        assertEquals(1, onlineAndInPersonConferences.size());
        assertEquals("Online and In-Person", onlineAndInPersonConferences.get(0).getStatus());
    }
}
