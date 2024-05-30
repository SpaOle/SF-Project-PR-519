package ee.mainor.studytimetable.mapper;

import ee.mainor.studytimetable.dto.CreateBookRequest;
import ee.mainor.studytimetable.dto.TimetableDto;
import ee.mainor.studytimetable.model.Timetable;

public class TimetableMapper {


    public static TimetableDto toDto(Timetable request) {
        TimetableDto timetableDto = new TimetableDto();
        timetableDto.setId(request.getId());
        timetableDto.setUserid(request.getUserid());
        timetableDto.setTimetable(request.getTimetable());
        timetableDto.setTime(request.getTime());
        return timetableDto;
    }

}
