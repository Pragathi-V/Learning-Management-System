package upskill.casestudy.studentservice.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import upskill.casestudy.studentservice.dto.APIResponseDto;
import upskill.casestudy.studentservice.dto.BatchDto;
import upskill.casestudy.studentservice.dto.StudentDto;
import upskill.casestudy.studentservice.entity.Student;
import upskill.casestudy.studentservice.exception.EmailAlreadyExistException;
import upskill.casestudy.studentservice.exception.ResourceNotFoundException;
import upskill.casestudy.studentservice.mapper.StudentMapper;
import upskill.casestudy.studentservice.repository.StudentRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentServiceImplTest {

    @Test
    void testCreateStudent() {
        // Arrange
        StudentRepository studentRepository = mock(StudentRepository.class);
        WebClient webClient = mock(WebClient.class);
        StudentServiceImpl studentService = new StudentServiceImpl(webClient, studentRepository);

        StudentDto studentDto = new StudentDto();
        Student student = StudentMapper.mapToStudent(studentDto);

        when(studentRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(studentRepository.save(any())).thenReturn(student);

        // Act
        StudentDto savedStudentDto = studentService.createStudent(studentDto);

        // Assert
        assertNotNull(savedStudentDto);
//        assertEquals(studentDto, savedStudentDto);
    }

    @Test
    void testCreateStudentWithEmailAlreadyExists() {
        // Arrange
        StudentRepository studentRepository = mock(StudentRepository.class);
        WebClient webClient = mock(WebClient.class);
        StudentServiceImpl studentService = new StudentServiceImpl(webClient, studentRepository);

        StudentDto studentDto = new StudentDto();
        when(studentRepository.findByEmail(any())).thenReturn(Optional.of(new Student()));

        // Act and Assert
        assertThrows(EmailAlreadyExistException.class, () -> studentService.createStudent(studentDto));
    }

   
    @Test
    void testGetStudentByIdNotFound() {
        // Arrange
        StudentRepository studentRepository = mock(StudentRepository.class);
        WebClient webClient = mock(WebClient.class);
        StudentServiceImpl studentService = new StudentServiceImpl(webClient, studentRepository);

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentByID(1L));
    }

    @Test
    void testGetAllStudents() {
        // Arrange
        StudentRepository studentRepository = mock(StudentRepository.class);
        WebClient webClient = mock(WebClient.class);
        StudentServiceImpl studentService = new StudentServiceImpl(webClient, studentRepository);

        List<Student> students = Collections.singletonList(new Student());
        when(studentRepository.findAll()).thenReturn(students);

        // Act
        List<StudentDto> studentDtos = studentService.getAllStudents();

        // Assert
        assertNotNull(studentDtos);
        assertEquals(students.size(), studentDtos.size());
    }

    @Test
    void testUpdateStudent() {
        // Arrange
        StudentRepository studentRepository = mock(StudentRepository.class);
        WebClient webClient = mock(WebClient.class);
        StudentServiceImpl studentService = new StudentServiceImpl(webClient, studentRepository);

        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(1L);
        Student student = StudentMapper.mapToStudent(studentDto);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any())).thenReturn(student);

        // Act
        StudentDto updatedStudentDto = studentService.updateStudent(studentDto);

        // Assert
        assertNotNull(updatedStudentDto);
        assertEquals(studentDto.getStudentId(), updatedStudentDto.getStudentId());
    }

    @Test
    void testUpdateStudentNotFound() {
        // Arrange
        StudentRepository studentRepository = mock(StudentRepository.class);
        WebClient webClient = mock(WebClient.class);
        StudentServiceImpl studentService = new StudentServiceImpl(webClient, studentRepository);

        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentService.updateStudent(studentDto));
    }

    @Test
    void testDeleteStudent() {
        // Arrange
        StudentRepository studentRepository = mock(StudentRepository.class);
        WebClient webClient = mock(WebClient.class);
        StudentServiceImpl studentService = new StudentServiceImpl(webClient, studentRepository);

        Student student = new Student();
        student.setStudentId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Act
        String result = studentService.deleteStudent(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Deleted Successfully!!", result);
    }

    @Test
    void testDeleteStudentNotFound() {
        // Arrange
        StudentRepository studentRepository = mock(StudentRepository.class);
        WebClient webClient = mock(WebClient.class);
        StudentServiceImpl studentService = new StudentServiceImpl(webClient, studentRepository);

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentService.deleteStudent(1L));
    }
}
