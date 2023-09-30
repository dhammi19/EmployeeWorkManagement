package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "addJobPage", urlPatterns = {"/add-job"})
public class AddJobPage extends HttpServlet {
    private JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");
        req.setAttribute("userName", name);
        req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jobName = req.getParameter("jobName");
        String startDay = req.getParameter("startDate");
        String endDay = req.getParameter("endDate");

        boolean isJobAdded = jobService.isJobAdded(jobName, startDay, endDay);

        if(isJobAdded) {
            //System.out.println("Thêm dự án thành công!");
            resp.sendRedirect(req.getContextPath()+"/job");
        } else {
            System.out.println("Thêm dự án thất bại!");
            //resp.sendRedirect(req.getContextPath()+"/job");
        }
    }
}
