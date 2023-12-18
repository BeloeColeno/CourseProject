package edu.CourseProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.CourseProject.entity.UserLogs;

public interface UserLogsRepository extends JpaRepository<UserLogs, Long> {

}
