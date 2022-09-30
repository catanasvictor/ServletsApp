package com.nttdata.servlets;

import com.nttdata.model.Employee;
import com.nttdata.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * WebServlet to save an employee.
 *
 * @author Catanas Kaj
 */
@WebServlet(name = "saveEmployee", urlPatterns = "/save")
public class EmployeeStore extends HttpServlet {

    private EmployeeService employeeService; // service for employees

    /**
     * Initialize service for employee.
     */
    @PostConstruct
    public void init() {
        this.employeeService = new EmployeeService();
    }

    /**
     * Store an employee.
     *
     * @param request  http servlet request
     * @param response http servlet response
     * @throws IOException I/O exception
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        try {
            Employee employee = new Employee(name, Integer.parseInt(age));
            this.employeeService.create(employee);
        } catch (IllegalArgumentException e) {
            PrintWriter out = response.getWriter();
            out.println("<p style='color:red'>" + e.getMessage() + "</p>");
            out.close();
        }
        response.sendRedirect("all");
    }
}
