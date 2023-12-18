package edu.CourseProject.service;

import edu.CourseProject.entity.Employee;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface EmployeeService {
    @Secured("ROLE_USER")
    List<Employee> getEmployees();

    @Secured("ROLE_USER")
    void addEmployee(Employee employee);
}
