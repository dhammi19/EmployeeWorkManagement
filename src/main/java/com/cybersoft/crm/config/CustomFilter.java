package com.cybersoft.crm.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}) // Filter sẽ hoạt động với url chỉ định đó là tất cả các url vì /*
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // System.out.println("Kiểm tra filter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();


        // Đây là code mẫu về cách sử dụng filter
        if (session.getAttribute("isLogin")!= null && !session.getAttribute("isLogin").equals("")) {
            boolean isLogin = (boolean) session.getAttribute("isLogin");

            if (isLogin) {
                // chuyển về page chỉ định
                if (request.getServletPath().equals("/login")) {
                    // nếu là trang login
                    response.sendRedirect(request.getContextPath()+"/home");
                } else {
                    filterChain.doFilter(request, response);
                }
            } else {
                // chuyển về page login
                response.sendRedirect(request.getContextPath()+"/login");
            }
        } else {
            // chưa login
            // chuyển về page login
            // System.out.println("Kiếm tra path "+request.getServletPath());
            if (request.getServletPath().equals("/login")) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath()+"/login");
            }

        }

//        filterChain.doFilter(servletRequest, servletResponse); // Cho phép đi vào link mong muốn
    }
}
