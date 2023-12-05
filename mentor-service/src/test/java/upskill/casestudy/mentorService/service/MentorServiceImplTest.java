package upskill.casestudy.mentorService.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.web.reactive.function.client.WebClientResponseException;

import upskill.casestudy.mentorService.dto.APIResponseDto;
import upskill.casestudy.mentorService.dto.MentorDto;
import upskill.casestudy.mentorService.entity.Mentor;
import upskill.casestudy.mentorService.mapper.MentorMapper;
import upskill.casestudy.mentorService.repository.MentorRepository;
import upskill.casestudy.mentorService.exception.EmailAlreadyExistException;
import upskill.casestudy.mentorService.exception.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
class MentorServiceImplTest {

	@Mock

	private MentorRepository mentorRepository;

	@Mock

	private WebClient webClient;

	@InjectMocks

	private MentorServiceImpl mentorService;

	@Test

	void testCreateMentor() {

	MentorDto mentorDto = new MentorDto(1L,"pragathi","pragathi@gmail.com",5,"Free","Not Assigned");

	Mentor mentorEntity = MentorMapper.mapToMentor(mentorDto);

	when(mentorRepository.findByEmail(mentorDto.getEmail())).thenReturn(Optional.empty());

	when(mentorRepository.save(any(Mentor.class))).thenReturn(mentorEntity);

	MentorDto savedMentorDto = mentorService.createMentor(mentorDto);

	assertNotNull(savedMentorDto);

	assertEquals("Free", savedMentorDto.getStatus());

	assertEquals("Not Assigned", savedMentorDto.getCourseCode());

	}


    @Test
    void testCreateMentorWithEmailAlreadyExists() {
        MentorDto mentorDto = new MentorDto(1L,"pragathi","pragathi@gmail.com",5,"free","AWS01");
        when(mentorRepository.findByEmail(mentorDto.getEmail())).thenReturn(Optional.of(new Mentor()));

        assertThrows(EmailAlreadyExistException.class, () -> mentorService.createMentor(mentorDto));
    }

	@Test
    void testGetAllMentors() {
        List<Mentor> mentorEntities = new ArrayList<>();
        mentorEntities.add(new Mentor(2L,"pragathi","pragathi1@gmail.com",5,"free","AWS01"));
        when(mentorRepository.findAll()).thenReturn(mentorEntities);

        List<MentorDto> mentorDtos = mentorService.getAllMentors();

        assertNotNull(mentorDtos);
        assertEquals(mentorEntities.size(), mentorDtos.size());
    }

    @Test
    void testUpdateMentor() {
        MentorDto mentorDto = new MentorDto(1L,"pragathi","pragathi@gmail.com",5,"free","AWS01");
        Mentor existingMentor = new Mentor(1L,"pragathi","pragathi@gmail.com",5,"free","AWS01");
        when(mentorRepository.findById(mentorDto.getId())).thenReturn(Optional.of(existingMentor));
        when(mentorRepository.save(any(Mentor.class))).thenReturn(existingMentor);

        MentorDto updatedMentorDto = mentorService.updateMentor(mentorDto);

        assertNotNull(updatedMentorDto);
    }

    @Test
    void testDeleteMentor() {
        long mentorId = 1L;
        Mentor existingMentor = new Mentor(1L,"pragathi","pragathi@gmail.com",5,"free","AWS01");
        when(mentorRepository.findById(mentorId)).thenReturn(Optional.of(existingMentor));

        String result = mentorService.deleteMentor(mentorId);

        assertEquals("Deleted Successfully!!", result);
        verify(mentorRepository, times(1)).deleteById(mentorId);
    }
//    @Test
//    void testGetMentorByID() {
//        long mentorId = 1L;
//        Mentor mentorEntity = new Mentor(1L,"pragathi","pragathi@gmail.com",5,"free","AWS01");
//        when(mentorRepository.findById(mentorId)).thenReturn(java.util.Optional.of(mentorEntity));
//
//        CourseDto courseDto = new CourseDto(2L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01");
//        when(webClient.get()).thenReturn(mock(WebClient.RequestHeadersUriSpec.class));
//       

//        APIResponseDto apiResponseDto = mentorService.getMentorByID(mentorId);
//
//        assertNotNull(apiResponseDto);
//        assertNotNull(apiResponseDto.getMentor());
//        assertEquals(mentorEntity.getName(), apiResponseDto.getMentor().getName());
//        assertNotNull(apiResponseDto.getCourse());
//        assertEquals(courseDto, apiResponseDto.getCourse());
//    }

    @Test
    void testGetMentorByIDWithResourceNotFoundException() {
        long mentorId = 1L;
        when(mentorRepository.findById(mentorId)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> mentorService.getMentorByID(mentorId));
    }

   
   
    @Test
    void testGetDefaultCourse() {
        long mentorId = 1L;
        Mentor mentorEntity = new Mentor(1L,"pragathi","pragathi@gmail.com",5,"free","AWS01");
        when(mentorRepository.findById(mentorId)).thenReturn(java.util.Optional.of(mentorEntity));

        APIResponseDto apiResponseDto = mentorService.getDafaultCourse(mentorId, new Exception());

        assertNotNull(apiResponseDto);
        assertNotNull(apiResponseDto.getMentor());
        assertNotNull(apiResponseDto.getCourse());
        
    }

    

    
}



