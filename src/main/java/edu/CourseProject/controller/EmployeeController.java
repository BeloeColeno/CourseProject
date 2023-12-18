package edu.CourseProject.controller;

import edu.CourseProject.entity.Employee;
import edu.CourseProject.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/list")
    public ModelAndView getAllEmployees(){
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("List-employees");
        mav.addObject("employees",employeeRepository.findAll());
        return mav;
    }

    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee employee = new Employee();
        mav.addObject("employee", employee);
        return mav;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = new Employee();
        if (optionalEmployee.isPresent()){
            employee = optionalEmployee.get();
        }
        mav.addObject("employee", employee);
        return mav;
    }

    @GetMapping("/deleteEmployee")
    public String deleteStudent(@RequestParam Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return "redirect:/list";
    }
}
