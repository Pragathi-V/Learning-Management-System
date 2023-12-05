package upskill.casestudy.mentorService.dto;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class APIResponseDtoTest {

	@Test

	void testAPIResponseDtoConstructorAndGetters() {

	MentorDto mentorDto = new MentorDto(1L, "pragathi", "pragathi@gmail.com", 40, "Occupied", "C123");

	CourseDto courseDto = new CourseDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01");

	APIResponseDto apiResponseDto = new APIResponseDto(mentorDto, courseDto);

	assertNotNull(apiResponseDto);

	assertEquals(mentorDto, apiResponseDto.getMentor());

	assertEquals(courseDto, apiResponseDto.getCourse());

	}

	@Test

	void testAPIResponseDtoDefaultConstructorAndSetters() {

	APIResponseDto apiResponseDto = new APIResponseDto();

	assertNotNull(apiResponseDto);

	MentorDto mentorDto = new MentorDto(1L, "pragathi", "pragathi@gmail.com", 40, "Occupied", "C123");

	CourseDto courseDto = new CourseDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01");

	apiResponseDto.setMentor(mentorDto);

	apiResponseDto.setCourse(courseDto);

	assertEquals(mentorDto, apiResponseDto.getMentor());

	assertEquals(courseDto, apiResponseDto.getCourse());

	}

	}