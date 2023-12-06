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
import reactor.core.publisher.Mono;


import org.springframework.web.reactive.function.client.WebClientResponseException;

import upskill.casestudy.mentorService.dto.APIResponseDto;
import upskill.casestudy.mentorService.dto.BatchDto;
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
	
	@Mock
    private WebClient.Builder webClientBuilder;

	@InjectMocks

	private MentorServiceImpl mentorService;

	@Test

	void testCreateMentor() {

		MentorDto mentorDto = new MentorDto(1L, 12345L, "pragathi", "pragathi@gmail.com",
                "A", "Senior Mentor", "Engineering", "Java",
                "Free", 2, "BATCH001");

	Mentor mentorEntity = MentorMapper.mapToMentor(mentorDto);

	when(mentorRepository.findByEmail(mentorDto.getEmail())).thenReturn(Optional.empty());

	when(mentorRepository.save(any(Mentor.class))).thenReturn(mentorEntity);

	MentorDto savedMentorDto = mentorService.createMentor(mentorDto);

	assertNotNull(savedMentorDto);

	assertEquals("Free", savedMentorDto.getStatus());

	assertEquals("BATCH001", savedMentorDto.getBatchCode());

	}


    @Test
    void testCreateMentorWithEmailAlreadyExists() {
    	MentorDto mentorDto = new MentorDto(1L, 12345L, "pragathi", "pragathi@gmail.com",
                "A", "Senior Mentor", "Engineering", "Java",
                "Occupied", 2, "BATCH001");
     when(mentorRepository.findByEmail(mentorDto.getEmail())).thenReturn(Optional.of(new Mentor()));

        assertThrows(EmailAlreadyExistException.class, () -> mentorService.createMentor(mentorDto));
    }

	@Test
    void testGetAllMentors() {
        List<Mentor> mentorEntities = new ArrayList<>();
        mentorEntities.add( new Mentor(1L, 12345L, "pragathi", "pragathi@gmail.com",
                "A", "Senior Mentor", "Engineering", "Java",
                "Occupied", 2, "BATCH001"));

        when(mentorRepository.findAll()).thenReturn(mentorEntities);

        List<MentorDto> mentorDtos = mentorService.getAllMentors();

        assertNotNull(mentorDtos);
        assertEquals(mentorEntities.size(), mentorDtos.size());
    }

    @Test
    void testUpdateMentor() {
    	MentorDto mentorDto = new MentorDto(1L, 12345L, "pragathi", "pragathi@gmail.com",
                "A", "Senior Mentor", "Engineering", "java",
                "Occupied", 2, "BATCH001");        
    	Mentor existingMentor = new Mentor(1L, 12345L, "pragathi", "pragathi@gmail.com",
                "A", "Senior Mentor", "Engineering", "Java",
                "Occupied", 2, "BATCH001");

        when(mentorRepository.findById(mentorDto.getId())).thenReturn(Optional.of(existingMentor));
        when(mentorRepository.save(any(Mentor.class))).thenReturn(existingMentor);

        MentorDto updatedMentorDto = mentorService.updateMentor(mentorDto);

        assertNotNull(updatedMentorDto);
    }

    @Test
    void testDeleteMentor() {
        Long empId = 12345L;
        Mentor existingMentor = new Mentor(1L, 12345L, "pragathi", "pragathi@gmail.com",
                "A", "Senior Mentor", "Engineering", "Java",
                "Occupied", 2, "BATCH001");      
        when(mentorRepository.findByEmpId(empId)).thenReturn(Optional.of(existingMentor));

        String result = mentorService.deleteMentor(existingMentor.getEmpId());

        assertEquals("Deleted Successfully!!", result);
        verify(mentorRepository, times(1)).deleteByEmpId(empId);
    }
//    @Test
//    void testGetMentorById() {
////        Long mentorId = 1L;
//        Mentor mentorEntity = new Mentor(1L, 12345L, "pragathi", "pragathi@gmail.com",
//                "A", "Senior Mentor", "Engineering", "Java",
//                "Occupied", 2, "AWS01");
//        when(mentorRepository.findById(mentorEntity.getId())).thenReturn(java.util.Optional.of(mentorEntity));
//
//        BatchDto batchDto = new BatchDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",3);
//        when(webClient.get()).thenReturn(mock(WebClient.RequestHeadersUriSpec.class));
//       
//
//        APIResponseDto apiResponseDto = mentorService.getMentorById(mentorEntity.getId());
//
//        assertNotNull(apiResponseDto);
//        assertNotNull(apiResponseDto.getMentor());
//        assertEquals(mentorEntity.getName(), apiResponseDto.getMentor().getName());
//        assertNotNull(apiResponseDto.getBatch());
//        assertEquals(batchDto.getBatchName(), apiResponseDto.getBatch().getBatchName());
//        assertEquals(batchDto.getStartDate(), apiResponseDto.getBatch().getStartDate());
//        assertEquals(batchDto.getEndDate(), apiResponseDto.getBatch().getEndDate());
//        assertEquals(batchDto.getBatchCode(), apiResponseDto.getBatch().getBatchCode());
//        assertEquals(batchDto.getBatchHrs(), apiResponseDto.getBatch().getBatchHrs());
//    }
//    @Test
//    void testGetMentorById() {
//        Long mentorId = 1L;
//        
//        // Mock mentor
//        Mentor mockMentor = new Mentor();
//        mockMentor.setId(mentorId);
//        mockMentor.setBatchCode("CL01");
//
//        // Mock batchDto
//        BatchDto mockBatchDto = new BatchDto();
//        mockBatchDto.setBatchCode("CL01");
//        mockBatchDto.setBatchName("Cloud");
//
//        // Mock the behavior of mentorRepository.findById
//        when(mentorRepository.findById(mentorId)).thenReturn(Optional.of(mockMentor));
//
////        // Mock the behavior of WebClient
////        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
////        when(webClientBuilder.build()).thenReturn(webClient);
////        when(webClient.get()).thenReturn(webClient.RequestHeadersUriSpec);
////        when(webClient.uri(anyString())).thenReturn(webClient.RequestHeadersSpec);
////        when(webClient.retrieve()).thenReturn(webClient.ResponseSpec);
////        when(webClient.bodyToMono(BatchDto.class)).thenReturn(Mono.just(mockBatchDto));
//        when(webClient.get()).thenReturn(mock(WebClient.RequestHeadersUriSpec.class));
//
//        // Call the actual method
//        APIResponseDto result = mentorService.getMentorById(mentorId);
//
//        // Assertions
//        assertEquals(mockMentor.getId(), result.getMentor().getId());
//        assertEquals(mockBatchDto.getBatchCode(), result.getBatch().getBatchCode());
//        assertEquals(mockBatchDto.getBatchName(), result.getBatch().getBatchName());
//    }


    @Test
    void testGetMentorByIDWithResourceNotFoundException() {
        Long mentorId = 1L;
        when(mentorRepository.findById(mentorId)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> mentorService.getMentorById(mentorId));
    }

   
   

    @Test
    public void testGetDefaultBatch() {
        
        Long empId = 123L;
        
       
        Mentor mentorEntity = new Mentor(1L, 12345L, "pragathi", "pragathi@gmail.com",
              "A", "Senior Mentor", "Engineering", "Java",
              "Occupied", 2, "BATCH001");
        
        when(mentorRepository.findByEmpId(empId)).thenReturn(Optional.of(mentorEntity));

        
       
        APIResponseDto result = mentorService.getDafaultBatch(empId, null);

    
        assertEquals("Cloud", result.getBatch().getBatchName());
        
        verify(mentorRepository, times(1)).findByEmpId(empId);
    }
    

    

    
}



