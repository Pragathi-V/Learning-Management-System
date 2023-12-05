package upskill.casestudy.studentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import upskill.casestudy.studentservice.dto.APIResponseDto;
import upskill.casestudy.studentservice.dto.StudentDto;
import upskill.casestudy.studentservice.mapper.StudentMapper;
import upskill.casestudy.studentservice.service.StudentService;
import upskill.casestudy.studentservice.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Student-Controller",
        description = "Student Controller Exposes REST APIs for Student-Service")
@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private StudentService studentService;
    @Operation(
            summary = "Save Student REST API",
            description = "Save Student REST API is used to save student object in database"
    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid StudentDto studentDto) {
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get student By ID REST API",
            description = "Get student By REST API is used to get a student object from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{studentId}")
    public ResponseEntity<APIResponseDto> getStudentById(@PathVariable Long studentId) {
        APIResponseDto apiResponseDto = studentService.getStudentByID(studentId);
        return new ResponseEntity<APIResponseDto>(apiResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Get student By REST API",
            description = "Get student By REST API is used to get a student object from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping()
    public ResponseEntity<List<StudentDto>> getAllStudent(){
        List<StudentDto> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Operation(
            summary = "Put student By REST API",
            description = "put student By REST API is used to update a student object from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId, @RequestBody @Valid  StudentDto studentDto){
        studentDto.setStudentId(studentId);
        StudentDto updateStudent = studentService.updateStudent(studentDto);
        return new ResponseEntity<StudentDto>(updateStudent, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete student By ID REST API",
            description = "Delete student By REST API is used to delete a student  from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<String>("Student successfully deleted!!", HttpStatus.OK);
    }
    
    @Operation(
            summary = "Get student By batch code REST API",
            description = "Get student REST API is used to get a student object from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/code/{batchCode}")
    public ResponseEntity<List<StudentDto>> getStudentByBatchCode(@PathVariable String batchCode) {
        List<StudentDto> studentDto = studentService.getStudentByBatchCode(batchCode);
        return new ResponseEntity<List<StudentDto>>(studentDto, HttpStatus.OK);
    }
}
