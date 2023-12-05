package upskill.casestudy.studentservice.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class APIResponseDtoTest {

    @Test
    public void testCreateAPIResponseDto() {
        APIResponseDto apiResponseDto = new APIResponseDto();
        assertNotNull(apiResponseDto);
    }

    @Test
    public void testAPIResponseDtoGettersAndSetters() {
        StudentDto studentDto = new StudentDto(1L, "pragathi", "pragathi@gmail.com", "CS101");
        BatchDto batchDto = new BatchDto(1L, "Computer Science", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 5, 31), "CS101", 2);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setStudent(studentDto);
        apiResponseDto.setBatch(batchDto);

        assertEquals(studentDto, apiResponseDto.getStudent());
        assertEquals(batchDto, apiResponseDto.getBatch());
    }
}