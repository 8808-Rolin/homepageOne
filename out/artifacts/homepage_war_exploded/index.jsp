<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/6
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>空白初始页</title>
    <link rel="icon" type="image/ico" sizes="128x128" href="img/n9.ico"/>
    <link href="css/index.css" charset="UTF-8" rel="stylesheet" type="text/css" />
    <script>
        function getfocus(){
            document.getElementById("search").style.display="block";
            document.getElementById("btn").style.display="none";
            document.getElementById("inp").focus();
        }
        function lofocus(){
            document.getElementById("btn").style.display="block";
            document.getElementById("search").style.display="none";
            document.getElementById("inp").value="";
        }
        document.addEventListener('keyup', function(e) {
            let currKey = 0
            e = e || event
            currKey = e.keyCode || e.which || e.charCode
            if (currKey === 13) {
                getfocus();
            }})
    </script>
</head>
<body vlink="gray">
    <div class="bottom">
        <a href="update.jsp">更新日志</a>
        <p>Copyright Rolin</p>
    </div>
    <%@include file="/part/top.jsp"%>
    <div style="text-align: center;">
        <div class="slogan">
            <p><b>无限接近于零，却又不等于零</b></p>
        </div>
        <div class="search_div" id="search">
            <form action="http://www.baidu.com/s" method="get" target="_self" name="bdfm" class="search_form">
                <input id="inp" type="text" class="search_input" name="word" onblur="lofocus()" autocomplete="off"/>
            </form>
        </div>
        <div id="btn">
            <input type="button" class="search_btn" value="Search" onclick="getfocus()"/>
        </div>
    </div>
</body>
</html>
