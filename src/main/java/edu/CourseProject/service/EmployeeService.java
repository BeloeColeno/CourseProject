package edu.CourseProject.service;

import edu.CourseProject.entity.Employee;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

   // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    void addEmployee(Employee employee);
}
