package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "editJobPage", urlPatterns = {"/edit-job"})
public class EditJobPage extends HttpServlet {
    private JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");
        int id = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("job", jobService.getJobById(id));
        req.setAttribute("userName", name);
        req.getRequestDispatcher("/edit-job.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jobName = req.getParameter("jobName");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        int jobId = Integer.parseInt(req.getParameter("jobId"));
        boolean isJobUpdated = jobService.editJob(jobName, startDate, endDate, jobId);

        if (isJobUpdated) {
            //System.out.println("Thêm dự án thành công!");
            resp.sendRedirect(req.getContextPath()+"/job");
        } else {
            System.out.println("Thêm dự án thất bại!");
            //resp.sendRedirect(req.getContextPath()+"/job");
        }
    }
}
