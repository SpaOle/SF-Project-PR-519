package ee.mainor.studytimetable.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LectureContent {
    @JsonProperty
    private String oppejoud;
    @JsonProperty
    private String kellaaeg;
    @JsonProperty
    private String maht;
    @JsonProperty
    private Room ruum;
}
