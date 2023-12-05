package upskill.casestudy.studentservice.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentDtoTest {

    @Test
    public void testCreateStudentDto() {
        StudentDto studentDto = new StudentDto();
        assertNotNull(studentDto);
    }

    @Test
    public void testStudentDtoGettersAndSetters() {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(1L);
        studentDto.setStudentName("pragathi");
        studentDto.setEmail("pragathi@gmail.com");
        studentDto.setBatchCode("C123");

        assertEquals(1L, studentDto.getStudentId());
        assertEquals("pragathi", studentDto.getStudentName());
        assertEquals("pragathi@gmail.com", studentDto.getEmail());
        assertEquals("C123", studentDto.getBatchCode());
    }
}
