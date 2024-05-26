package ee.mainor.studytimetable.dto;

import lombok.Data;

@Data
public class CreateBookRequest {

    private String author;
    private String title;
    private Integer year;
    private String genre;

}
