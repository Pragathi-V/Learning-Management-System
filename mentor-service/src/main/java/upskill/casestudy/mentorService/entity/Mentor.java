package upskill.casestudy.mentorService.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import upskill.casestudy.mentorService.dto.BatchDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mentor")
public class Mentor {


   	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long empId;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

//	private int workingHours;
    private String grade;
    
    private String designation;
    
    private String stream;
    
    @Lob
    private String skills;
    
	private String status;
	
//	 @Column(nullable = true)
//    private String courseCode;
	
	private int numOfBatches;
	
	private String batchCode;
	
//	@Lob
//	private List<String> batchCode;
	
	
//	 public String[] getSkills() {
//			return skills;
//		}
//
//		public void setSkills(String[] skills) {
//			this.skills = skills;
//		}
//
//		public List<String> getBatchCode() {
//			return batchCode;
//		}
//
//		public void setBatchCode(List<String> batchCode) {
//			this.batchCode = batchCode;
//		}
    
}