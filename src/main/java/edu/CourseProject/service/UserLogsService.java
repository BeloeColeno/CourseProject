package edu.CourseProject.service;

import edu.CourseProject.entity.Employee;
import edu.CourseProject.entity.UserLogs;
import edu.CourseProject.repository.UserLogsRepository;
import lombok.AllArgsConstructor;
import edu.CourseProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserLogsService {
    private UserLogsRepository userLogsRepository;

    @Autowired
    private UserService userService;


    private enum ActionType {
        ADD,
        REMOVE,
        EDIT
    }

    public List<UserLogs> getAll() {
        return userLogsRepository.findAll();
    }

    public void addAction(Employee employee, User user) {
        saveAction(ActionType.ADD.name(), employee, user);
    }

    public void deleteAction(Employee employee, User user) {
        saveAction(ActionType.REMOVE.name(), employee, user);
    }

    public void editAction(Employee employee, User user) {
        saveAction(ActionType.EDIT.name(), employee, user);
    }

    private void saveAction(String action, Employee employee, User user) {
        UserLogs userLogs = new UserLogs();
        userLogs.setDescription(action + " " + employee.toString());
//        userAction.setUserId(user);
        userLogs.setUserId(userService.getCurrentUser());
        userLogs.setDateActions(new Date(System.currentTimeMillis()));
        userLogsRepository.save(userLogs);
    }
}
