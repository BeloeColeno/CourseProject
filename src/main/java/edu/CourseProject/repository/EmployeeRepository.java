package edu.CourseProject.repository;

import edu.CourseProject.entity.Employee;
import edu.CourseProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByUser(User user);
}
