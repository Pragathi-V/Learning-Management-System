package upskill.casestudy.studentservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import upskill.casestudy.studentservice.dto.APIResponseDto;
import upskill.casestudy.studentservice.dto.BatchDto;
import upskill.casestudy.studentservice.dto.MentorDto;
import upskill.casestudy.studentservice.dto.StudentDto;
import upskill.casestudy.studentservice.entity.Student;
import upskill.casestudy.studentservice.exception.EmailAlreadyExistException;
import upskill.casestudy.studentservice.exception.ResourceNotFoundException;
import upskill.casestudy.studentservice.mapper.StudentMapper;
import upskill.casestudy.studentservice.repository.StudentRepository;
import upskill.casestudy.studentservice.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private WebClient webClient;

    private StudentRepository studentRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(studentDto.getEmail());
        if (optionalStudent.isPresent()) {
            throw new EmailAlreadyExistException("Email Already Exists for Student");
        }
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        StudentDto savedStudentDto = StudentMapper.mapToStudentDto(savedStudent);
        return savedStudentDto;
    }

//    @Override
//    public StudentDto getStudentByID(Long studentId) {
//        Student student = studentRepository.findById(studentId)
//    .orElseThrow(() -> new ResourceNotFoundException("student", "id", studentId));
//       StudentDto studentDto= StudentMapper.mapToStudentDto(student);
//        return studentDto;
//
//    }

    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDafaultBatch")
    @Override
    public APIResponseDto getStudentByID(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("student", "id", studentId));
 
        BatchDto batchDto = webClient.get()
				.uri("http://localhost:8081/api/batch/code/" +student.getBatchCode())
				.retrieve()
				.bodyToMono(BatchDto.class)
				.block();
        StudentDto studentDto = StudentMapper.mapToStudentDto(student);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setBatch(batchDto);
        apiResponseDto.setStudent(studentDto);
        return apiResponseDto;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> student = studentRepository.findAll();
        return student.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {

        Student student = studentRepository.findById(studentDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("student", "id", studentDto.getStudentId()));
        student.setStudentName(studentDto.getStudentName());
        student.setEmail(studentDto.getEmail());
        student.setBatchCode(studentDto.getBatchCode());
        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updatedStudent);
    }

    @Override
    public String deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("student", "id", studentId));
        studentRepository.deleteById(studentId);
        return "Deleted Successfully!!";

    }

	


  //fallback method
  		public APIResponseDto getDafaultBatch(Long studentId , Exception exception) {
  			LOGGER.info("inside getDafaultBatch() method");
  			Student student= studentRepository.findById(studentId).get();
  			   
  			 BatchDto batchDto= new BatchDto();
  			 batchDto.setBatchName("Cloud");
  			 batchDto.setStartDate(LocalDate.of(2024, 01, 03));
  			 batchDto.setEndDate(LocalDate.of(2024, 05, 23));
  			 batchDto.setBatchCode("CL01");
  			
  			   StudentDto	studentDto = StudentMapper.mapToStudentDto(student);
  			
  			 APIResponseDto apiResponseDto= new APIResponseDto();
  			 apiResponseDto.setBatch(batchDto);
  			 apiResponseDto.setStudent(studentDto);
  			
  			return apiResponseDto;
  		}

		@Override
		public List<StudentDto> getStudentByBatchCode(String batchCode) {
			List<Student> student = studentRepository.findByBatchCode(batchCode);
			List<StudentDto> studentDto = student.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());
			return studentDto;
		}
}
