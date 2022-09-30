package com.nttdata.repository;

import com.nttdata.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repository for Employee.
 *
 * @author Catanas Kaj
 */
public class EmployeeRepository {

    private static final Map<Integer, Employee> employees; // map of employees with Integer type key and Employee type value

    private static final AtomicInteger atomicInteger; // atomic Integer

    // static block to initialize Employee objects and store them in the map of employees.
    static {
        employees = new HashMap<>(); // employee Hashmap
        atomicInteger = new AtomicInteger(); // atomic integer

        Employee ion = new Employee(atomicInteger.incrementAndGet(), "Ion", 23);
        employees.put(ion.getId(), ion); // store employee ion

        Employee ana = new Employee(atomicInteger.incrementAndGet(), "Ana", 21);
        employees.put(ana.getId(), ana); // store employee ana

        Employee dan = new Employee(atomicInteger.incrementAndGet(), "Dan", 20);
        employees.put(dan.getId(), dan); // store employee dan
    }

    /**
     * Returns a list of all employees.
     *
     * @return list of employees
     */
    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    /**
     * Returns an employee based on their id.
     *
     * @param id employee id
     * @return id of employee
     */
    public Employee getPerson(int id) {
        return employees.get(id);
    }

    /**
     * Store the employee in the database.
     *
     * @param employee given employee
     */
    public void create(Employee employee) {
        employee.setId(atomicInteger.incrementAndGet());
        employees.put(employee.getId(), employee);
    }

    /**
     * Update an employee from the map.
     *
     * @param employee given employee
     */
    public void update(Employee employee) {
        employees.put(employee.getId(), employee);
    }

    /**
     * Delete an employee based on id.
     *
     * @param id employee id
     */
    public void deleteById(int id) {
        employees.remove(id);
    }
}
