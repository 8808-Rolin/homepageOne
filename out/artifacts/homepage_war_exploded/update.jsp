<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/6
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>更新日志</title>
    <link rel="icon" type="image/ico" sizes="128x128" href="img/n9.ico"/>
    <style>
        body {
            background-repeat: repeat-x;
            background-size: auto 100%;
            background-attachment: fixed;
            background-position: 50% 0;
            background-color: #eaeadf;
            -webkit-background-size: auto 100%;
            min-height: 300px;
            align-items: center;
            justify-content: center;
        }
        .update_div{
            margin-top: 100px;
            text-align: center;
        }
        .title{
            font-size: 50px;
        }
        .time{
            color: brown;
            font-size: 20px;
        }
        .context{
            color: #000000;
        }
    </style>
</head>
<body vlink="gray">
    <%@include file="/part/top.jsp"%>
<div class="update_div">
    <p class="title"><b>更新日志</b></p>
    <p class="time">2020年10月12日</p>
    <p class="context">优化 优化结构,提高运行速度</p>
    <p class="time">2020年10月8日</p>
    <p class="context">增加 登录功能</p>
    <p class="context">增加 个人中心(未完善)</p>
    <p class="context">进行中 优化代码结构</p>
    <p class="time">2020年10月7日</p>
    <p class="context">增加 登录功能</p>
    <p class="time">2020年10月3日</p>
    <p class="context">优化 重新设计了一波页面</p>
    <p class="time">2020年9月25日</p>
    <p class="context">初始建站</p>
</div>
</body>
</html>

