package upskill.casestudy.mentorService.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import upskill.casestudy.mentorService.dto.MentorDto;
import upskill.casestudy.mentorService.entity.Mentor;

class MentorMapperTest {

	@Test
	public void testMapToMentorDto() {

		Mentor mentor = new Mentor(1L, 12345L, "pragathi", "pragathi@gmail.com",
                "A", "Senior Mentor", "Engineering", "Java",
                "Occupied", 2, "BATCH001");

		MentorDto mentorDto = MentorMapper.mapToMentorDto(mentor);

		assertThat(mentorDto.getId()).isEqualTo(1L);

		assertThat(mentorDto.getName()).isEqualTo("pragathi");

		assertThat(mentorDto.getEmail()).isEqualTo("pragathi@gmail.com");

		assertThat(mentorDto.getNumOfBatches()).isEqualTo(2);
		assertThat(mentorDto.getGrade()).isEqualTo("A");

		assertThat(mentorDto.getSkills()).isEqualTo("Java");
		assertThat(mentorDto.getStream()).isEqualTo("Engineering");
		assertThat(mentorDto.getDesignation()).isEqualTo("Senior Mentor");


		assertThat(mentorDto.getStatus()).isEqualTo("Occupied");

		assertThat(mentorDto.getBatchCode()).isEqualTo("BATCH001");

		}

		@Test

		public void testMapToMentor() {

			MentorDto mentorDto = new MentorDto(1L, 12345L, "pragathi", "pragathi@gmail.com",
	                "A", "Senior Mentor", "Engineering", "Java",
	                "Occupied", 2, "BATCH001");

		Mentor mentor = MentorMapper.mapToMentor(mentorDto);

		assertThat(mentor.getId()).isEqualTo(1L);

		assertThat(mentor.getName()).isEqualTo("pragathi");

		assertThat(mentor.getEmail()).isEqualTo("pragathi@gmail.com");

		assertThat(mentor.getNumOfBatches()).isEqualTo(2);
		assertThat(mentor.getGrade()).isEqualTo("A");

		assertThat(mentor.getSkills()).isEqualTo("Java");
		assertThat(mentor.getStream()).isEqualTo("Engineering");
		assertThat(mentor.getDesignation()).isEqualTo("Senior Mentor");

		assertThat(mentor.getStatus()).isEqualTo("Occupied");

		assertThat(mentor.getBatchCode()).isEqualTo("BATCH001");

		}

		}