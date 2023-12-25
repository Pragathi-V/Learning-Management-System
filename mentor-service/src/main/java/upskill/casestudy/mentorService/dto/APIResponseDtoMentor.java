package upskill.casestudy.mentorService.dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDtoMentor {

    private MentorDto mentor;
    private APIResponseDto apiResponseDto;
//    private BatchDto batch;
//    private List<StudentDto> students;
}