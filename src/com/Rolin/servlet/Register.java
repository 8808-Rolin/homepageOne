package com.Rolin.servlet;
import com.Rolin.type.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static com.Rolin.utils.Constant.MYSQL.*;

@WebServlet("/regdeal")
public class Register extends HttpServlet {
    Connection conn;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String vercode = (String) request.getSession().getAttribute("VerCode");
        String input = request.getParameter("vercode");
        String username = request.getParameter("name"); //名字
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String telephone = request.getParameter("tele");
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();

        //验证码验证 返回代码3000
        if (!vercode.equals(input)){
            session.setAttribute("UserReturn",new Type.UserReg(username,telephone,email));
            session.setAttribute("ErrorCode","3000");
            response.sendRedirect("/reg/reg.jsp");
            return;
        }
        try {
            conn =null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(LINK_MYSQL_URL,"root","120605.rolin");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        //用户名重复验证 返回代码3001
        if (checkRepeat(username, CHECKED_USER_EXSITER_USERNAME, conn)) {
            session.setAttribute("UserReturn",new Type.UserReg("",telephone,email));
            session.setAttribute("ErrorCode","3001");
            response.sendRedirect("/reg/reg.jsp");
            return;
        }
        //电话重复验证 返回代码3002
        if (checkRepeat(telephone, CHECKED_USER_EXSITER_TELE, conn)) {
            session.setAttribute("UserReturn",new Type.UserReg(username,"",email));
            session.setAttribute("ErrorCode","3002");
            //request.getRequestDispatcher("/reg/reg.jsp?id=3002").forward(request,response);
            response.sendRedirect("/reg/reg.jsp");
            return;
        }
        //邮箱验证 返回代码3003
        if (checkRepeat(email, CHECKED_USER_EXSITER_EMAIL, conn)) {
            session.setAttribute("UserReturn",new Type.UserReg(username,telephone,""));
            session.setAttribute("ErrorCode","3003");
            response.sendRedirect("/reg/reg.jsp");
            return;
        }
        //执行注册操作
        if (session.getAttribute("ErrorCode")==null){
            try {
                PreparedStatement stat;
                stat = conn.prepareStatement(REGISTER_USER_ALLINFO);
                stat.setString(1, username);
                stat.setString(2, password);
                stat.setString(3, email);
                stat.setString(4, telephone);
                stat.executeUpdate();
                System.out.println("Success");
                closeconn(conn);
                session.setAttribute("deliveryCode","200");
                session.setAttribute("UserReturn",new Type.UserReg("",telephone,""));
                response.sendRedirect("login.jsp");
            } catch (SQLException throwables) {
                System.out.println("Failure");
                throwables.printStackTrace();
            }
        }else{
            session.setAttribute("UserReturn",new Type.UserReg(username,telephone,email));
            session.setAttribute("ErrorCode","8888"); //错误代码8888
            response.sendRedirect("/reg/reg.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write("<html>\n" +
                "\t<head>\n" +
                "\t\t<meta charset=\"utf-8\">\n" +
                "\t\t<title>注册处理</title>\n" +
                "\t</head>\n" +
                "\t\n" +
                "\t<body>");
        pw.write("请求错误");
        pw.write("</body>\n" +
                "</html>");
    }
    //检查是否重复 如果重复则返回false 不重复则返回true
    public boolean checkRepeat(String value, String SQL, Connection conn){
        PreparedStatement stat;
        try {
            stat = conn.prepareStatement(SQL);
            stat.setString(1,value);
            ResultSet res = stat.executeQuery();
            res.next();
            return res.getInt(1) != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return true;
        }
    }

    public void closeconn(Connection conn){
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
