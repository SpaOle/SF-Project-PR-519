package ee.mainor.studytimetable.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return ID.equals(room.ID) && number_eng.equals(room.number_eng) && mahutavus.equals(room.mahutavus) && vahendid_eng.equals(room.vahendid_eng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, number_eng, mahutavus, vahendid_eng);
    }
}
