package ee.mainor.studytimetable.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Table(name = "timetable")
public class Timetable {

    @Id
    private Integer id;
    private Integer userid;
    private String timetable;
    private LocalDateTime time;

}
