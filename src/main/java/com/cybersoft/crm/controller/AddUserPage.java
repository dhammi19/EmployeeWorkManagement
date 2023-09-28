package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.service.StatusService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addUserPage", urlPatterns = {"/add-user"})
public class AddUserPage extends HttpServlet {
    private RoleService roleService = new RoleService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService);
        req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("example-email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("userName");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        boolean checkAddUser = userService.checkAddUser(email, password, fullName, roleId);

        if (checkAddUser == true) {
            resp.sendRedirect("/EmployeeWorkManagement/user-table");
        } else {
            req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
        }


    }
}
