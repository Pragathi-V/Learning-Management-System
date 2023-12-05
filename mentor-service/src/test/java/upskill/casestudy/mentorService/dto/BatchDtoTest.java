package upskill.casestudy.mentorService.dto;

import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.Test;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
 
public class BatchDtoTest {
 
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
 
    @Test
    public void testValidBatchDto() {
        BatchDto batchDto = new BatchDto();
               
 
        assertTrue(validator.validate(batchDto).isEmpty());
    }
 
    @Test
    public void testInvalidBatchDto() {
        BatchDto batchDto = new BatchDto(
                null, // Invalid: batchId is null
                "",   // Invalid: batchName is empty
                null, // Invalid: startDate is null
                null, // Invalid: endDate is null
                ""  , //Invalid: batchCode is empty
                0  // Invalid: batchHrs is empty
        );
 
      
 
    }
 
    @Test
    public void testNullStartDate() {
        BatchDto batchDto = new BatchDto(
                1L,
                "AWS",
                null, // Invalid: startDate is null
                LocalDate.of(2023, 12, 31),
                "AWS101",
                5
        );
 
        assertEquals(1, validator.validate(batchDto).size());
    }
 
    @Test
    public void testNullEndDate() {
        BatchDto batchDto = new BatchDto(
                1L,
                "AWS",
                LocalDate.of(2023, 1, 1),
                null, // Invalid: endDate is null
                "AWS101",
                5
        );
 
        assertEquals(1, validator.validate(batchDto).size());
    }
 
    @Test
    public void testNullBatchCode() {
        BatchDto batchDto = new BatchDto(
                1L,
                "AWS",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                null ,
                5// Invalid: batchCode is null
        );
 
        assertEquals(1, validator.validate(batchDto).size());
    }
 
    
}
 