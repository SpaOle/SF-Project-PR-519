package ee.mainor.studytimetable.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class TimetableDto {

    private Integer id;
    private Integer userid;
    private String timetable;
    private String time;

}

