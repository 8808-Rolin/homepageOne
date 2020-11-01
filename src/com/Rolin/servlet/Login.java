package com.Rolin.servlet;

import javax.naming.Name;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import com.Rolin.type.UserInfo;
import  com.Rolin.utils.Constant.*;

@WebServlet("/logindeal")
public class Login extends HttpServlet {
    Connection conn;
    PreparedStatement stat;

    //数据库语句


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化和获取数据
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String telepohone = request.getParameter("telephone");
        String password = request.getParameter("password");
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        PrintWriter pw = response.getWriter();
        //数据库初始化
        try {
            conn =null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://rolin.org.cn:3300/WebServer?useUnicode=true&characterEncoding=UTF-8","root","120605.rolin");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            stat = conn.prepareStatement(MYSQL.CHECKED_USER_EXSITER_TELE);
            stat.setString(1, telepohone);
            ResultSet rs = stat.executeQuery();
            rs.next();
            if (rs.getInt(1)!=0){
                stat = conn.prepareStatement(MYSQL.SELECT_USER_LEGALITY_TELE);
                stat.setString(1, telepohone);
                rs = stat.executeQuery();
                rs.next();
                if (rs.getString("password").equals(password)){
                    String username = rs.getString("username");
                    int id = rs.getInt("id");
                    pw.print("登录成功,正在跳转");
                    if (conn!=null) conn.close();
                    UserInfo.User user = new UserInfo.User(id);
                    request.getSession().setAttribute("user",user);
                    response.sendRedirect("/index.jsp");
                }else{
                    if (conn!=null) conn.close();
                    pw.print("密码错误,2s后自动跳转");
                    response.setHeader("refresh","2;url=/login.jsp");
                }
            }else{
                if (conn!=null) conn.close();
                pw.print("用户不存在,请检查手机号码  2s自动跳转回登陆页面");
                response.setHeader("refresh","2;url=/login.jsp");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("请求错误 错误代码：002");
    }
}
