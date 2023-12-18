package edu.CourseProject.service;

import edu.CourseProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    public void updateRoles(String username, List<String> roles) {
    }
}
