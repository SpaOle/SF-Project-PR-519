package ee.mainor.studytimetable.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class BookDto {

    private Integer id;
    private String author;
    private String title;
    private Integer year;
    private String genre;
    private Integer ownerid;
    private Instant deadline;

}

