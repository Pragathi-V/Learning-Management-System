package upskill.casestudy.mentorService.dto;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class APIResponseDtoTest {

	@Test

	void testAPIResponseDtoConstructorAndGetters() {

		 MentorDto mentorDto = new MentorDto(1, 12345L, "pragathi", "pragathi@gmail.com",
	                "A", "Senior Mentor", "Engineering", new String[]{"Java", "Spring", "Hibernate"},
	                "Occupied", 2, "BATCH001");
	BatchDto batchDto = new BatchDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",2);

	APIResponseDto apiResponseDto = new APIResponseDto(mentorDto, batchDto);

	assertNotNull(apiResponseDto);

	assertEquals(mentorDto, apiResponseDto.getMentor());

	assertEquals(batchDto, apiResponseDto.getBatch());

	}

	@Test

	void testAPIResponseDtoDefaultConstructorAndSetters() {

	APIResponseDto apiResponseDto = new APIResponseDto();

	assertNotNull(apiResponseDto);

	 MentorDto mentorDto = new MentorDto(1, 12345L, "pragathi", "pragathi@gmail.com",
             "A", "Senior Mentor", "Engineering", new String[]{"Java", "Spring", "Hibernate"},
             "Occupied", 2, "BATCH001");
	BatchDto batchDto = new BatchDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01", 2);

	apiResponseDto.setMentor(mentorDto);

	apiResponseDto.setBatch(batchDto);

	assertEquals(mentorDto, apiResponseDto.getMentor());

	assertEquals(batchDto, apiResponseDto.getBatch());

	}

	}