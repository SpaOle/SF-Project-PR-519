package ee.mainor.studytimetable.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ee.mainor.studytimetable.dto.TimetableDto;
import ee.mainor.studytimetable.mapper.TimetableMapper;
import ee.mainor.studytimetable.model.Lecture;
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
import java.util.List;


@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final UserRepository userRepository;

    public ResponseEntity create(Integer userid) throws JsonProcessingException {
        Timetable timetable_old = timetableRepository.findByUserid(userid);
        Timetable timetable_new = GetTimetableForUser(userid);
        timetableRepository.save(timetable_new);

        if (timetable_old != null)
        {
            if (timetable_old.getTimetable().compareTo(timetable_new.getTimetable()) != 0)
            {
                ObjectMapper objectMapper = new ObjectMapper();
                List<Lecture> listLecture_old = objectMapper.readValue(timetable_old.getTimetable(), new TypeReference<>(){});
                List<Lecture> listLecture_new = objectMapper.readValue(timetable_new.getTimetable(), new TypeReference<>(){});



                return new ResponseEntity(HttpStatus.OK);
            }
        }

        return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
    }

    private Timetable GetTimetableForUser(Integer userid) throws JsonProcessingException {
        var t = timetableRepository.findByUserid(userid);

        if (t == null) {
            t = new Timetable();
            t.setUserid(userid);
        }

        RestClient defaultClient = RestClient.create();
        String s = defaultClient.get()
                .uri("https://ois.eek.ee/student/syllabus/syllabus-json?start=2024-06-01T00%3A00%3A00%2B03%3A00&end=2024-06-30T00%3A00%3A00%2B03%3A00")
                .header("Cookie", "PHPSESSID=s13hunn7i79sfcku3uvskb2sjn; Path=/; Secure; HttpOnly;")
                .retrieve()
                .body(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Lecture> listLecture = objectMapper.readValue(s, new TypeReference<>(){});

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        t.setTimetable(ow.writeValueAsString(listLecture));
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
