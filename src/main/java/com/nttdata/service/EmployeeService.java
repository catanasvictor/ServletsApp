package com.nttdata.service;

import com.nttdata.exception.NotFoundException;
import com.nttdata.model.Employee;
import com.nttdata.repository.EmployeeRepository;

import java.util.List;

/**
 * Service for Employee.
 *
 * @author Catanas Kaj
 */
public class EmployeeService {

    private final EmployeeRepository employeeRepository; // repository for employee

    /**
     * Constructor for EmployeeService.
     */
    public EmployeeService() {
        this.employeeRepository = new EmployeeRepository();
    } // service for employee

    /**
     * Returns a list of all employees.
     *
     * @return list of employees
     */
    public List<Employee> getAll() {
        List<Employee> employees = this.employeeRepository.getAll();
        if (employees == null || employees.isEmpty()) {
            throw new NotFoundException("Could not find any employee!");
        }
        return employees;
    }

    /**
     * Returns an employee based on id.
     *
     * @param id employee id
     * @return employee with specified id
     */
    public Employee getById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("The id must be provided!");
        }
        Employee employee = this.employeeRepository.getPerson(id);
        if (employee == null) {
            throw new NotFoundException("Could not find any employee with id: " + id + "!");
        }
        return employee;
    }

    /**
     * Verify employee id and store the employee.
     *
     * @param employee given employee
     */
    public void create(Employee employee) {
        if (employee.getId() != null) {
            throw new IllegalArgumentException("The id must not be provided when create a new employee!");
        }
        this.employeeRepository.create(employee);
    }

    /**
     * Verify employee id and update employee.
     *
     * @param employee given employee
     */
    public void update(Employee employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("The id must be provided when update an employee!");
        }
        this.getById(employee.getId()); // validates if person exists
        this.employeeRepository.update(employee);
    }

    /**
     * Delete an employee based on id.
     *
     * @param id employee id
     */
    public void deleteById(int id) {
        Employee employeeToDelete = this.getById(id);
        this.employeeRepository.deleteById(employeeToDelete.getId());
    }
}
