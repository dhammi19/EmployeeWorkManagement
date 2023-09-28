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
import java.io.PrintWriter;

@WebServlet(name = "addUserApi", urlPatterns = {"/api/add-user"})
public class AddUserApi extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        int role_id = Integer.parseInt(req.getParameter("roleId"));
        boolean checkAddUser = userService.checkAddUser(email, password, fullName, role_id);

        ResponseData responseData = new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(checkAddUser);
        responseData.setDescription(checkAddUser ? "Xóa thành công" : "Xóa thất bại");

        Gson gson = new Gson();
        String json = gson.toJson(responseData); // toJson biến đối tượng thành 1 chuổi json

        out.print(json);
        out.flush();

        System.out.println(json);
    }
}
