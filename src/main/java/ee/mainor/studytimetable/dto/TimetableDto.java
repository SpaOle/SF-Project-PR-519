package ee.mainor.studytimetable.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TimetableDto {

    private Integer id;
    private Integer userid;
    private String timetable;
    private LocalDateTime time;

}

