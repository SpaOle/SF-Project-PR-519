package ee.mainor.studytimetable.service;

import ee.mainor.studytimetable.dto.TimetableDto;
import ee.mainor.studytimetable.mapper.TimetableMapper;
import ee.mainor.studytimetable.model.Timetable;
import ee.mainor.studytimetable.model.User;
import ee.mainor.studytimetable.repository.TimetableRepository;
import ee.mainor.studytimetable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final UserRepository userRepository;

    public ResponseEntity create(Integer userid) {
        Timetable timetable = GetTimetableForUser(userid);
        timetableRepository.save(timetable);
        return new ResponseEntity(HttpStatus.OK);
    }

    private Timetable GetTimetableForUser(Integer userid) {
        var t = timetableRepository.findByUserid(userid);
        if (t == null) {
            t = new Timetable();
            t.setUserid(userid);
        }

        RestClient defaultClient = RestClient.create();
        String s = defaultClient.get()
                .uri("https://ois.eek.ee/student/syllabus/syllabus-json?start=2024-06-01T00%3A00%3A00%2B03%3A00&end=2024-06-05T00%3A00%3A00%2B03%3A00")
                .header("Cookie", "PHPSESSID=ss788iq7op70jan45fb0u3mfsp; Path=/; Secure; HttpOnly;")
                .retrieve()
                .body(String.class);
        t.setTimetable(s);
        t.setTime(LocalDateTime.now().toString());
        return t;
    }

    public TimetableDto findById(Integer id) {
        Timetable timetable = requireTimetable(id);
        return TimetableMapper.toDto(timetable);
    }

    private Timetable requireTimetable(Integer id) {
        return timetableRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Timetable not found"));
    }

    private User requireUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
