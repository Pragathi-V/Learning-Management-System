package upskill.casestudy.mentorService.entity;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;



class MentorTest {

	

	    @Test
	    public void testMentorEntity() {
	        // Given
	        Mentor mentor = new Mentor();
	        mentor.setId(1);
	        mentor.setEmpId(12345L);
	        mentor.setName("pragathi");
	        mentor.setEmail("pragathi@gmail.com");
	        mentor.setGrade("A");
	        mentor.setDesignation("Senior Mentor");
	        mentor.setStream("Engineering");
	        mentor.setSkills(new String[]{"Java", "Spring", "Hibernate"});
	        mentor.setStatus("Occupied");
	        mentor.setNumOfBatches(2);
	        mentor.setBatchCode("BATCH001");

	        
	        assertThat(mentor.getId()).isEqualTo(1);
	        assertThat(mentor.getEmpId()).isEqualTo(12345L);
	        assertThat(mentor.getName()).isEqualTo("pragathi");
	        assertThat(mentor.getEmail()).isEqualTo("pragathi@gmail.com");
	        assertThat(mentor.getGrade()).isEqualTo("A");
	        assertThat(mentor.getDesignation()).isEqualTo("Senior Mentor");
	        assertThat(mentor.getStream()).isEqualTo("Engineering");
	        assertThat(mentor.getSkills()).containsExactly("Java", "Spring", "Hibernate");
	        assertThat(mentor.getStatus()).isEqualTo("Occupied");
	        assertThat(mentor.getNumOfBatches()).isEqualTo(2);
	        assertThat(mentor.getBatchCode()).isEqualTo("BATCH001");
	    }

	    
	}