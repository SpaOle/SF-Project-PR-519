package ee.mainor.studytimetable.web;

import ee.mainor.studytimetable.dto.CreateBookRequest;
import ee.mainor.studytimetable.dto.BookDto;
import ee.mainor.studytimetable.dto.GiveUserRequest;
import ee.mainor.studytimetable.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookDto create(@RequestBody CreateBookRequest request) {
        return bookService.create(request);
    }

    @PutMapping("{id}")
    public BookDto update(@PathVariable Integer id, @RequestBody BookDto request) {
        return bookService.update(id ,request);
    }

    @GetMapping("{id}")
    public BookDto findById(@PathVariable Integer id) {
        return bookService.findById(id);
    }

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        bookService.deleteById(id);
    }

    @PostMapping("{id}/give")
    public BookDto giveToUser(@PathVariable Integer id, @RequestBody GiveUserRequest request) {
        return bookService.giveToUser(id ,request);
    }

    @PostMapping("{id}/return")
    public BookDto returnToLibrary(@PathVariable Integer id) {
        return bookService.returnToLibrary(id);
    }

}
