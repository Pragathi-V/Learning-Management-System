package upskill.casestudy.studentservice.dto;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class BatchDtoTest {

    @Test
    public void testCreateBatchDto() {
        BatchDto batchDto = new BatchDto();
        assertNotNull(batchDto);
    }

    @Test
    public void testBatchDtoGettersAndSetters() {
        BatchDto batchDto = new BatchDto();
        batchDto.setBatchId(1L);
        batchDto.setBatchName("Java Programming");
        batchDto.setStartDate(LocalDate.of(2023, 1, 1));
        batchDto.setEndDate(LocalDate.of(2023, 5, 31));
        batchDto.setBatchCode("JP101");

        assertEquals(1L, batchDto.getBatchId());
        assertEquals("Java Programming", batchDto.getBatchName());
        assertEquals(LocalDate.of(2023, 1, 1), batchDto.getStartDate());
        assertEquals(LocalDate.of(2023, 5, 31), batchDto.getEndDate());
        assertEquals("JP101", batchDto.getBatchCode());
    }
}