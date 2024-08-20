package pro.sky.collectionStart.service;

import org.springframework.stereotype.Service;
import pro.sky.collectionStart.model.Employee;
import pro.sky.collectionStart.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectionStart.exceptions.EmployeeNotFoundExceptions;
import pro.sky.collectionStart.exceptions.EmployeesStorageFullException;

import java.util.*;

@Service
public class EmployeeService {
    private static final int MAX_NUM_EMPLOYEES = 10;

    private final Map<String, Employee> employees = new HashMap<>();

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() == MAX_NUM_EMPLOYEES) {
            throw new EmployeesStorageFullException("Больше нельзя добавлять сотрудников.");
        } else if (employees.containsKey(firstName + lastName)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует.");
        } else {
            Employee employee = new Employee(firstName, lastName);
            employees.put(employee.getFullName(), employee);
            return employee;
        }
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if(employees.containsKey(employee.getFullName())){
            return employees.remove(employee.getFullName());
        } else {
            throw new EmployeeNotFoundExceptions("Такого сотрудника не существует.");
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
     Employee employee = new Employee(firstName, lastName);
        if(employees.containsKey(employee.getFullName())){
            return employees.get(employee.getFullName());
        } else {
            throw new EmployeeNotFoundExceptions("Такого сотрудника не существует.");
        }
    }

    public Collection<Employee> printAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

}