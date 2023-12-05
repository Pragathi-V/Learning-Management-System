package upskill.casestudy.mentorService.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import upskill.casestudy.mentorService.dto.MentorDto;
import upskill.casestudy.mentorService.entity.Mentor;

class MentorMapperTest {

	@Test
	public void testMapToMentorDto() {

		 Mentor mentor = new Mentor(1, 12345L, "pragathi", "pragathi@gmail.com",
	                "A", "Senior Mentor", "Engineering", new String[]{"Java", "Spring", "Hibernate"},
	                "Occupied", 2, "BATCH001");
		MentorDto mentorDto = MentorMapper.mapToMentorDto(mentor);

		assertThat(mentorDto.getId()).isEqualTo(1L);

		assertThat(mentorDto.getName()).isEqualTo("pragathi");

		assertThat(mentorDto.getEmail()).isEqualTo("pragathi@gmail.com");

		assertThat(mentorDto.getNumOfBatches()).isEqualTo(4);
		assertThat(mentorDto.getGrade()).isEqualTo("A4");

		assertThat(mentorDto.getSkills()).isEqualTo("java");
		assertThat(mentorDto.getStream()).isEqualTo("JFS");
		assertThat(mentorDto.getDesignation()).isEqualTo("Analyst");


		assertThat(mentorDto.getStatus()).isEqualTo("Occupied");

		assertThat(mentorDto.getBatchCode()).isEqualTo("C123");

		}

		@Test

		public void testMapToMentor() {

			 MentorDto mentorDto = new MentorDto(1, 12345L, "pragathi", "pragathi@gmail.com",
		                "A", "Senior Mentor", "Engineering", new String[]{"Java", "Spring", "Hibernate"},
		                "Occupied", 2, "BATCH001");
		Mentor mentor = MentorMapper.mapToMentor(mentorDto);

		assertThat(mentor.getId()).isEqualTo(1L);

		assertThat(mentor.getName()).isEqualTo("pragathi");

		assertThat(mentor.getEmail()).isEqualTo("pragathi@gmail.com");

		assertThat(mentor.getNumOfBatches()).isEqualTo(40);
		assertThat(mentor.getGrade()).isEqualTo("A4");

		assertThat(mentor.getSkills()).isEqualTo("java");
		assertThat(mentor.getStream()).isEqualTo("JFS");
		assertThat(mentor.getDesignation()).isEqualTo("Analyst");

		assertThat(mentor.getStatus()).isEqualTo("Occupied");

		assertThat(mentor.getBatchCode()).isEqualTo("C123");

		}

		}