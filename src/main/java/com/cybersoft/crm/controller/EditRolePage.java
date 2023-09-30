package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "editRolePage", urlPatterns = {"/edit-role"})
public class EditRolePage extends HttpServlet {
    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int id = Integer.parseInt(req.getParameter("id"));
        String name = (String) session.getAttribute("userName");

        req.setAttribute("roleId", id);
        req.setAttribute("userName", name);
        req.setAttribute("role", roleService.getRoleById(id));
        req.getRequestDispatcher("edit-role.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
       String description = req.getParameter("description");
       int roleId = Integer.parseInt(req.getParameter("roleId"));

       boolean isUpdated = roleService.editRole(name, description, roleId);

       if (isUpdated) {
           resp.sendRedirect(req.getContextPath()+"/role");
       } else {
           resp.sendRedirect(req.getContextPath()+"/role");
       }
    }
}
