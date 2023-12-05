package upskill.casestudy.mentorService.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import upskill.casestudy.mentorService.dto.MentorDto;
import upskill.casestudy.mentorService.entity.Mentor;

class MentorMapperTest {

	@Test
	public void testMapToMentorDto() {

		Mentor mentor = new Mentor(1L, "pragathi", "pragathi@gmail.com", 40, "Occupied", "C123");

		MentorDto mentorDto = MentorMapper.mapToMentorDto(mentor);

		assertThat(mentorDto.getId()).isEqualTo(1L);

		assertThat(mentorDto.getName()).isEqualTo("pragathi");

		assertThat(mentorDto.getEmail()).isEqualTo("pragathi@gmail.com");

		assertThat(mentorDto.getWorkingHours()).isEqualTo(40);

		assertThat(mentorDto.getStatus()).isEqualTo("Occupied");

		assertThat(mentorDto.getCourseCode()).isEqualTo("C123");

		}

		@Test

		public void testMapToMentor() {

		MentorDto mentorDto = new MentorDto(1L, "pragathi", "pragathi@gmail.com", 40, "Occupied", "C123");

		Mentor mentor = MentorMapper.mapToMentor(mentorDto);

		assertThat(mentor.getId()).isEqualTo(1L);

		assertThat(mentor.getName()).isEqualTo("pragathi");

		assertThat(mentor.getEmail()).isEqualTo("pragathi@gmail.com");

		assertThat(mentor.getWorkingHours()).isEqualTo(40);

		assertThat(mentor.getStatus()).isEqualTo("Occupied");

		assertThat(mentor.getCourseCode()).isEqualTo("C123");

		}

		}