package ee.mainor.studytimetable.repository;

import ee.mainor.studytimetable.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAll();

}
