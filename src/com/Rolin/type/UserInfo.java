package com.Rolin.type;


import com.Rolin.utils.Constant.*;

import java.io.Serializable;
import java.sql.*;

public class UserInfo { //用户信息
    public static class User implements Serializable {
        private static final long serialVersionUID = 1L;
        public int id;
        public String username;
        public String telephone;
        public String email;

        Connection conn;
        PreparedStatement stat;
        public  User(int id) {
            try {
                conn = null;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://rolin.org.cn:3300/WebServer?useUnicode=true&characterEncoding=UTF-8",
                        "root", "120605.rolin");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            try {
                stat = conn.prepareStatement(MYSQL.CHECKED_USER_EXSITER_ID);
                stat.setInt(1, id);
                ResultSet rs = stat.executeQuery();
                rs.next();
                if (rs.getInt(1) != 0) {
                    stat = conn.prepareStatement(MYSQL.SELECT_USER_LEGALITY_ID);
                    stat.setInt(1, id);
                    rs = stat.executeQuery();
                    rs.next();
                    this.id = id;
                    this.username = rs.getString("username");
                    this.telephone = rs.getString("telephone");
                    this.email = rs.getString("email");
                    if (conn!=null) conn.close();
                }else{
                    if (conn!=null) conn.close();
                    this.username="-1";
                    this.id=-1;
                    this.telephone="-1";
                    this.email="-1";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        public  User(String telephone) {
            try {
                conn = null;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://rolin.org.cn:3300/WebServer?useUnicode=true&characterEncoding=UTF-8",
                        "root", "120605.rolin");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            try {
                stat = conn.prepareStatement(MYSQL.CHECKED_USER_EXSITER_TELE);
                stat.setString(1, telephone);
                ResultSet rs = stat.executeQuery();
                rs.next();
                if (rs.getInt(1) != 0) {
                    stat = conn.prepareStatement(MYSQL.SELECT_USER_LEGALITY_TELE);
                    stat.setString(1, telephone);
                    rs = stat.executeQuery();
                    rs.next();
                    this.id = rs.getInt("id");
                    this.username = rs.getString("username");
                    this.telephone = telephone;
                    this.email = rs.getString("email");
                    if (conn!=null) conn.close();
                } else {
                    if (conn!=null) conn.close();
                    this.username="-1";
                    this.id=-1;
                    this.telephone="-1";
                    this.email="-1";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}

