package upskill.casestudy.mentorService.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import upskill.casestudy.mentorService.dto.APIResponseDto;
import upskill.casestudy.mentorService.dto.APIResponseDtoMentor;
import upskill.casestudy.mentorService.dto.BatchDto;
import upskill.casestudy.mentorService.dto.MentorDto;
import upskill.casestudy.mentorService.dto.StudentDto;
import upskill.casestudy.mentorService.entity.Mentor;
import upskill.casestudy.mentorService.exception.EmailAlreadyExistException;
import upskill.casestudy.mentorService.exception.ResourceNotFoundException;
import upskill.casestudy.mentorService.mapper.MentorMapper;
import upskill.casestudy.mentorService.repository.MentorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MentorServiceImpl implements MentorService{

    private static final Logger LOGGER = LoggerFactory.getLogger(MentorServiceImpl.class);

    private MentorRepository mentorRepository;

    private WebClient webClient;

    @Override
    public MentorDto createMentor(MentorDto mentorDto) {
        Optional<Mentor> optionalMentor = mentorRepository.findByEmail(mentorDto.getEmail());
        if(optionalMentor.isPresent()) {
            throw new EmailAlreadyExistException("Email Already Exists for Mentor");
        }
        
//        if(!mentorDto.getBatchCode().isEmpty()) {
//        	mentorDto.setNumOfBatches(mentorDto.getBatchCode().size());
//        }else {
//        	mentorDto.setNumOfBatches(0);
//        }
//        
//        if(mentorDto.getNumOfBatches()>0) {
//        	mentorDto.setStatus("Occupied");
//        }else {
//        	mentorDto.setStatus("Free");
//        }
        if(mentorDto.getNumOfBatches()==0 && mentorDto.getBatchCode()== "") {
        	mentorDto.setStatus("Free");
        	mentorDto.setNumOfBatches(0);
        	mentorDto.setBatchCode("Not Assigned");
        }else
        {mentorDto.setStatus("Occupied");}
        
        Mentor mentor = MentorMapper.mapToMentor(mentorDto);
        Mentor savedMentor = mentorRepository.save(mentor);
        MentorDto savedMentorDto = MentorMapper.mapToMentorDto(savedMentor);
        
        return savedMentorDto;
    }
    

    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDafaultBatch")
    public APIResponseDtoMentor getMentorByEmpId(Long empId) {
        Mentor mentor = mentorRepository.findByEmpId(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor", "empId", empId));
//        BatchDto batchDto = webClient.get()
//                .uri("http://localhost:8081/api/batch/code/" +mentor.getBatchCode())
//                .retrieve()
//                .bodyToMono(BatchDto.class)
//                .block();
        APIResponseDto apiResponseDto = webClient.get()
                .uri("http://localhost:8081/api/batch/code/" +mentor.getBatchCode())
                .retrieve()
                .bodyToMono(APIResponseDto.class)
                .block();

        MentorDto mentorDto = MentorMapper.mapToMentorDto(mentor);
        APIResponseDtoMentor apiResponseDtoMentor = new APIResponseDtoMentor();
        apiResponseDtoMentor.setApiResponseDto(apiResponseDto);       
        apiResponseDtoMentor.setMentor(mentorDto);
//        apiResponseDto.setStudents(studentDtoList);
        return apiResponseDtoMentor;
    }

    @Override
    public List<MentorDto> getAllMentors() {
        List<Mentor> mentor = mentorRepository.findAll();
        return mentor.stream().map(MentorMapper::mapToMentorDto).collect(Collectors.toList());
    }

//    @Override
//    public MentorDto updateMentor(MentorDto mentorDto) {
//
//        Mentor mentor = mentorRepository.findByEmpId(mentorDto.getEmpId())
//                .orElseThrow(() -> new ResourceNotFoundException("Mentor", "EmpId", mentorDto.getEmpId()));
//        
//        mentor.setName(mentorDto.getName());
//        mentor.setEmail(mentorDto.getEmail());
//        mentor.setDesignation(mentorDto.getDesignation());
//        mentor.setGrade(mentorDto.getGrade());
//        mentor.setSkills(mentorDto.getSkills());
//        mentor.setStream(mentorDto.getStream());
//        mentor.setNumOfBatches(mentorDto.getNumOfBatches());
//        mentor.setBatchCode(mentorDto.getBatchCode());
//        
//        if(mentorDto.getNumOfBatches()>0 && mentorDto.getBatchCode()== null) {
//        	mentor.setStatus("Free");
//        	mentor.setNumOfBatches(0);
//        	mentor.setBatchCode("Not Assigned");
//        }else
//        {mentor.setStatus("Occupied");}
//        Mentor updatedMentor = mentorRepository.save(mentor);
//       
//        return MentorMapper.mapToMentorDto(updatedMentor);
//    }
    @Override
    public MentorDto updateMentor(MentorDto mentorDto) {

        Mentor mentor = mentorRepository.findById(mentorDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Mentor", "Id", mentorDto.getId()));
        mentor.setEmpId(mentorDto.getEmpId());
        mentor.setName(mentorDto.getName());
        mentor.setEmail(mentorDto.getEmail());
        mentor.setDesignation(mentorDto.getDesignation());
        mentor.setGrade(mentorDto.getGrade());
        mentor.setSkills(mentorDto.getSkills());
        mentor.setStream(mentorDto.getStream());
        mentor.setNumOfBatches(mentorDto.getNumOfBatches());
        mentor.setBatchCode(mentorDto.getBatchCode());
        
        if(mentorDto.getNumOfBatches()>0 && mentorDto.getBatchCode()== null) {
        	mentor.setStatus("Free");
        	mentor.setNumOfBatches(0);
        	mentor.setBatchCode("Not Assigned");
        }else
        {mentor.setStatus("Occupied");}
        Mentor updatedMentor = mentorRepository.save(mentor);
       
        return MentorMapper.mapToMentorDto(updatedMentor);
    }
    
    

    @Override
    public String deleteMentor(Long empId)  {
        Mentor mentor = mentorRepository.findByEmpId(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor", "empId", empId));
        mentorRepository.deleteByEmpId(empId);
        return "Deleted Successfully!!";

    }

    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDafaultBatchByMentorId")
    public APIResponseDtoMentor getMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor", "id", id));
//        BatchDto batchDto = webClient.get()
//                .uri("http://localhost:8081/api/batch/code/" +mentor.getBatchCode())
//                .retrieve()
//                .bodyToMono(BatchDto.class)
//                .block();
        LOGGER.info(mentor.getBatchCode());
        APIResponseDto apiResponseDto = webClient.get()
                .uri("http://localhost:8081/api/batch/code/" +mentor.getBatchCode())
                .retrieve()
                .bodyToMono(APIResponseDto.class)
                .block();
        MentorDto mentorDto = MentorMapper.mapToMentorDto(mentor);
        APIResponseDtoMentor apiResponseDtoMentor = new APIResponseDtoMentor();
        apiResponseDtoMentor.setApiResponseDto(apiResponseDto);       
        apiResponseDtoMentor.setMentor(mentorDto);
        return apiResponseDtoMentor;
    }
    


	
    //fallback method
    public APIResponseDtoMentor getDafaultBatch(Long empId , Exception exception) {
        LOGGER.info("inside getDafaultBatch() method");
        Mentor mentor= mentorRepository.findByEmpId(empId).get();

//        BatchDto batchDto= new BatchDto();
//        batchDto.setBatchName("Cloud");
//        batchDto.setStartDate(LocalDate.of(2024, 01, 03));
//        batchDto.setEndDate(LocalDate.of(2024, 05, 23));
//        batchDto.setBatchCode("CL01");
//        batchDto.setBatchHrs(1);
        APIResponseDto apiResponseDto = new APIResponseDto(null,null);

        MentorDto	mentorDto = MentorMapper.mapToMentorDto(mentor);

        APIResponseDtoMentor apiResponseDtoMentor= new APIResponseDtoMentor();
        apiResponseDtoMentor.setApiResponseDto(apiResponseDto);
        apiResponseDtoMentor.setMentor(mentorDto);

        return apiResponseDtoMentor;
    }
  //fallback method
    public APIResponseDtoMentor getDafaultBatchByMentorId(Long id , Exception exception) {
        LOGGER.info("inside getDafaultBatch() method");
        Mentor mentor= mentorRepository.findById(id).get();

//        BatchDto batchDto= new BatchDto();
//        batchDto.setBatchName("Cloud");
//        batchDto.setStartDate(LocalDate.of(2024, 01, 03));
//        batchDto.setEndDate(LocalDate.of(2024, 05, 23));
//        batchDto.setBatchCode("CL01");
//        batchDto.setBatchHrs(1);
        APIResponseDto apiResponseDto = new APIResponseDto(null,null);

        MentorDto	mentorDto = MentorMapper.mapToMentorDto(mentor);

        APIResponseDtoMentor apiResponseDtoMentor= new APIResponseDtoMentor();
        apiResponseDtoMentor.setApiResponseDto(apiResponseDto);
        apiResponseDtoMentor.setMentor(mentorDto);

        return apiResponseDtoMentor;
    }


	
		

	}

