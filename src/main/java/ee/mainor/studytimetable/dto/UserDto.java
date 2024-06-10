package ee.mainor.studytimetable.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserDto {

    private Integer id;
    private String login;

}
