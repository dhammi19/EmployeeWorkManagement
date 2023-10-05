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

@WebServlet(name = "taskUpdate2Page", urlPatterns = {"/task-update-2"})
public class TaskUpdate2Page extends HttpServlet {

    private TaskService taskService = new TaskService();
    private StatusService statusService = new StatusService();
    private JobService jobService = new JobService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");
        int taskId = Integer.parseInt(req.getParameter("taskId"));

        req.setAttribute("task", taskService.getTaskByItsId(taskId));
        req.setAttribute("statuses", statusService);
        req.setAttribute("job", jobService);
        req.setAttribute("user", userService);
        req.setAttribute("userName", name);
        req.getRequestDispatcher("/task-update-2.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("taskName");
        int jobId = Integer.parseInt(req.getParameter("jobId"));
        int userId= Integer.parseInt(req.getParameter("userId"));
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        int statusId = Integer.parseInt(req.getParameter("statusId"));
        int id = Integer.parseInt(req.getParameter("id"));

        boolean isTaskUpdated = taskService.isTaskUpdated(name, jobId, userId, startDate, endDate, statusId, id);

        if (isTaskUpdated) {
            //System.out.println("Cập nhật thành công");
            resp.sendRedirect(req.getContextPath()+"/task");
        }else {
            //System.out.println("Cập nhật thất bại");
            resp.sendRedirect(req.getContextPath()+"/task");
        }
    }
}
