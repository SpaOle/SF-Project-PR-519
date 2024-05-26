package ee.mainor.studytimetable.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Table(name = "book")
public class Book {

    @Id
    private Integer id;

    private String author;
    private String title;
    private Integer year;
    private String genre;
    private Integer ownerid;
    private Instant deadline;

}
