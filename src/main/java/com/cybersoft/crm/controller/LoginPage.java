package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginPage", urlPatterns = {"/login"})
public class LoginPage extends HttpServlet {
    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

//        Cookie cookie = new Cookie("email", email); // Khai báo 1 cookie tên là email
//        cookie.setMaxAge(5*60); // Đặt thời gian coolie là 60s
//        resp.addCookie(cookie); // Thông báo cho client biết rắng server muốn tạo ra 1 cooke tên là email
//
//        Cookie cookie1 = new Cookie("password", password);
//        cookie1.setMaxAge(5*60);
//        resp.addCookie(cookie1);
//
//        Cookie[] cookies = req.getCookies();
//        for (Cookie ck : cookies) {
//            System.out.println("Name cookie: "+ck.getName()+" Value cookie: "+ck.getValue());
//        }

        boolean isLogin = loginService.checkLogin(email, password);

        if (isLogin) {
            HttpSession httpSession = req.getSession(); // Yêu cầu sử dụng session
            httpSession.setAttribute("isLogin", true);
            httpSession.setMaxInactiveInterval(8*60);
        }

        System.out.println("Kiếm tra login: "+isLogin);

        req.getRequestDispatcher("/webapp/login.html").forward(req, resp);
    }
}
