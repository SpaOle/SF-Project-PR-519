package ee.mainor.studytimetable.repository;

import ee.mainor.studytimetable.model.Timetable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimetableRepository extends CrudRepository<Timetable, Integer> {

    List<Timetable> findAll();

    @Query("select * from timetable t where t.userid = :userid")
    Timetable findByUserid(@Param("userid") Integer userid);
}
