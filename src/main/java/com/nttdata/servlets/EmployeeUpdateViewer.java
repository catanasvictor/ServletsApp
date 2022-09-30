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
 * WebServlet to update an employee.
 *
 * @author Catanas Kaj
 */
@WebServlet(name = "updateEmployeeView", urlPatterns = "/showUpdateView")
public class EmployeeUpdateViewer extends HttpServlet {

    private EmployeeService employeeService; // service for employees

    /**
     * Initialize service for employee.
     */
    @PostConstruct
    public void init() {
        this.employeeService = new EmployeeService();
    }

    /**
     * Show updated employee.
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
            Employee employee = this.employeeService.getById(Integer.parseInt(id));

            out.println("<h1>Update Employee:</h1>");

            out.print("<form action='update' method='post'>");
            out.print("<table>");
            out.print("<tr><td></td><td><input type='hidden' name='id' value='" + employee.getId() + "'/></td></tr>");
            out.print("<tr><td>Name:</td><td><input type='text' name='name' value='" + employee.getName() + "'/></td></tr>");
            out.print("<tr><td>Age:</td><td><input type='text' name='age' value='" + employee.getAge() + "'/> </td></tr>");

            out.print("<tr><td colspan='2'><input type='submit' value='Update'/></td></tr>");
            out.print("</table>");
            out.print("</form>");

            out.println("<a href='all'>View all persons</a>");
        } catch (NotFoundException | IllegalArgumentException e) {
            out.println("<p style='color:red'>" + e.getMessage() + "</p>");
        }
        out.close();
    }
}