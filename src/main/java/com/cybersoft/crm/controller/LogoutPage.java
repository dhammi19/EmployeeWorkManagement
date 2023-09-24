package com.cybersoft.crm.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logoutPace", urlPatterns = {"/logout"})
public class LogoutPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy đối tượng HttpSession từ yêu cầu
        HttpSession session = req.getSession(false); // Sử dụng "false" để không tạo session mới nếu nó chưa tồn tại

        if (session != null) {
            // Hủy bỏ session
            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath()+"/login");
    }
}
