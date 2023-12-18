package edu.CourseProject.controller;

import edu.CourseProject.entity.UserLogs;
import edu.CourseProject.service.UserLogsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class LogController {
    private UserLogsService userLogsService;

    @GetMapping("/logs")
    public String getLogs(Model model) {
        List<UserLogs> userLogs = userLogsService.getAll();
        model.addAttribute("logs", userLogs);
        return "logs";
    }
}
