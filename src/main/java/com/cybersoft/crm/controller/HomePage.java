package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.StatusService;
import com.cybersoft.crm.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/home")
@WebServlet(name = "homePage", urlPatterns = {"/home"})
public class HomePage extends HttpServlet {
    private StatusService statusService = new StatusService();
    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");

        req.setAttribute("statuses", statusService);
        req.setAttribute("tasks", taskService);
        req.setAttribute("userName", name);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
