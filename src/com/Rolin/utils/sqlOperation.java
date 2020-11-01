package com.Rolin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class sqlOperation {
    private static Connection conn = null;
    private static PreparedStatement stat = null;
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://rolin.org.cn:3300/WebServer?useUnicode=true&characterEncoding=UTF-8","root","120605.rolin");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn() {
        return conn;
    }

    public static PreparedStatement getStat() {
        return stat;
    }

    public static void setStat(PreparedStatement stat) {
        sqlOperation.stat = stat;
    }

    public static void checkLogin(String tele , String password ,String successURL, String failURL){ //检查账号密码

    }
}
