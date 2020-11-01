<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/26
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>这里是测试页面_AJAX</title>
    <script type="text/javascript" src = "js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        //同步请求点击事件
        function sendRequest(){
            location.href = "ajaxTestServlet?name=admin&password=tongbu"
        }
        //异步请求点击事件
        function sendAsynRequest(){
            //创建ajax对象
            var xmlHttp = new XMLHttpRequest();
            //设置回调函数，目的是处理服务器完全返回的数据
            xmlHttp.onreadystatechange = function (){
                /**
                 * 这是一个回调函数，在ajax引擎对象与服务器通信状态码改变的时候调用
                 * ajax对象与服务器通信状态码xmlHttp.readystate，范围0-4
                 * 0：请求未初始化，1：服务器连接已建立
                 * 2.请求已接收 3，请求处理中 4.请求已完成，且相应就绪
                 * 这个回调函数一共被调用四次，但只有状态码4的时候才表示服务器响应完成处理完成
                 * ajax引擎通信状态码为4和Http通信状态码200
                 */
                if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
                    var content = xmlHttp.responseText;
                    alert(content);
                }
            }
            xmlHttp.open("get","ajaxTestServlet?name=admin&password=yibu");
            xmlHttp.send();
        }
    </script>
    <style>
        .a{
            position: relative;
            top:80px;
        }
    </style>
</head>
<body>
<%@include file="/part/top.jsp"%>

    <input type="button" value="发送同步请求" onclick="sendRequest()" class="a">
    <input type="button" value="发送异步请求" onclick="sendAsynRequest()" class="a">

</body>
</html>
