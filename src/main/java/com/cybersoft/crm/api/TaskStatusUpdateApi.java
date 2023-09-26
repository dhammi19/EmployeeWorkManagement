package com.cybersoft.crm.api;

import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.service.TaskService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "taskStatusUpdateApi", urlPatterns = {"/api/task-status-update"})
public class TaskStatusUpdateApi extends HttpServlet {
    private TaskService taskService = new TaskService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int statusId = Integer.parseInt(req.getParameter("statusId"));
        int taskId = Integer.parseInt(req.getParameter("taskId"));

        boolean isSuccess = taskService.checkTaskStatusUpdate(statusId, taskId);
        ResponseData responseData = new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess ? "Cập nhật thành công" : "Cập nhật thất bại");

        Gson gson = new Gson();
        String json = gson.toJson(responseData); // toJson biến đối tượng thành 1 chuổi json

        out.print(json);
        out.flush();

        System.out.println(json);
        resp.sendRedirect(req.getContextPath()+"/profile");
    }
}
