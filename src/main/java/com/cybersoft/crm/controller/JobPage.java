package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "jobPage", urlPatterns = {"/job"})
public class JobPage extends HttpServlet {
    private JobService jobService = new JobService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");
        req.setAttribute("userName", name);
        req.setAttribute("jobs", jobService);
        req.getRequestDispatcher("/groupwork.jsp").forward(req, resp);
    }
}
