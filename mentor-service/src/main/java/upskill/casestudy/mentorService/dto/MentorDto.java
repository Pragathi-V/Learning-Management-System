package upskill.casestudy.mentorService.dto;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "MentorDto model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorDto {

    private int id;

    private Long empId; 
    @Schema(
            description = "Name"
    )
    @NotEmpty(message = "Mentor Name should not be null or empty ")
    private String name;

    @Schema(
            description = "Email"
    )
    @NotEmpty(message = "Mentor email should not be null or empty ")
    @Email(message = "Email address should be valid")
    private String email;

	

//	@Schema(
//			description = "workingHours"
//			)
//	private int workingHours;
    
 private String grade;
    
    private String designation;
    
    private String stream;
    
    private String[] skills;
    

	@Schema(
			description = "status"
			)
	private String status;
	
//    @Schema(
//            description = "course code"
//    )
//    private String courseCode;
	
	private int numOfBatches;
	
	private String batchCode;
//	private List<String> batchCode;
    
  

}
