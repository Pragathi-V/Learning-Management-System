package upskill.casestudy.studentservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import upskill.casestudy.studentservice.dto.APIResponseDto;
import upskill.casestudy.studentservice.dto.StudentDto;
import upskill.casestudy.studentservice.service.StudentService;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Test
    void testCreateStudent() {
        // Arrange
        StudentService studentService = mock(StudentService.class);
        StudentDto studentDto = new StudentDto();
        when(studentService.createStudent(any())).thenReturn(studentDto);

        StudentController studentController = new StudentController(studentService);

        // Act
        ResponseEntity<StudentDto> responseEntity = studentController.createStudent(studentDto);

        // Assert
        verify(studentService, times(1)).createStudent(any());
        assertSame(studentDto, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testGetStudentById() {
        // Arrange
        StudentService studentService = mock(StudentService.class);
        APIResponseDto apiResponseDto = new APIResponseDto();
        when(studentService.getStudentByID(1L)).thenReturn(apiResponseDto);

        StudentController studentController = new StudentController(studentService);

        // Act
        ResponseEntity<APIResponseDto> responseEntity = studentController.getStudentById(1L);

        // Assert
        verify(studentService, times(1)).getStudentByID(1L);
        assertSame(apiResponseDto, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllStudent() {
        // Arrange
        StudentService studentService = mock(StudentService.class);
        List<StudentDto> students = Arrays.asList(new StudentDto(), new StudentDto());
        when(studentService.getAllStudents()).thenReturn(students);

        StudentController studentController = new StudentController(studentService);

        // Act
        ResponseEntity<List<StudentDto>> responseEntity = studentController.getAllStudent();

        // Assert
        verify(studentService, times(1)).getAllStudents();
        assertSame(students, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateStudent() {
        // Arrange
        StudentService studentService = mock(StudentService.class);
        StudentDto studentDto = new StudentDto();
        when(studentService.updateStudent(any())).thenReturn(studentDto);

        StudentController studentController = new StudentController(studentService);

        // Act
        ResponseEntity<StudentDto> responseEntity = studentController.updateStudent(1L, new StudentDto());

        // Assert
        verify(studentService, times(1)).updateStudent(any());
        assertSame(studentDto, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteStudent() {
        // Arrange
        StudentService studentService = mock(StudentService.class);

        StudentController studentController = new StudentController(studentService);

        // Act
        ResponseEntity<String> responseEntity = studentController.deleteStudent(1L);

        // Assert
        verify(studentService, times(1)).deleteStudent(1L);
        assertEquals("Student successfully deleted!!", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}

