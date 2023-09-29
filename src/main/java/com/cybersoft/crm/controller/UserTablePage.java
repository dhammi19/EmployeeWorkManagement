package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userTablePage", urlPatterns = {"/user-table"})
public class UserTablePage extends HttpServlet {
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService);
        req.setAttribute("role", roleService);
        req.getRequestDispatcher("/user-table.jsp").forward(req, resp);
    }
}
