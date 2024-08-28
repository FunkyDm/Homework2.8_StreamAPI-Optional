package pro.sky.collectionStart.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionStart.exceptions.*;
import pro.sky.collectionStart.model.Employee;

import java.util.*;

@Service
public class EmployeeService {
    private static final int MAX_NUM_EMPLOYEES = 10;

    private final Map<String, Employee> employees = new HashMap<>();

    public Employee addEmployee(String firstName, String lastName, double salary, int department) {
        if (employees.size() == MAX_NUM_EMPLOYEES) {
            throw new EmployeesStorageFullException("Больше нельзя добавлять сотрудников.");
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует.");
        } else if (employee.getSalary() <= 0){
            throw new EmployeeWrongSalaryException("Установлено неправильное значение зарплаты.");
        } else if (0 < employee.getDepartment() && employee.getDepartment() > 5){
            throw new EmployeeWrongDepartmentNumberException("Уставлен неправильный номер отдела.");
        } else{
            employees.put(employee.getFullName(), employee);
            return employee;
        }
    }

    public Employee removeEmployee(String firstName, String lastName, double salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        } else {
            throw new EmployeeNotFoundExceptions("Такого сотрудника не существует.");
        }
    }

    public Employee findEmployee(String firstName, String lastName, double salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        } else {
            throw new EmployeeNotFoundExceptions("Такого сотрудника не существует.");
        }
    }

    public Collection<Employee> printAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

    public Map<String, Employee> getMap() {
        return employees;
    }

}