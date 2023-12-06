package com.capg.batchservice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.capg.batchservice.dto.APIResponseDto;
import com.capg.batchservice.dto.BatchDto;
import com.capg.batchservice.dto.StudentDto;
import com.capg.batchservice.entity.Batch;
import com.capg.batchservice.exception.ResourceNotFoundException;
import com.capg.batchservice.mapper.BatchMapper;
import com.capg.batchservice.repository.BatchRepository;
import com.capg.batchservice.service.BatchService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;

 
@Service
@AllArgsConstructor
public class BatchServiceImpl implements BatchService  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchServiceImpl.class);
 
	private BatchRepository batchRepository;
	
	private WebClient webclient;
	
	@Override
	public BatchDto createBatch(BatchDto batchDto) {
		Batch batch = BatchMapper.mapToBatch(batchDto);
		Batch savedBatch = batchRepository.save(batch);
		BatchDto savedBatchDto = BatchMapper.mapToBatchDto(savedBatch);
		return savedBatchDto;
	}
 
//	@Override
//	public BatchDto getBatchByCode(String batchCode) {
//		Batch batch = batchRepository.findByBatchCode(batchCode);
////				.orElseThrow(() -> new ResourceNotFoundException("Batch", "batchCode", batchCode));
//		BatchDto batchDto = BatchMapper.mapToBatchDto(batch);
//		return batchDto;
//	}
	@Override
	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDafaultStudent")
	public APIResponseDto getBatchByCode(String batchCode) {
		Batch batch = batchRepository.findByBatchCode(batchCode);
//				.orElseThrow(() -> new ResourceNotFoundException("Batch", "batchCode", batchCode));
	    ParameterizedTypeReference<List<StudentDto>> responseType = new ParameterizedTypeReference<List<StudentDto>>() {};

//		List<StudentDto> studentDto =  webclient.get()
//				.uri("http://localhost:8083/api/student/code/" +batch.getBatchCode())
//				.retrieve()
//				.bodyToMono(StudentDto.class)
//				.block();
	    List<StudentDto> studentDtoList = webclient.get()
	            .uri("http://localhost:8083/api/student/code/" + batch.getBatchCode())
	            .retrieve()
	            .bodyToMono(responseType)
	            .block();
		
		BatchDto batchDto = BatchMapper.mapToBatchDto(batch);
		APIResponseDto apiResponseDto = new APIResponseDto();
	      apiResponseDto.setBatch(batchDto);
	      apiResponseDto.setStudents(studentDtoList);
	      return apiResponseDto;
//		BatchDto batchDto = BatchMapper.mapToBatchDto(batch);
//		return batchDto;
	}
	
	@Override
	public BatchDto getBatchById(Long batchId) throws ResourceNotFoundException {
		Batch batch = batchRepository.findById(batchId).get();
//				.orElseThrow(() -> new ResourceNotFoundException("Batch", "batchCode", batchCode));
		BatchDto batchDto = BatchMapper.mapToBatchDto(batch);
		return batchDto;
	}

	@Override
	public List<BatchDto> getAllBatch() {
		List<Batch> batch = batchRepository.findAll();
		return batch.stream().map(BatchMapper::mapToBatchDto).collect(Collectors.toList());
	}
 
	@Override
	public BatchDto updateBatch(BatchDto batchDto) throws ResourceNotFoundException {
		Batch batch = batchRepository.findByBatchCode(batchDto.getBatchCode());
//				.orElseThrow(() -> new ResourceNotFoundException("Batch", "batchCode", batchDto.getBatchCode()));
		
		batch.setBatchName(batchDto.getBatchName());
		batch.setStartDate(batchDto.getStartDate());
		batch.setEndDate(batchDto.getEndDate());
		batch.setBatchCode(batchDto.getBatchCode());
		batch.setBatchHrs(batchDto.getBatchHrs());
		Batch updatedBatch = batchRepository.save(batch);
		return BatchMapper.mapToBatchDto(updatedBatch);
	}
	@Override
		public BatchDto updateBatch1(BatchDto batchDto) throws ResourceNotFoundException {
			Optional<Batch> batch = batchRepository.findById(batchDto.getBatchId());
//					.orElseThrow(() -> new ResourceNotFoundException("Batch", "batchCode", batchDto.getBatchCode()));
			Batch batch1 = batch.get();
			batch1.setBatchName(batchDto.getBatchName());
			batch1.setStartDate(batchDto.getStartDate());
			batch1.setEndDate(batchDto.getEndDate());
			batch1.setBatchCode(batchDto.getBatchCode());
			batch1.setBatchHrs(batchDto.getBatchHrs());
			Batch updatedBatch = batchRepository.save(batch1);
			return BatchMapper.mapToBatchDto(updatedBatch);
		}
	
 
	@Override
//	@Transactional
	public String deleteBatch1(String batchCode) {
		Batch batch = batchRepository.findByBatchCode(batchCode);
		batchRepository.deleteByBatchCode(batchCode);
		return "Deleted Successfully!!";
	}
	
	@Override
	public String deleteBatch(Long batchID) throws ResourceNotFoundException {
		Batch batch = batchRepository.findById(batchID).get();
		batchRepository.deleteById(batchID);
		return "Deleted Successfully!!";
	}
//	//fallback method
//		public APIResponseDto getDafaultStudent(String batchCode , Exception exception) {
//			LOGGER.info("inside getDafaultBatch() method");
//			Batch batch= batchRepository.findByBatchCode(batchCode);
//			   
//			 List<StudentDto> studentDtoList= new ArrayList<>();
//			 StudentDto studentDto1 = new StudentDto();
//			 studentDto1.setStudentId(12345L);
//			 studentDto1.setStudentName("Not registered");
//			 studentDto1.setEmail("Not Registered");
//			 studentDto1.setBatchCode("CLS01");
//			 studentDtoList.add(studentDto1);
//			
//			   BatchDto	batchDto = BatchMapper.mapToBatchDto(batch);
//			
//			 APIResponseDto apiResponseDto= new APIResponseDto();
//			 apiResponseDto.setBatch(batchDto);
//			 apiResponseDto.setStudents(studentDtoList);
//			
//			return apiResponseDto;
//		}
// 
}
 




