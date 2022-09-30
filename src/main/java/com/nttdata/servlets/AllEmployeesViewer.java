package com.nttdata.servlets;

import com.nttdata.exception.NotFoundException;
import com.nttdata.model.Employee;
import com.nttdata.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * WebServlet to view all employees.
 *
 * @author Catanas Kaj
 */
@WebServlet(name = "viewAllEmployees", urlPatterns = "/all")
public class AllEmployeesViewer extends HttpServlet {

    private EmployeeService employeeService; // service for employees

    /**
     * Initialize service for employee.
     */
    @PostConstruct
    public void init() {
        this.employeeService = new EmployeeService();
    }

    /**
     * Show table with all employees.
     *
     * @param request  http servlet request
     * @param response http servlet response
     * @throws IOException I/O exception
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setHeader("design", "NTTData");

        PrintWriter out = response.getWriter();
        try {
            out.println("<h1>Employee List:</h1>");

            List<Employee> employees = this.employeeService.getAll();

            out.print("<table border='1' width='100%'>");
            out.print("<tr><th>Id</th><th>Name</th><th>Age</th><th>View</th><th>Update</th><th>Delete</th></tr>");
            for (Employee employee : employees) {
                out.print("<tr><td>" + employee.getId() + "</td><td>" + employee.getName() + "</td><td>" + employee.getAge() + "</td>" +
                        "<td><a href='view?id=" + employee.getId() + "'>view</a></td>" +
                        "<td><a href='showUpdateView?id=" + employee.getId() + "'>update</a></td>" +
                        "<td><a href='delete?id=" + employee.getId() + "'>delete</a></td></tr>");
            }
            out.print("</table>");

            out.println("</br>");
            out.println("<a href='index.html'>Add Employee</a>");
        } catch (NotFoundException e) {
            out.println("<p style='color:red'>" + e.getMessage() + "</p>");
        }
        out.close();
    }
}