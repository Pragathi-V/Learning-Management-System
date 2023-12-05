package upskill.casestudy.mentorService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.casestudy.mentorService.dto.APIResponseDto;
import upskill.casestudy.mentorService.dto.MentorDto;
import upskill.casestudy.mentorService.service.MentorService;

import java.util.List;

@Tag(
        name = "Mentor-Controller",
        description = "Mentor Controller Exposes REST APIs for Mentor-Service")
@RestController
@RequestMapping("/api/mentor")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MentorController {

    private MentorService mentorService;
    @Operation(
            summary = "Save Mentor REST API",
            description = "Save Mentor REST API is used to save mentor object in database"
    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<MentorDto> createMentor(@RequestBody @Valid MentorDto mentorDto) {
        MentorDto savedMentor = mentorService.createMentor(mentorDto);
        return new ResponseEntity<>(savedMentor, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get mentor By ID REST API",
            description = "Get mentor By REST API is used to get a mentor object from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/empId/{empId}")
    public ResponseEntity<APIResponseDto> getMentorByEmpId(@PathVariable("empId") Long empId) {
        APIResponseDto apiResponseDto = mentorService.getMentorByEmpId(empId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Get mentor By REST API",
            description = "Get mentor By REST API is used to get a mentor object from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    
    @GetMapping()
    public ResponseEntity<List<MentorDto>> getAllMentor(){
        List<MentorDto> mentors = mentorService.getAllMentors();
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    @Operation(
            summary = "Put mentor By REST API",
            description = "put mentor By REST API is used to update a mentor object from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("empId/{empId}")
    public ResponseEntity<MentorDto> updateMentor(@PathVariable Long empId, @RequestBody @Valid  MentorDto mentorDto){
        mentorDto.setEmpId(empId);
        MentorDto updateMentor = mentorService.updateMentor(mentorDto);
        return new ResponseEntity<MentorDto>(updateMentor, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete mentor By ID REST API",
            description = "Delete mentor By REST API is used to delete a mentor  from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{empId}")
    public ResponseEntity<String> deleteMentor(@PathVariable Long empId){
        mentorService.deleteMentor(empId);
        return new ResponseEntity<String>("User successfully deleted!!", HttpStatus.OK);
    }
    @Operation(
            summary = "Update mentor By ID REST API",
            description = "Update mentor By REST API is used to delete a mentor  from the database"
    )
    
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<MentorDto> updateMentorById(@PathVariable Long id, @RequestBody @Valid  MentorDto mentorDto){
        mentorDto.setId(id);
        MentorDto updateMentor = mentorService.updateMentor(mentorDto);
        return new ResponseEntity<MentorDto>(updateMentor, HttpStatus.OK);
    }
}
