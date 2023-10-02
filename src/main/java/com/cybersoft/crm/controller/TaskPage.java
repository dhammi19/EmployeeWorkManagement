package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;
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

@WebServlet(name = "taskPage", urlPatterns = {"/task"})
public class TaskPage extends HttpServlet {
    private TaskService taskService = new TaskService();
    private JobService jobService = new JobService();
    private UserService userService = new UserService();
    private StatusService statusService = new StatusService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");

        req.setAttribute("tasks", taskService);
        req.setAttribute("userName", name);
        req.setAttribute("job", jobService);
        req.setAttribute("user", userService);
        req.setAttribute("status", statusService);
        req.getRequestDispatcher("/task.jsp").forward(req, resp);
    }
}
