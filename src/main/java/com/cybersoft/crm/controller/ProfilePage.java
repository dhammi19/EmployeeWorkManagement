package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.UserService;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "profilePage",urlPatterns = {"/profile"})
public class ProfilePage extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int id = (int) session.getAttribute("id");

        req.setAttribute("showEmailAndFullName", userService.getEmailAndFullNameOfUserService(id));
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }
}
