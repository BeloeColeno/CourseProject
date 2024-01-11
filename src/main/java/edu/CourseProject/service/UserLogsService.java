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


    private enum LogType {
        ADD,
        REMOVE,
        EDIT
    }

    public List<UserLogs> getAll() {
        return userLogsRepository.findAll();
    }

    public void addLog(Employee employee, User user) {
        saveLog(LogType.ADD.name(), employee, user);
    }

    public void deleteLog(Employee employee, User user) {
        saveLog(LogType.REMOVE.name(), employee, user);
    }

    public void editLog(Employee employee, User user) {
        saveLog(LogType.EDIT.name(), employee, user);
    }

    private void saveLog(String log, Employee employee, User user) {
        UserLogs userLogs = new UserLogs();
        userLogs.setDescription(log + " " + employee.toString());
//        userAction.setUserId(user);
        userLogs.setUserId(userService.getCurrentUser());
        userLogs.setDateLogs(new Date(System.currentTimeMillis()));
        userLogsRepository.save(userLogs);
    }
}
