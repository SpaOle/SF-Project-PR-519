package ee.mainor.studytimetable.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureContent that = (LectureContent) o;
        return oppejoud.equals(that.oppejoud) && kellaaeg.equals(that.kellaaeg) && maht.equals(that.maht) && ruum.equals(that.ruum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oppejoud, kellaaeg, maht, ruum);
    }
}
