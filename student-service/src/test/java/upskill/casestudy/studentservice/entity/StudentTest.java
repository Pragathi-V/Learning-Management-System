package upskill.casestudy.studentservice.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        assertNotNull(student);
    }

    @Test
    public void testStudentGettersAndSetters() {
        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentName("pragathi");
        student.setEmail("pragathi@gmail.com");
        student.setBatchCode("C123");

        assertEquals(1L, student.getStudentId());
        assertEquals("pragathi", student.getStudentName());
        assertEquals("pragathi@gmail.com", student.getEmail());
        assertEquals("C123", student.getBatchCode());
    }
}