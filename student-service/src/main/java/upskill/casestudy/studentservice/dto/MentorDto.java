package upskill.casestudy.studentservice.dto;
import io.swagger.v3.oas.annotations.media.Schema;
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

    private Long id;

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
//			description = "course"
//			)
//	@NotEmpty(message = "Mentor course should not be null or empty ")
//	private String course;

    @Schema(
            description = "course code"
    )
    @NotEmpty(message = "course code should not be null or empty ")
    private String courseCode;
}
