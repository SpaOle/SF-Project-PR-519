package ee.mainor.studytimetable.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimetableDiffDto {

    private Integer id;
    private Integer userid;
    private String timetable_diff;
    private LocalDateTime time;

}

