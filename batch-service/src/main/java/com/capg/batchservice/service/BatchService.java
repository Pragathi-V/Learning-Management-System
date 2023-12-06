package com.capg.batchservice.service;

import java.util.List;

import com.capg.batchservice.dto.APIResponseDto;
import com.capg.batchservice.dto.BatchDto;
 
public interface BatchService {
 
	public BatchDto createBatch(BatchDto batchDto);
//	public BatchDto getBatchByCode(String batchCode);
	public APIResponseDto getBatchByCode(String batchCode);
	public BatchDto getBatchById(Long batchId);
	public List<BatchDto> getAllBatch();
	public BatchDto updateBatch(BatchDto batchDto);
	public BatchDto updateBatch1(BatchDto batchDto);
	public String deleteBatch1(String batchCode);
	public String deleteBatch(Long batchID);
}