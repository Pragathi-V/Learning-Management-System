package com.capg.batchservice.service.impl;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capg.batchservice.controller.BatchController;
import com.capg.batchservice.dto.BatchDto;
import com.capg.batchservice.entity.Batch;
import com.capg.batchservice.exception.ResourceNotFoundException;
import com.capg.batchservice.mapper.BatchMapper;
import com.capg.batchservice.repository.BatchRepository;
import com.capg.batchservice.service.BatchService;
import com.capg.batchservice.service.impl.BatchServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
public class BatchServiceImplTest {
 
    @InjectMocks
    private BatchServiceImpl batchService;
 
    @Mock
    private BatchRepository batchRepository;
 
   
//	@BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
 
    @BeforeEach
    public void setUp() {
        batchRepository = mock(BatchRepository.class);
        batchService = new BatchServiceImpl(batchRepository);
    }
    @Test
    public void testCreateBatch() {
        BatchDto batchDto = new BatchDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",5);
        Batch batch = BatchMapper.mapToBatch(batchDto);
        when(batchRepository.save(any())).thenReturn(batch);
 
        BatchDto savedBatchDto = batchService.createBatch(batchDto);
 
        assertNotNull(savedBatchDto);
        // Add more assertions as needed
    }

    @Test
    public void testGetBatchByCode() {
        String batchCode = "AWS101";
        Batch batch = new Batch(/* initialize with valid data */);
        when(batchRepository.findByBatchCode(batchCode)).thenReturn(batch);
 
        BatchDto batchDto = batchService.getBatchByCode(batchCode);
 
        assertNotNull(batchDto);
        // Add more assertions as needed
    }
 
    @Test
    public void testGetAllBatch() {
        when(batchRepository.findAll()).thenReturn(Collections.emptyList());
 
        List<BatchDto> batchList = batchService.getAllBatch();
 
        assertNotNull(batchList);
        assertEquals(0, batchList.size());
        // Add more assertions as needed
    }
 
    @Test
    public void testUpdateBatch() {
        BatchDto batchDto = new BatchDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",5);
        Batch existingBatch = new Batch(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",5);
//        when(batchRepository.findById(batchDto.getBatchId()).thenReturn(Optional.of(existingBatch));
//        when(batchRepository.save(any())).thenReturn(existingBatch);
        when(batchRepository.findById(batchDto.getBatchId())).thenReturn(Optional.of(existingBatch));
        when(batchRepository.save(any())).thenReturn(existingBatch);

 
        BatchDto updatedBatchDto = batchService.updateBatch1(batchDto);
 
        assertNotNull(updatedBatchDto);
        // Add more assertions as needed
    }
    
    @Test
    public BatchDto updateBatch(BatchDto batchDto) throws ResourceNotFoundException {
        Optional<Batch> optionalBatch = batchRepository.findById(batchDto.getBatchId());

        if (optionalBatch.isPresent()) {
            Batch existingBatch = optionalBatch.get();

            

            // Update batch details
            existingBatch.setBatchName(batchDto.getBatchName());
            existingBatch.setStartDate(batchDto.getStartDate());
            existingBatch.setEndDate(batchDto.getEndDate());
            existingBatch.setBatchCode(batchDto.getBatchCode());
            existingBatch.setBatchHrs(batchDto.getBatchHrs());

            // Save and return updated batch
            Batch updatedBatch = batchRepository.save(existingBatch);
            return BatchMapper.mapToBatchDto(updatedBatch);
        } else {
            throw new ResourceNotFoundException("Batch", "batchId", batchDto.getBatchId());
        }
    }

 
    @Test
    public void testDeleteBatch() {
        Long batchId = 1L;
        Batch existingBatch = new Batch(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",5);
        when(batchRepository.findById(batchId)).thenReturn(Optional.of(existingBatch));
 
        String result = batchService.deleteBatch(batchId);
 
        assertEquals("Deleted Successfully!!", result);
     
    }
}
