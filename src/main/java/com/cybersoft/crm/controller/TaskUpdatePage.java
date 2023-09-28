package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.StatusService;
import com.cybersoft.crm.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "taskUpdatePage", urlPatterns = {"/task-update"})
public class TaskUpdatePage extends HttpServlet {
    TaskService taskService = new TaskService();

    JobService jobService = new JobService();

    StatusService statusService = new StatusService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("taskId"));

            req.setAttribute("tasksList", taskService.getTaskByItsId(id));
            req.setAttribute("jobName", jobService);
            req.setAttribute("statusName", statusService);
            req.setAttribute("statusList", statusService.getAllStatus());
            req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
        } catch(Exception e) {
            // System.out.println(e);
            resp.sendRedirect(req.getContextPath()+"/profile");
        }
//        int id = Integer.parseInt(req.getParameter("taskId"));
//
//        req.setAttribute("tasksList", taskService.getTaskByItsId(id));
//        req.setAttribute("jobName", jobService);
//        req.setAttribute("statusName", statusService);
//        req.setAttribute("statusList", statusService.getAllStatus());
        // System.out.println(req.getParameter("taskId"));

        // req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
    }
}
