package com.capg.batchservice.entity;

import static org.junit.jupiter.api.Assertions.*;
 
 
import java.time.LocalDate;
 
import org.junit.jupiter.api.Test;

import com.capg.batchservice.entity.Batch;
public class BatchTest {
 
	@Test
    public void testBatchGetterAndSetter() {
        Batch batch = new Batch();
        batch.setBatchId(1L);
        batch.setBatchName("AWS");
        batch.setStartDate(LocalDate.of(2023, 1, 1));
        batch.setEndDate(LocalDate.of(2023, 12, 31));
        batch.setBatchCode("AWS01");
        batch.setBatchHrs(5);
 
        assertEquals(1L, batch.getBatchId());
        assertEquals("AWS", batch.getBatchName());
        assertEquals(LocalDate.of(2023, 1, 1), batch.getStartDate());
        assertEquals(LocalDate.of(2023, 12, 31), batch.getEndDate());
        assertEquals("AWS01", batch.getBatchCode());
        assertEquals(5, batch.getBatchHrs());
    }
	 @Test
	    public void testDefaultConstructor() {
	        Batch batch = new Batch();
 
	        assertNull(batch.getBatchId());
	        assertNull(batch.getBatchName());
	        assertNull(batch.getStartDate());
	        assertNull(batch.getEndDate());
	        assertNull(batch.getBatchCode());
//	        assertNull(batch.getBatchHrs());
	        
	    }
 
	    @Test
	    public void testParameterizedConstructor() {
	        Long batchId = 1L;
	        String batchName = "AWS";
	        LocalDate startDate = LocalDate.of(2023, 1, 1);
	        LocalDate endDate = LocalDate.of(2023, 12, 31);
	        String batchCode = "AWS01";
	        int batchHrs = 5;
 
	        Batch batch = new Batch(batchId, batchName, startDate, endDate, batchCode, batchHrs);
 
	        assertEquals(batchId, batch.getBatchId());
	        assertEquals(batchName, batch.getBatchName());
	        assertEquals(startDate, batch.getStartDate());
	        assertEquals(endDate, batch.getEndDate());
	        assertEquals(batchCode, batch.getBatchCode());
	        assertEquals(batchHrs, batch.getBatchHrs());
	    }
}
 
