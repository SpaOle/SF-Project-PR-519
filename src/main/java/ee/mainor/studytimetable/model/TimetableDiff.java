package ee.mainor.studytimetable.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table(name = "timetable_diff")
public class TimetableDiff {

    @Id
    private Integer id;
    private Integer userid;
    private String timetable_diff;
    private LocalDateTime time;

}
