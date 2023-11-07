package edu.Sim3LR8.service;

import edu.Sim3LR8.dto.UserDto;
import edu.Sim3LR8.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
