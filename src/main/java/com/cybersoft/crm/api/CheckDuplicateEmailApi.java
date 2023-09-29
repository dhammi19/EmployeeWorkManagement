package com.cybersoft.crm.api;

import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "checkDuplicateEmail", urlPatterns = {"/api/check-duplicate-email"})
public class CheckDuplicateEmailApi extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");

        if (userService.isEmailExists(email)) {
            // Email đã tồn tại
            ResponseData responseData = new ResponseData();
            responseData.setStatus(400); // Mã lỗi tùy chọn
            responseData.setSuccess(false);
            responseData.setDescription("Email đã tồn tại trong hệ thống.");
            Gson gson = new Gson();
            String json = gson.toJson(responseData);
            resp.getWriter().write(json);
        } else {
            // Email không tồn tại
            ResponseData responseData = new ResponseData();
            responseData.setStatus(200); // Mã thành công tùy chọn
            responseData.setSuccess(true);
            responseData.setDescription("Email có thể sử dụng.");
            Gson gson = new Gson();
            String json = gson.toJson(responseData);
            resp.getWriter().write(json);
        }
    }
}
