package edu.CourseProject.controller;

import edu.CourseProject.entity.Role;
import edu.CourseProject.entity.User;
import edu.CourseProject.entity.UserRole;
import edu.CourseProject.repository.RoleRepository;
import edu.CourseProject.repository.UserRepository;
import edu.CourseProject.service.SecureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecureService securityService;

    @PersistenceContext
    private EntityManager entityManager;


    @GetMapping("/users/list")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @PostMapping("/users")
    public String addRoles(@RequestParam("userId") Long userId, @RequestParam("role") String role, Model model) {


        // Получаем текущего пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Проверяем, что текущий пользователь имеет роль ADMIN
        boolean isAdmin = securityService.isUserInRole("ROLE_ADMIN");

        if (!isAdmin) {
            throw new AccessDeniedException("У вас недостаточно прав для выполнения этого действия.");
        }


        // Проверяем, что роль существует
        Role roleToAdd = roleRepository.findByName(String.valueOf(role));
        // Выводим роль

        System.out.println(role);
        System.out.println(userId);
        if (roleToAdd == null) {
            model.addAttribute("error", "Роль не существует.");
            return "users"; // Вернуться к списку пользователей с ошибкой
        }


        // Получаем пользователя по его идентификатору
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Пользователь не найден."));
        // Добавляем роль пользователю
        System.out.println(user);
        Set<UserRole> userRoles = user.getUserRoles();
        userRoles.add(new UserRole(user, roleToAdd));

        // Сохраняем изменения в базе данных
        entityManager.flush();

        return "redirect:/users"; // Перенаправляем на список пользователей
    }
}
