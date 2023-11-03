package serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import com.cybage.exception.ConfNotFoundException;
import com.cybage.model.Conference;
import com.cybage.repository.ConferenceRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import com.cybage.service.ConferenceServiceImpl;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class, ConferenceServiceImpl.class})
public class ConferenceServiceImplTest {

    @Autowired
    private ConferenceServiceImpl conferenceService;

    @MockBean
    private ConferenceRepository conferenceRepo;

    @BeforeEach
    public void setUp() {
        conferenceService.setConferenceRepo(conferenceRepo);
    }

    @Test
    public void testSaveConference() {
        Conference conference = new Conference();
        when(conferenceRepo.save(conference)).thenReturn(conference);

        Conference savedConference = conferenceService.saveConference(conference);

        assertEquals(conference, savedConference);
    }

    @Test
    public void testGetAllConference() {
        List<Conference> conferences = new ArrayList<>();
        Sort sort = Sort.by(Sort.Order.desc("date"));
        when(conferenceRepo.findAll(sort)).thenReturn(conferences);

        List<Conference> result = conferenceService.getAllConference("date");

        assertEquals(conferences, result);
    }

    @Test
    public void testGetConferenceById() {
        Integer id = 1;
        Conference conference = new Conference();
        when(conferenceRepo.findById(id)).thenReturn(Optional.of(conference));

        Conference result = conferenceService.getConferenceById(id);

        assertEquals(conference, result);
    }

    @Test
    public void testGetConferenceByIdNotFoundException() {
        Integer id = 1;
        when(conferenceRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(ConfNotFoundException.class, () -> {
            conferenceService.getConferenceById(id);
        });
    }

    @Test
    public void testDeleteConference() {
        Integer id = 1;
        Conference conference = new Conference();
        when(conferenceRepo.findById(id)).thenReturn(Optional.of(conference));

        doNothing().when(conferenceRepo).delete(conference);

        String result = conferenceService.deleteConference(id);

        assertEquals("Conference Deleted Successfully", result);
        verify(conferenceRepo, times(1)).delete(conference);
    }

    @Test
    public void testEditConference() {
        Integer id = 1;
        Conference existingConference = new Conference();
        existingConference.setConferenceId(id);
        existingConference.setName("Old Name");

        Conference updatedConference = new Conference();
        updatedConference.setConferenceId(id);
        updatedConference.setName("New Name");

        when(conferenceRepo.findById(id)).thenReturn(Optional.of(existingConference));
        when(conferenceRepo.save(existingConference)).thenReturn(updatedConference);

        Conference result = conferenceService.editConference(updatedConference, id);

        assertEquals("New Name", result.getName());
    }
}
