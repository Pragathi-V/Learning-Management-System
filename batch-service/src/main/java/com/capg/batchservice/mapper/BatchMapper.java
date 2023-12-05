package com.capg.batchservice.mapper;

import com.capg.batchservice.dto.BatchDto;
import com.capg.batchservice.entity.Batch;

public class BatchMapper {

	//convert JPA Entity to DTO
	public static BatchDto mapToBatchDto(Batch batch) {
		BatchDto batchDto = new BatchDto(
				batch.getBatchId(),
				batch.getBatchName(),
				batch.getStartDate(),
				batch.getEndDate(),
				batch.getBatchCode(),
				batch.getBatchHrs()
				);
		return batchDto;
	}
	
	public static Batch mapToBatch(BatchDto batchDto) {
		Batch batch = new Batch(
				batchDto.getBatchId(),
				batchDto.getBatchName(),
				batchDto.getStartDate(),
				batchDto.getEndDate(),
				batchDto.getBatchCode(),
				batchDto.getBatchHrs()
				);
		return batch;
	}
}
