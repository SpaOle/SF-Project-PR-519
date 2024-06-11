package ee.mainor.studytimetable.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import ee.mainor.studytimetable.dto.CreateTimetableRequest;
import ee.mainor.studytimetable.dto.TimetableDiffDto;
import ee.mainor.studytimetable.dto.TimetableDto;
import ee.mainor.studytimetable.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("timetable")
public class TimetableController {

    private final TimetableService timetableService;

    @PostMapping("{userid}")
    public ResponseEntity create(@PathVariable Integer userid,
                                 @RequestBody CreateTimetableRequest period) throws JsonProcessingException {
        return timetableService.create(userid, period);
    }

    @GetMapping("{userid}")
    public TimetableDto findById(@PathVariable Integer userid) {
        return timetableService.findById(userid);
    }

    @GetMapping("{userid}/diff")
    public List<TimetableDiffDto> findDiffById(@PathVariable Integer userid) {
        return timetableService.findDiffById(userid);
    }

}
