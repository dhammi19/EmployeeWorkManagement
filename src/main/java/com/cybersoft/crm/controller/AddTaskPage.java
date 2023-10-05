package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.TaskService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addTaskPage", urlPatterns = {"/add-task"})
public class AddTaskPage extends HttpServlet {
    private JobService jobService = new JobService();
    private UserService userService = new UserService();
    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");

        req.setAttribute("jobs", jobService);
        req.setAttribute("users", userService);
        req.setAttribute("userName", name);
        req.getRequestDispatcher("task-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskName = req.getParameter("taskName");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        int userId = Integer.parseInt(req.getParameter("userId"));
        int jobId = Integer.parseInt(req.getParameter("jobId"));
        int statusId = 1;

        boolean isTaskAdded = taskService.isTaskAdded(taskName, startDate, endDate, userId, jobId, statusId);

        if(isTaskAdded) {
            resp.sendRedirect(req.getContextPath()+"/task");
        } else {
            resp.sendRedirect(req.getContextPath()+"/task");
        }
    }
}
