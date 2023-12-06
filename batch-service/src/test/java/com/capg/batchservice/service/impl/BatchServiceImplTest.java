package com.capg.batchservice.service.impl;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import com.capg.batchservice.controller.BatchController;
import com.capg.batchservice.dto.APIResponseDto;
import com.capg.batchservice.dto.BatchDto;
import com.capg.batchservice.dto.StudentDto;
import com.capg.batchservice.entity.Batch;
import com.capg.batchservice.exception.ResourceNotFoundException;
import com.capg.batchservice.mapper.BatchMapper;
import com.capg.batchservice.repository.BatchRepository;
import com.capg.batchservice.service.BatchService;
import com.capg.batchservice.service.impl.BatchServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
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
    
    @Mock
    private WebClient webClient;
 
   
//	@BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
 
    @BeforeEach
    public void setUp() {
        batchRepository = mock(BatchRepository.class);
        batchService = new BatchServiceImpl(batchRepository, webClient);
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

//    @Test
//    public void testGetBatchByCode() {
//        String batchCode = "AWS101";
//        Batch batch = new Batch(/* initialize with valid data */);
//        when(batchRepository.findByBatchCode(batchCode)).thenReturn(batch);
// 
//        BatchDto batchDto = batchService.getBatchByCode(batchCode);
// 
//        assertNotNull(batchDto);
//        // Add more assertions as needed
//    }
 
    
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
        when(batchRepository.findById(batchDto.getBatchId())).thenReturn(Optional.of(existingBatch));
        when(batchRepository.save(any())).thenReturn(existingBatch);

 
        BatchDto updatedBatchDto = batchService.updateBatch1(batchDto);
 
        assertNotNull(updatedBatchDto);
      
    }
    
    @Test
    public void testUpdateBatch1() throws ResourceNotFoundException {
        // Mock data
        BatchDto batchDto = new BatchDto();
        batchDto.setBatchCode("AWS01");
        batchDto.setBatchName("AWS Batch");
        // Set other properties as needed

        Batch existingBatch = new Batch();
        existingBatch.setBatchCode("AWS01");
        // Set other properties as needed

        // Mock the behavior of the batchRepository.findByBatchCode method
        when(batchRepository.findByBatchCode(batchDto.getBatchCode())).thenReturn(existingBatch);

        // Mock the behavior of the batchRepository.save method
        when(batchRepository.save(existingBatch)).thenReturn(existingBatch);

        // Call the actual method from your service
        BatchDto result = batchService.updateBatch(batchDto);

        // Assertions
        assertEquals(batchDto.getBatchName(), result.getBatchName());
        // Add more specific assertions based on your mapping logic and other properties

        // Verify that batchRepository.findByBatchCode was called
        verify(batchRepository, times(1)).findByBatchCode(batchDto.getBatchCode());

        // Verify that batchRepository.save was called
        verify(batchRepository, times(1)).save(existingBatch);
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
