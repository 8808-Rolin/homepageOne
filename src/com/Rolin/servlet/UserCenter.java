package com.Rolin.servlet;


import com.Rolin.type.UserInfo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/uCenter")
public class UserCenter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String id;
        User user;
        id = request.getParameter("id");
        user= (User)request.getSession().getAttribute("user");
        if (id.equals(String.valueOf(user.id))){
            request.getRequestDispatcher("/usercenter.jsp").forward(request,response);
        }else {
            response.getWriter().print("请勿看别人的信息");
        }
    }
}
