package com.nttdata.servlets;

import com.nttdata.exception.NotFoundException;
import com.nttdata.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * WebServlet to delete an employee.
 *
 * @author Catanas Kaj
 */
@WebServlet(name = "deleteEmployee", urlPatterns = "/delete")
public class EmployeeDeleter extends HttpServlet {

    private EmployeeService employeeService; // service for employees

    /**
     * Initialize service for employee.
     */
    @PostConstruct
    public void init() {
        this.employeeService = new EmployeeService();
    }

    /**
     * Delete an employee.
     *
     * @param request  http servlet request
     * @param response http servlet response
     * @throws IOException I/O exception
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String id = request.getParameter("id");
        try {
            this.employeeService.deleteById(Integer.parseInt(id));
        } catch (NotFoundException | IllegalArgumentException e) {
            PrintWriter out = response.getWriter();
            out.println("<p style='color:red'>" + e.getMessage() + "</p>");
            out.close();
        }
        response.sendRedirect("all");
    }
}
