package ee.mainor.studytimetable.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "userdb")
public class User {

    @Id
    private Integer id;
    private String login;

}
