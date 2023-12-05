package upskill.casestudy.mentorService.service;

import upskill.casestudy.mentorService.dto.APIResponseDto;
import upskill.casestudy.mentorService.dto.MentorDto;

import java.util.List;

public interface MentorService {

    public MentorDto createMentor(MentorDto mentorDto);

    public APIResponseDto getMentorByEmpId(Long empId);
    
    public APIResponseDto getMentorById(Long id);

    public List<MentorDto> getAllMentors();

    public MentorDto updateMentor(MentorDto mentorDto);

    public String deleteMentor(Long empId);
    
   
    
//    public String getMentorStatus(MentorDto mentorDto);
}
