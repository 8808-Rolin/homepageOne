package com.Rolin.utils;

import sun.security.provider.PolicySpiFile;

public class Constant {
    public static class MYSQL{
        public static final String LINK_MYSQL_URL= "jdbc:mysql://rolin.org.cn:3300/WebServer?useUnicode=true&characterEncoding=UTF-8";
        public static final String CHECKED_USER_EXSITER_TELE = "SELECT COUNT(*) FROM user WHERE TELEPHONE=?"; //通过手机号码查询用户是否存在
        public static final String CHECKED_USER_EXSITER_ID = "SELECT COUNT(*) FROM user WHERE ID=?"; //通过ID查询用户是否存在
        public static final String CHECKED_USER_EXSITER_USERNAME ="SELECT COUNT(*) FROM user WHERE username = ?"; //查询该名字是否存在
        public static final String CHECKED_USER_EXSITER_EMAIL ="SELECT COUNT(*) FROM user WHERE email = ?"; //查询该邮箱是否存在
        public static final String SELECT_USER_LEGALITY_TELE = "SELECT * FROM user WHERE TELEPHONE=?";   //通过手机号码查询用户信息
        public static final String SELECT_USER_LEGALITY_ID = "SELECT * FROM user WHERE ID=?"; //通过id查询用户信息
        public static final String REGISTER_USER_ALLINFO="INSERT INTO user (username,password,email,telephone) VALUES (?,?,?,?)";//注册用户



    }
}

