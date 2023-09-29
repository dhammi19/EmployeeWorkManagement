package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "editUserPage", urlPatterns = {"/edit-user"})
public class EditUserPage extends HttpServlet {
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");

        req.setAttribute("user",  userService.getUserByUserId(id));
        req.setAttribute("roles", roleService);
        req.setAttribute("userName", name);
        req.getRequestDispatcher("/edit-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String fullName = req.getParameter("userName");
        String password = req.getParameter("password");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        String email = req.getParameter("example-email");

        boolean checkUpdated = userService.checkUpdated(id, fullName, password, roleId);

        if (checkUpdated) {
            System.out.println("Cập nhật thành công");

            int userId = userService.getIdByUserEmail(email);

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userName", userService.getUserNameByUserId(id));

            resp.sendRedirect(req.getContextPath()+"/user-table");
        } else {
            System.out.println("Cập nhật thất bại");
        }
    }
}
