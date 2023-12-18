package edu.CourseProject.service;

import edu.CourseProject.entity.Employee;
import edu.CourseProject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final UserService userService;

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findEmployeesByUser(userService.getCurrentUser());
    }

    @Override
    public void addEmployee(Employee employee) {
        employee.setUser(userService.getCurrentUser());
        employeeRepository.save(employee);
    }
}
