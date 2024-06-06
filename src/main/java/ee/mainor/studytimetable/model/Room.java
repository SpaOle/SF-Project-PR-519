package ee.mainor.studytimetable.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {
    @JsonProperty
    private Integer ID;
    @JsonProperty
    private String number_eng;
    @JsonProperty
    private Integer mahutavus;
    @JsonProperty
    private String vahendid_eng;

}
