package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "jobDetailPage", urlPatterns = {"/job-detail"})
public class JobDetailPage extends HttpServlet {
    private JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");
        int id = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("userName", name);
        req.setAttribute("job", jobService.getJobById(id));
        req.getRequestDispatcher("/groupwork-details.jsp").forward(req, resp);
    }
}
