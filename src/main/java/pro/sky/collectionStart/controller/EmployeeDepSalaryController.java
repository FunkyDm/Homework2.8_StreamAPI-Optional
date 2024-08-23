package pro.sky.collectionStart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collectionStart.model.Employee;
import pro.sky.collectionStart.service.EmployeeDepSalaryService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/departments/")
public class EmployeeDepSalaryController {
    private final EmployeeDepSalaryService employeeDepSalaryService;

    public EmployeeDepSalaryController(EmployeeDepSalaryService employeeDepSalaryService) {
        this.employeeDepSalaryService = employeeDepSalaryService;
    }

    @GetMapping("all")
    public Map<Integer,List<Employee>> getEmployees() {
        return employeeDepSalaryService.getEmployees();
    }

    @GetMapping("all-by-dep")
    public List<Employee> getEmployeesByDep(@RequestParam(value = "departmentId") int departmentId) {
        return employeeDepSalaryService.getEmployeesByDep(departmentId);
    }

    @GetMapping("max-salary")
    public Employee getEmployeeDepMaxSalary(@RequestParam(value = "departmentId") int departmentId) {
        return employeeDepSalaryService.getEmployeeDepMaxSalary(departmentId);
    }

    @GetMapping("min-salary")
    public Employee getEmployeeMinSalary(@RequestParam(value = "departmentId") int departmentId) {
        return employeeDepSalaryService.getEmployeeDepMinSalary(departmentId);
    }

}
