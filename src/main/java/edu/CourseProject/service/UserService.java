package edu.CourseProject.service;

import edu.CourseProject.dto.UserDto;
import edu.CourseProject.entity.User;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    @Secured("ROLE_ADMIN")
    List<UserDto> findAllUsers();

    User getCurrentUser();
}
