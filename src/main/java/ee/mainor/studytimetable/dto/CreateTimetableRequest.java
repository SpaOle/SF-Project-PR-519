package ee.mainor.studytimetable.dto;

import lombok.Data;

@Data
public class CreateTimetableRequest {

    private String startdate;
    private String enddate;

}
