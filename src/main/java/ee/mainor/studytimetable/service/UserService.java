package ee.mainor.studytimetable.service;

import ee.mainor.studytimetable.dto.UserDto;
import ee.mainor.studytimetable.dto.CreateUserRequest;
import ee.mainor.studytimetable.mapper.UserMapper;
import ee.mainor.studytimetable.model.User;
import ee.mainor.studytimetable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserDto create(CreateUserRequest createUserRequest) {
        User User = UserMapper.toEntity(createUserRequest);
        return UserMapper.toDto(userRepository.save(User));
    }

    public UserDto update(Integer id, UserDto UserDto) {
        User User = UserMapper.updateEntity(UserDto, requireUser(id));

        return UserMapper.toDto(userRepository.save(User));
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

    public UserDto findById(Integer id) {
        User User = requireUser(id);
        return UserMapper.toDto(User);
    }

    private User requireUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void deleteById(Integer id) {
        User user = requireUser(id);
        userRepository.deleteById(id);
    }
}
