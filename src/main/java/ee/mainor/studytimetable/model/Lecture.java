package ee.mainor.studytimetable.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lecture {
    @JsonProperty
    public Integer id;
    @JsonProperty
    public String title;
    @JsonProperty
    public String start;
    @JsonProperty
    public String end;
    @JsonProperty
    private LectureContent sisu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return id.equals(lecture.id) && title.equals(lecture.title) && start.equals(lecture.start) && end.equals(lecture.end) && sisu.equals(lecture.sisu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, start, end, sisu);
    }
}
