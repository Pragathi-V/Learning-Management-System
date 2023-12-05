package upskill.casestudy.studentservice.mapper;

import upskill.casestudy.studentservice.dto.StudentDto;
import upskill.casestudy.studentservice.entity.Student;

public class StudentMapper {

    //convert JPA Entity to DTO
    public static StudentDto mapToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto(
        		student.getStudentId(),
        		student.getStudentName(),
        		student.getEmail(),
        		student.getBatchCode()
                
        );
        return studentDto;
    }

    //convert DTO to JPA Entity
    public static Student mapToStudent(StudentDto studentDto) {
        Student student = new Student(
        		studentDto.getStudentId(),
        		studentDto.getStudentName(),
        		studentDto.getEmail(),
        		studentDto.getBatchCode()
        );
        return student;
    }
}

