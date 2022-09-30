package com.nttdata.model;

/**
 * Employee model class with fields: id, name and age.
 *
 * @author Catanas Kaj
 */
public class Employee {

    private Integer id; // employee id
    private String name; // employee name
    private final int age; // employee age

    /**
     * Constructor for Employee with name and age fields.
     *
     * @param name name of Employee
     * @param age  age of Employee
     */
    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Constructor for employee with id, name and age fields.
     *
     * @param id   id of employee
     * @param name name of employee
     * @param age  age of employee
     */
    public Employee(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the id of a specified employee.
     *
     * @return employee id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id for an employee.
     *
     * @param id employee id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the name of a specified employee.
     *
     * @return name of employee
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for an employee.
     *
     * @param name employee name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the age of a specified employee.
     *
     * @return age of employee
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the properties of an employee as a string.
     *
     * @return employee properties as string
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
