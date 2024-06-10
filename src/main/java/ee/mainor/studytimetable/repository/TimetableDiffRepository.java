package ee.mainor.studytimetable.repository;

import ee.mainor.studytimetable.model.Timetable;
import ee.mainor.studytimetable.model.TimetableDiff;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimetableDiffRepository extends CrudRepository<TimetableDiff, Integer> {


    @Query("select * from timetable_diff t where t.userid = :userid")
    List<TimetableDiff> findByUserid(@Param("userid") Integer userid);

    @Query("DELETE FROM timetable_diff t WHERE t.time < (NOW() - interval '1 hour') and t.userid = :userid")
    List<TimetableDiff> cleanupByUserid(@Param("userid") Integer userid);

}
