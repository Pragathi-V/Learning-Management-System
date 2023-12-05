package com.capg.batchservice.controller;


import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.Test;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capg.batchservice.controller.BatchController;
import com.capg.batchservice.dto.BatchDto;
import com.capg.batchservice.service.BatchService;
 
public class BatchControllerTest {
 
    private BatchController batchController;
    private BatchService batchService;
 
    @BeforeEach
    public void setUp() {
        batchService = mock(BatchService.class);
        batchController = new BatchController(batchService);
    }
 
    //used Mockito to mock the BatchService and set it up in the setUp method.
    //The test cases then use this mocked service to simulate interactions with the actual service.
    @Test
    public void testCreateBatch() {
        BatchDto batchDto = new BatchDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",5);
        when(batchService.createBatch(batchDto)).thenReturn(batchDto);
 
        ResponseEntity<BatchDto> responseEntity = batchController.createBatch(batchDto);
 
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(batchDto, responseEntity.getBody());
        verify(batchService, times(1)).createBatch(batchDto);
    }
 
    @Test
    public void testGetBatchByBatchCode() {
        String batchCode = "C101";
        BatchDto batchDto = new BatchDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",5);
        when(batchService.getBatchByCode(batchCode)).thenReturn(batchDto);
 
        ResponseEntity<BatchDto> responseEntity = batchController.getBatchByBatchCode(batchCode);
 
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(batchDto, responseEntity.getBody());
        verify(batchService, times(1)).getBatchByCode(batchCode);
    }
    
    @Test
    public void testGetBatchById() {
        // Mock data
        Long batchId = 1L;
        BatchDto mockBatchDto = new BatchDto(); 

        // Mock the behavior of your service method
        when(batchService.getBatchById(batchId)).thenReturn(mockBatchDto);

        // Perform the test
        ResponseEntity<BatchDto> responseEntity = batchController.getBatchById(batchId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockBatchDto, responseEntity.getBody());

        // Verify that the service method was called with the correct parameter
        verify(batchService, times(1)).getBatchById(batchId);
    }

 
    @Test
    public void testGetAllBatch() {
        List<BatchDto> batchList = Collections.singletonList(new BatchDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",5));
        when(batchService.getAllBatch()).thenReturn(batchList);
 
        ResponseEntity<List<BatchDto>> responseEntity = batchController.getAllBatch();
 
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(batchList, responseEntity.getBody());
        verify(batchService, times(1)).getAllBatch();
    }
 
    @Test
    public void testUpdateBatch() {
        Long batchId = 1L;
        BatchDto batchDto = new BatchDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",5);
        when(batchService.updateBatch(batchDto)).thenReturn(batchDto);
 
        ResponseEntity<BatchDto> responseEntity = batchController.updateBatch(batchId, batchDto);
 
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(batchDto, responseEntity.getBody());
        verify(batchService, times(1)).updateBatch(batchDto);
    }
    
    @Test
    public void testUpdateBatch1() {
    	String batchCode = "AWS01";
        BatchDto batchDto = new BatchDto(1L,"AWS",LocalDate.of(2023, 11, 11),LocalDate.of(2023, 12, 12),"AWS01",5);
        when(batchService.updateBatch(batchDto)).thenReturn(batchDto);
 
        ResponseEntity<BatchDto> responseEntity = batchController.updateBatch1(batchCode, batchDto);
 
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(batchDto, responseEntity.getBody());
        verify(batchService, times(1)).updateBatch(batchDto);
    }
 
    @Test
    public void testDeleteBatch() {
        Long batchId = 1L;
 
        ResponseEntity<String> responseEntity = batchController.deleteBatch(batchId);
 
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Batch successfully deleted!!", responseEntity.getBody());
        verify(batchService, times(1)).deleteBatch(batchId);
    }
    
    @Test
    public void testDeleteBatch1() {
        String batchCode = "AWS01";
 
        ResponseEntity<String> responseEntity = batchController.deleteBatch1(batchCode);
 
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Batch successfully deleted!!", responseEntity.getBody());
        verify(batchService, times(1)).deleteBatch1(batchCode);
    }
}
 
