package upskill.casestudy.mentorService.mapper;


import upskill.casestudy.mentorService.dto.MentorDto;
import upskill.casestudy.mentorService.entity.Mentor;

public class MentorMapper {

    //convert JPA Entity to DTO
    public static MentorDto mapToMentorDto(Mentor mentor) {
        MentorDto mentorDto = new MentorDto(
                mentor.getId(),
                mentor.getEmpId(),
                mentor.getName(),
                mentor.getEmail(),
                mentor.getGrade(),
                mentor.getDesignation(),
                mentor.getStream(),
                mentor.getSkills(),
                mentor.getStatus(),
                mentor.getNumOfBatches(),
                mentor.getBatchCode()
//                mentor.getBatches()
                               
        );
        return mentorDto;
    }

    //convert DTO to JPA Entity
    public static Mentor mapToMentor(MentorDto mentorDto) {
        Mentor mentor = new Mentor(
                mentorDto.getId(),
                mentorDto.getEmpId(),
                mentorDto.getName(),
                mentorDto.getEmail(),
//                mentorDto.getWorkingHours(),
                mentorDto.getGrade(),
                mentorDto.getDesignation(),
                mentorDto.getStream(),
                mentorDto.getSkills(),
                mentorDto.getStatus(),
               mentorDto.getNumOfBatches(),
               mentorDto.getBatchCode()
//                mentorDto.getBatches()

        );
        return mentor;
    }
}
