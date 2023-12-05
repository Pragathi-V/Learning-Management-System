package upskill.casestudy.mentorService.dto;

import java.time.LocalDate;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
		description = "MentorDto model information"
		)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BatchDto {
	private Long batchId;
	
	@Schema(
			description = "Batch Name"
			)
	@NotEmpty(message = "Batch Name should not be null or empty ")
	private String batchName;
	
	@Schema(
			description = "startDate"
			)
//	@NotEmpty(message = "Start Date should not be null or empty ")
	@NotNull(message = "Start Date should not be null or empty ")
	private LocalDate startDate;
	
	@Schema(
			description = "End Date"
			)
	@NotNull(message = "End Date should not be null or empty ")
	private LocalDate endDate;
	
	@Schema(
			description = "Batch Hrs"
			)
	private int batchHrs;
	@Schema(
			description = "Batch Code"
			)
	private String batchCode;
}
