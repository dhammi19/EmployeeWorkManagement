package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.StatusService;
import com.cybersoft.crm.service.TaskService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "userDetailPage", urlPatterns = {"/user-detail"})
public class UserDetailPage extends HttpServlet {
    private UserService userService = new UserService();
    private StatusService statusService = new StatusService();
    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");
        int userId = Integer.parseInt(req.getParameter("userId"));

        req.setAttribute("statuses", statusService);
        req.setAttribute("userName", name);
        req.setAttribute("user", userService.getUserByUserId(userId));
        req.setAttribute("users", userService);
        req.setAttribute("tasks", taskService);
        req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
    }
}
