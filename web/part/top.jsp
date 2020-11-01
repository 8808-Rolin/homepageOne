<%@ page import="java.io.Console" %>
<%@ page import="com.Rolin.type.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/6
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
	<link href="../css/top.css" rel="stylesheet" type="text/css"/>
	<script src="${pageContext.request.contextPath}/js/top.js" type="text/javascript"></script>
	<%
		String username;
		String id = "-1";
		UserInfo.User user = (UserInfo.User) session.getAttribute("user");
		if (user==null){
			username = "-1";
		}else {
			username = user.username;
			id = String.valueOf(user.id);
			Cookie cookie = new Cookie("JSESSIONID",session.getId());
			cookie.setMaxAge(60*60*24*7);
			response.addCookie(cookie);
		}
	%>
</head>
<body vlink="gray" onload="updatepage('<%=username%>','<%=id%>')">
	<div class="top" >
				<ul class="toplist">
					<li><a href="${pageContext.request.contextPath}/index.jsp"><p class="text">主页</p></a></li>
					<li><a href="javascript:alert('收藏夹还未开放');"><p class="text">收藏</p></a></li>
					<li><a href="javascript:alert('资源站还未开放');" ><p class="text">资源站</p></a></li>
					<li><a href="${pageContext.request.contextPath}/login.jsp" id="logina"><p class="text" id="login">登录</p></a></li>
					<li><a href="${pageContext.request.contextPath}/reg/reg.jsp" id="rega"><p class="text" id="reg">注册</p></a></li>
					<li><a href="${pageContext.request.contextPath}/testCodePage.jsp"><p class="text">测试网页</p></a></li>
				</ul>
		</div>


</body>

</html>
