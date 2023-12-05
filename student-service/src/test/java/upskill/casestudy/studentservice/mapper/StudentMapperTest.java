package upskill.casestudy.studentservice.mapper;

import org.junit.jupiter.api.Test;

import upskill.casestudy.studentservice.dto.StudentDto;
import upskill.casestudy.studentservice.entity.Student;

import static org.junit.jupiter.api.Assertions.*;

public class StudentMapperTest {

    @Test
    public void testMapToStudentDto() {
        Student student = new Student(1L, "pragathi", "pragathi@gmail.com", "CS101");
        StudentDto studentDto = StudentMapper.mapToStudentDto(student);

        assertEquals(student.getStudentId(), studentDto.getStudentId());
        assertEquals(student.getStudentName(), studentDto.getStudentName());
        assertEquals(student.getEmail(), studentDto.getEmail());
        assertEquals(student.getBatchCode(), studentDto.getBatchCode());
    }

    @Test
    public void testMapToStudent() {
        StudentDto studentDto = new StudentDto(1L, "pragathi", "pragathi@gmail.com", "CS101");
        Student student = StudentMapper.mapToStudent(studentDto);

        assertEquals(studentDto.getStudentId(), student.getStudentId());
        assertEquals(studentDto.getStudentName(), student.getStudentName());
        assertEquals(studentDto.getEmail(), student.getEmail());
        assertEquals(studentDto.getBatchCode(), student.getBatchCode());
    }

   
}

