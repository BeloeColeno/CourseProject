package edu.CourseProject.repository;

import edu.CourseProject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByUsername(String username);
}
