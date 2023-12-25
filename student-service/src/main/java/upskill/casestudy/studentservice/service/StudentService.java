package upskill.casestudy.studentservice.service;

import java.util.List;

import upskill.casestudy.studentservice.dto.APIResponseDto;
import upskill.casestudy.studentservice.dto.StudentDto;
import upskill.casestudy.studentservice.dto.MentorDto;

public interface StudentService {


    public StudentDto createStudent(StudentDto studentDto);

    public APIResponseDto getStudentByID(Long studentId);
//    public StudentDto getStudentByID(Long StudentId);

    public List<StudentDto> getAllStudents();

    public StudentDto updateStudent(StudentDto studentDto);

    public String deleteStudent(Long studentId);
    
    public List<StudentDto> getStudentByBatchCode(String batchCode);

   
}


