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

/**
 * WebServlet to view a specific employee.
 *
 * @author Catanas Kaj
 */
@WebServlet(name = "viewEmployee", urlPatterns = "/view")
public class EmployeeViewer extends HttpServlet {

    private EmployeeService employeeService; // service for employees

    /**
     * Initialize service for employee.
     */
    @PostConstruct
    public void init() {
        this.employeeService = new EmployeeService();
    }

    /**
     * Show specified employee.
     *
     * @param request  http servlet request
     * @param response http servlet response
     * @throws IOException I/O exception
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String id = request.getParameter("id");

        PrintWriter out = response.getWriter();
        try {
            out.println("<h1>View Employee:</h1>");

            Employee employee = this.employeeService.getById(Integer.parseInt(id));

            out.println("<span> Name: " + employee.getName() + "</span>");
            out.println("</br>");
            out.println("<span> Age: " + employee.getAge() + "</span>");
            out.println("</br>");
            out.println("</br>");

            out.println("<a href='all'>View all employees</a>");
        } catch (NotFoundException | IllegalArgumentException e) {
            out.println("<p style='color:red'>" + e.getMessage() + "</p>");
        }
        out.close();
    }
}