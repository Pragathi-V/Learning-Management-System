package com.capg.batchservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.capg.batchservice.dto.BatchDto;
import com.capg.batchservice.entity.Batch;
import com.capg.batchservice.mapper.BatchMapper;

//import static org.junit.Assert.assertEquals;
//import org.junit.Test;

public class BatchMapperTest {

    @Test
    public void testMapToBatchDto() {
        // Create a sample Batch entity
        Batch batch = new Batch(1L, "AWS", LocalDate.of(2023, 11, 11), LocalDate.of(2023, 11, 11),"AWS01",5);

        // Map the Batch entity to BatchDto
        BatchDto batchDto = BatchMapper.mapToBatchDto(batch);

        // Assert that the mapping is correct
        assertEquals(batch.getBatchId(), batchDto.getBatchId());
        assertEquals(batch.getBatchName(), batchDto.getBatchName());
        assertEquals(batch.getStartDate(), batchDto.getStartDate());
        assertEquals(batch.getEndDate(), batchDto.getEndDate());
        assertEquals(batch.getBatchCode(), batchDto.getBatchCode());
        assertEquals(batch.getBatchHrs(), batchDto.getBatchHrs());
    }

    @Test
    public void testMapToBatch() {
        // Create a sample BatchDto
        BatchDto batchDto = new BatchDto(1L, "AWS", LocalDate.of(2023, 11, 11), LocalDate.of(2023, 11, 11),"AWS01",5);

        // Map the BatchDto to Batch entity
        Batch batch = BatchMapper.mapToBatch(batchDto);

        // Assert that the mapping is correct
        assertEquals(batchDto.getBatchId(), batch.getBatchId());
        assertEquals(batchDto.getBatchName(), batch.getBatchName());
        assertEquals(batchDto.getStartDate(), batch.getStartDate());
        assertEquals(batchDto.getEndDate(), batch.getEndDate());
        assertEquals(batchDto.getBatchCode(), batch.getBatchCode());
        assertEquals(batchDto.getBatchHrs(), batch.getBatchHrs());
    }
}

