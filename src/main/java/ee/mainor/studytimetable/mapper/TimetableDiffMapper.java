package ee.mainor.studytimetable.mapper;

import ee.mainor.studytimetable.dto.TimetableDiffDto;
import ee.mainor.studytimetable.dto.TimetableDto;
import ee.mainor.studytimetable.model.Timetable;
import ee.mainor.studytimetable.model.TimetableDiff;

import java.util.ArrayList;
import java.util.List;

public class TimetableDiffMapper {


    public static List<TimetableDiffDto> toDto(List<TimetableDiff> request) {
        List<TimetableDiffDto> result = new ArrayList<>();
        for (var d : request) {
            TimetableDiffDto diff = new TimetableDiffDto();
            diff.setId(d.getId());
            diff.setUserid(d.getUserid());
            diff.setTimetable_diff(d.getTimetable_diff());
            diff.setTime(d.getTime());
            result.add(diff);
        }

        return result;
    }

}
