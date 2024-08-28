package pro.sky.collectionStart.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionStart.exceptions.EmployeeWrongDepartmentNumberException;
import pro.sky.collectionStart.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeDepSalaryService {
    private final EmployeeService employeeService;

    public EmployeeDepSalaryService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Map<Integer, List<Employee>> getEmployees() {
        return employeeService.getMap().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public List<Employee> getEmployeesByDep(int departmentId) {
        return employeeService.getMap().values().stream()
                .filter(e -> (e.getDepartment() == departmentId))
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }

    public Employee getEmployeeDepMaxSalary(int departmentId) {
        return employeeService.getMap().values().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeWrongDepartmentNumberException("Уставлен неправильный номер отдела."));
    }

    public Employee getEmployeeDepMinSalary(int departmentId) {
        return employeeService.getMap().values().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeWrongDepartmentNumberException("Уставлен неправильный номер отдела."));
    }

}
