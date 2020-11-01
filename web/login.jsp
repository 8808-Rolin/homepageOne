<%@ page import="com.Rolin.type.Type" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/6
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>空白·登录</title>
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/ico" sizes="128x128" href="img/n9.ico"/>
</head>

<body >
<%@include file="/part/top.jsp"%>

<div class="logstyle">
    <div class="logtext">
        <span class="line"></span>
        <span class="title"><b>登录</b></span>
        <span class="line"></span>
    </div>
</div>

<div>
    <form class="login" action="../logindeal" method="post" name="logininfo">
        <input type="text" placeholder="手机号码" name="telephone"
               class="textbox" id="name"/>
        <br />
        <input type="password" placeholder="请输入密码" name="password"
               class="textbox" id="password" autocomplete="off"/>
        <br />
        <input type="submit" value="Login" class="loginbtn" id="loginbtn" onclick="return checkInfo()"/>
        <br />
        <a href="#" class="password-found-a">忘记密码？立即找回</a>
    </form>

</div>
</body>
<script>
    <%
        String deliveryCode = "";
        if (session.getAttribute("deliveryCode")!=null) deliveryCode = (String)session.getAttribute("deliveryCode");
        session.setAttribute("deliveryCode",null);

        Type.UserReg u = new Type.UserReg("null","null","null");
        if((Type.UserReg)session.getAttribute("UserReturn")!=null) u=(Type.UserReg)session.getAttribute("UserReturn");
    %>
    switch ("<%=deliveryCode%>"){
        case "":
            break;
        case "200":
            alert("注册成功");
            document.getElementById("name").value = "<%=u.Tele%>";
            break;
        default:
            alert("返回代码："+"<%=deliveryCode%>");
    }
    function checkInfo() {
        var account = document.getElementById("name").value;
        var password = document.getElementById("password").value;
        if (account == "" || password == ""){
            alert("账号或密码不能为空");
            return false;
        }else if(account.length < 11){
            alert("请输入正确的手机号码");
            document.getElementById("name").value = "";
            return false;
        }else if(password.length < 8){
            alert("请输入8位或8位以上的密码");
            return false;
        }else{
            return true;
        }
    }
</script>
</html>