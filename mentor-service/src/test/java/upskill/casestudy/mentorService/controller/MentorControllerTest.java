package upskill.casestudy.mentorService.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import upskill.casestudy.mentorService.dto.APIResponseDtoMentor;
import upskill.casestudy.mentorService.dto.MentorDto;
import upskill.casestudy.mentorService.service.MentorService;

class MentorControllerTest {

    private MentorService mentorService = mock(MentorService.class);
    private MentorController mentorController = new MentorController(mentorService);

    @Test
    public void testCreateMentor() {
    	MentorDto mentorDto = new MentorDto(1L, 12345L, "pragathi", "pragathi@gmail.com",
                "A", "Senior Mentor", "Engineering", "Java",
                "Occupied", 2, "BATCH001");
        when(mentorService.createMentor(mentorDto)).thenReturn(mentorDto);

        ResponseEntity<MentorDto> responseEntity = mentorController.createMentor(mentorDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        
    }

    @Test
    public void testGetMentorById() {
        Long empId = 1L;
        APIResponseDtoMentor apiResponseDto = new APIResponseDtoMentor();
        when(mentorService.getMentorByEmpId(empId)).thenReturn(apiResponseDto);

        ResponseEntity<APIResponseDtoMentor> responseEntity = mentorController.getMentorByEmpId(empId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
       
    }

    @Test
    public void testGetAllMentor() {
        List<MentorDto> mentorList = Arrays.asList( new MentorDto(1L, 12345L, "pragathi", "pragathi@gmail.com",
                "A", "Senior Mentor", "Engineering", "Java",
                "Occupied", 2, "BATCH001"));

        when(mentorService.getAllMentors()).thenReturn(mentorList);

        ResponseEntity<List<MentorDto>> responseEntity = mentorController.getAllMentor();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testUpdateMentor() {
        long mentorId = 1L;
        MentorDto mentorDto = new MentorDto(1L, 12345L, "pragathi", "pragathi@gmail.com",
                "A", "Senior Mentor", "Engineering", "Java",
                "Occupied", 2, "BATCH001");

        when(mentorService.updateMentor(mentorDto)).thenReturn(mentorDto);

        ResponseEntity<MentorDto> responseEntity = mentorController.updateMentor(mentorId, mentorDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
       
    }

    @Test
    public void testDeleteMentor() {
        long mentorId = 1L;
        ResponseEntity<String> responseEntity = mentorController.deleteMentor(mentorId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}

