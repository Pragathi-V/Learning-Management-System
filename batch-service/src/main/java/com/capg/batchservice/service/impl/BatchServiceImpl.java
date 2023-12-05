package com.capg.batchservice.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.capg.batchservice.dto.BatchDto;
import com.capg.batchservice.entity.Batch;
import com.capg.batchservice.exception.ResourceNotFoundException;
import com.capg.batchservice.mapper.BatchMapper;
import com.capg.batchservice.repository.BatchRepository;
import com.capg.batchservice.service.BatchService;

import lombok.AllArgsConstructor;
 
@Service
@AllArgsConstructor
public class BatchServiceImpl implements BatchService  {
 
	private BatchRepository batchRepository;
	
	@Override
	public BatchDto createBatch(BatchDto batchDto) {
		Batch batch = BatchMapper.mapToBatch(batchDto);
		Batch savedBatch = batchRepository.save(batch);
		BatchDto savedBatchDto = BatchMapper.mapToBatchDto(savedBatch);
		return savedBatchDto;
	}
 
	@Override
	public BatchDto getBatchByCode(String batchCode) {
		Batch batch = batchRepository.findByBatchCode(batchCode);
//				.orElseThrow(() -> new ResourceNotFoundException("Batch", "batchCode", batchCode));
		BatchDto batchDto = BatchMapper.mapToBatchDto(batch);
		return batchDto;
	}
//	@Override
//	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDafaultStudent")
//	public APIResponseDto getBatchByCode(String batchCode) {
//		Batch batch = batchRepository.findByBatchCode(batchCode);
////				.orElseThrow(() -> new ResourceNotFoundException("Batch", "batchCode", batchCode));
//		List<StudentDto> studentDto = webClient.get()
//				.uri("http://localhost:8083/api/student/code/" +batch.getBatchCode())
//				.retrieve()
//				.bodyToMono(StudentDto.class)
//				.block();
//		
//		BatchDto batchDto = BatchMapper.mapToBatchDto(batch);
//		APIResponseDto apiResponseDto = new APIResponseDto();
//	      apiResponseDto.setBatch(batchDto);
//	      apiResponseDto.setStudent(studentDto);
//	      return apiResponseDto;
////		BatchDto batchDto = BatchMapper.mapToBatchDto(batch);
////		return batchDto;
//	}
	
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
 
}
 




