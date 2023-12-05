package upskill.casestudy.mentorService.dto;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class MentorDtoTest {

	@Test

	public void testValidMentorDto() {

		
		        MentorDto mentorDto = new MentorDto(1, 12345L, "pragathi", "pragathi@gmail.com",
		                "A", "Senior Mentor", "Engineering", new String[]{"Java", "Spring", "Hibernate"},
		                "Occupied", 2, "BATCH001");

	assertThat(mentorDto.getId()).isEqualTo(1L);

	assertThat(mentorDto.getName()).isEqualTo("pragathi");

	assertThat(mentorDto.getEmail()).isEqualTo("pragathi@gmail.com");
	
	assertThat(mentorDto.getGrade()).isEqualTo("A");
	assertThat(mentorDto.getDesignation()).isEqualTo("Senior Mentor");
	assertThat(mentorDto.getSkills()).isEqualTo(new String[]{"Java", "Spring", "Hibernate"});
	assertThat(mentorDto.getStream()).isEqualTo("Engineering");


	assertThat(mentorDto.getNumOfBatches()).isEqualTo(40);

	assertThat(mentorDto.getStatus()).isEqualTo("Occupied");

	assertThat(mentorDto.getBatchCode()).isEqualTo("C123");

	}
	
	@Test

	public void testDefaultConstructor() {

	MentorDto mentorDto = new MentorDto();

	assertNull(mentorDto.getName());

	assertNull(mentorDto.getEmail());
	
	assertNull(mentorDto.getGrade());
	assertNull(mentorDto.getDesignation());
	assertNull(mentorDto.getStatus());
	assertNull(mentorDto.getStream());

	assertNull(mentorDto.getSkills());

	assertNull(mentorDto.getBatchCode());

	}

	
}
