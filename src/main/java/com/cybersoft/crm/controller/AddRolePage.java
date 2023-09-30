package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addRolePage", urlPatterns = {"/add-role"})
public class AddRolePage extends HttpServlet {
    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");

        req.setAttribute("userName", name);
        req.getRequestDispatcher("role-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        boolean isUpdated = roleService.addRole(name, description);

        if (isUpdated) {
            resp.sendRedirect(req.getContextPath()+"/role");
        } else {
            req.getRequestDispatcher("/add-role").forward(req, resp);
        }
    }
}
