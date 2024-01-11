package edu.CourseProject.controller;

import edu.CourseProject.entity.Employee;
import edu.CourseProject.entity.User;
import edu.CourseProject.repository.EmployeeRepository;
import edu.CourseProject.service.EmployeeService;
import edu.CourseProject.service.UserLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserLogsService userLogsService;

    @GetMapping("/list")
    public ModelAndView getAllEmployees(){
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("List-employees");
        mav.addObject("employees",employeeService.getEmployees());
        return mav;
    }

    @GetMapping("/list1")
    public String aboutPage() {
        return "list1";
    }

    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee employee = new Employee();
        mav.addObject("employee", employee);
        return mav;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee, @AuthenticationPrincipal User user){
        employeeService.addEmployee(employee);
        userLogsService.addLog(employee, user);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId, @AuthenticationPrincipal User user){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = new Employee();
        if (optionalEmployee.isPresent()){
            employee = optionalEmployee.get();
        }
        mav.addObject("employee", employee);
        Employee savedEmployee = employeeRepository.save(employee);
        userLogsService.editLog(savedEmployee, user);
        return mav;
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId,
                             @AuthenticationPrincipal User user,
                             @ModelAttribute Employee employee) {
        Employee employeeToDelete = employeeRepository.findById(employeeId).orElse(null);
        userLogsService.deleteLog(employeeToDelete, user);
        employeeRepository.deleteById(employeeId);
        return "redirect:/list";
    }
}
