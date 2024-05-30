package ee.mainor.studytimetable.web;

import ee.mainor.studytimetable.dto.TimetableDto;
import ee.mainor.studytimetable.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("timetable")
public class TimetableController {

    private final TimetableService timetableService;

    @PostMapping("{id}")
    public ResponseEntity create(@PathVariable Integer id) {
        return timetableService.create(id);
    }


    @GetMapping("{id}")
    public TimetableDto findById(@PathVariable Integer id) {
        return timetableService.findById(id);
    }

}
