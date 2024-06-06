package ee.mainor.studytimetable.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lecture {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String start;
    @JsonProperty
    private String end;
    @JsonProperty
    private LectureContent sisu;
}
