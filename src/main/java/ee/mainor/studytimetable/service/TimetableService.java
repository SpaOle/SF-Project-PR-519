package ee.mainor.studytimetable.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ee.mainor.studytimetable.dto.CreateTimetableRequest;
import ee.mainor.studytimetable.dto.TimetableDiffDto;
import ee.mainor.studytimetable.dto.TimetableDto;
import ee.mainor.studytimetable.mapper.TimetableDiffMapper;
import ee.mainor.studytimetable.mapper.TimetableMapper;
import ee.mainor.studytimetable.model.Lecture;
import ee.mainor.studytimetable.model.Timetable;
import ee.mainor.studytimetable.model.TimetableDiff;
import ee.mainor.studytimetable.model.User;
import ee.mainor.studytimetable.repository.TimetableDiffRepository;
import ee.mainor.studytimetable.repository.TimetableRepository;
import ee.mainor.studytimetable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final TimetableDiffRepository timetableDiffRepository;
    private final UserRepository userRepository;

    public ResponseEntity create(Integer userid, CreateTimetableRequest period) throws JsonProcessingException {
        Timetable timetable_old = timetableRepository.findByUserid(userid);
        Timetable timetable_new = GetTimetableForUser(userid, period);
        timetableRepository.save(timetable_new);

        if (timetable_old != null)
        {
            if (timetable_old.getTimetable().compareTo(timetable_new.getTimetable()) != 0)
            {
                ObjectMapper objectMapper = new ObjectMapper();
                List<Lecture> listLecture_old = objectMapper.readValue(timetable_old.getTimetable(), new TypeReference<>(){});
                List<Lecture> listLecture_new = objectMapper.readValue(timetable_new.getTimetable(), new TypeReference<>(){});

                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String diffDescription = "";
                for (Lecture lnew : listLecture_new) {
                    Lecture l1 = listLecture_old.stream().filter(l -> l.id == lnew.id).findFirst().orElse(null);
                    if (l1 == null) {
                        diffDescription += "New lecture found: " + ow.writeValueAsString(lnew) + "\n";
                    } else if (!l1.equals(lnew)){
                        diffDescription += "Lecture changed: " + ow.writeValueAsString(lnew) + "\n";
                    }
                }
                for (Lecture lold : listLecture_old) {
                    Lecture l1 = listLecture_new.stream().filter(l -> l.id == lold.id).findFirst().orElse(null);
                    if (l1 == null) {
                        diffDescription += "Lecture removed: " + ow.writeValueAsString(lold) + "\n";
                    }
                }


                TimetableDiff diff = new TimetableDiff();
                diff.setUserid(userid);
                diff.setTime(LocalDateTime.now(Clock.systemUTC()));
                diff.setTimetable_diff(diffDescription);
                timetableDiffRepository.save(diff);

                return new ResponseEntity(HttpStatus.OK);
            }
        }

        return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
    }

    private Timetable GetTimetableForUser(Integer userid, CreateTimetableRequest period) throws JsonProcessingException {
        var t = timetableRepository.findByUserid(userid);

        if (t == null) {
            t = new Timetable();
            t.setUserid(userid);
        }

        RestClient defaultClient = RestClient.create();
        String s = defaultClient.get()
                .uri("https://ois.eek.ee/student/syllabus/syllabus-json?" +
                        "token=426kbh2f3iqfag9ovuf7btqv9l&" +
                        "student_id=1049277&" +
                        "start=" + period.getStartdate() + "&" +
                        "end=" + period.getEnddate() + "&" +
                        "type=all")
                .retrieve()
                .body(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Lecture> listLecture = objectMapper.readValue(s, new TypeReference<>(){});

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        t.setTimetable(ow.writeValueAsString(listLecture));
        t.setTime(LocalDateTime.now(Clock.systemUTC()));
        return t;
    }

    public TimetableDto findById(Integer id) {
        Timetable timetable = requireTimetable(id);
        return TimetableMapper.toDto(timetable);
    }

    private Timetable requireTimetable(Integer id) {
        return timetableRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Timetable not found"));
    }

    private List<TimetableDiff> requireTimetableDiff(Integer id) {
        var diff = timetableDiffRepository.findByUserid(id);
        if(diff == null || diff.size() == 0)
            throw new IllegalArgumentException("Timetable diff not found");
        return diff;
    }

    private User requireUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public List<TimetableDiffDto> findDiffById(Integer userid) {
        List<TimetableDiff> diff = requireTimetableDiff(userid);
        return TimetableDiffMapper.toDto(diff);
    }
}
