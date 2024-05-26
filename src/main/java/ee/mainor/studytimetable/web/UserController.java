package ee.mainor.studytimetable.web;

import ee.mainor.studytimetable.dto.UserDto;
import ee.mainor.studytimetable.dto.CreateUserRequest;
import ee.mainor.studytimetable.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @GetMapping("{id}")
    public UserDto findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
    }

}
