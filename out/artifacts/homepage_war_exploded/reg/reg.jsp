<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/3
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.lang.annotation.Documented" %>
<%@ page import="com.Rolin.type.Type" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>空白·注册</title>
    <link type="text/css" href="../css/reg.css" rel="stylesheet" />

</head>

<body vlink="gray">
    <%@ include file="/part/top.jsp"%>
    <div class="regstyle">
        <div class="regtext">
            <span class="line"></span>
            <span class="title"><b>注册</b></span>
            <span class="line"></span>
        </div>
    </div>

    <div class="info">
        <form action="../regdeal" method="post" name="reginfo" >
            <input type="text" placeholder="请输入昵称" name="name" class="textbox" id="name"  autocomplete="off"/>
            <br />
            <input type="password" placeholder="请输入密码" name="password" class="textbox" id="firstpassword" autocomplete="off"/>
            <br />
            <input type="password" placeholder="确认密码" name="conpass" class="textbox" id="againpassword" autocomplete="off"/>
            <br />
            <input type="email" placeholder="常用邮箱" name="email" class="textbox" id="email" />
            <br />
            <input type="text" placeholder="手机号码" name="tele"
                   onkeyup="this.value=this.value.replace(/\D/g,'')"
                   onafterpaste="this.value=this.value.replace(/\D/g,'')"
                   class="textbox"  maxlength="11" id="tele"  autocomplete="off"/>
            <br />
            <input type="text" placeholder="请输入验证码"  class="vertext" id="vertext" value="" name="vercode"/>
           <!-- <canvas id="verbox" width="100" height="43" onclick="dj()" class="verbox"></canvas> -->
            <img src="${pageContext.request.contextPath}/Verification" alt="Not Found" id="verbox" width="60" height="20"  class="verbox" onclick="update()"/>
            <div class="abox">
                <input type="checkbox" name="agree" style="vertical-align:middle;" id="ag" onclick="checkAgree()"/><label for="ag">我已同意<a href="#">《祖安公司用户使用协议》</a>和<a href="#">《祖安公司隐私政策》</a></label>
            </div>
            <input type="submit" onclick="return checkInfo()" name="reginfo" value="确 认 注 册"  class="sub" id="butt" disabled="disabled"/>
            <!-- <button class="sub"  type="submit">确 认 注 册</button>-->
        </form>
        <div class="ex">
            <p><a href="../login.jsp">已有帐号，直接登录 --></a></p>
        </div>
    </div>

</body>


<script type="text/javascript">
    <%
        String ErrorCode = null;
        if(session.getAttribute("ErrorCode")!=null) ErrorCode = (String)session.getAttribute("ErrorCode");
        session.setAttribute("ErrorCode",null);
        if (ErrorCode != null){
            request.getRequestDispatcher("/reg/reg.jsp?id="+ErrorCode).forward(request,response);
            return;
        }
        Type.UserReg u = new Type.UserReg("null","null","null");
        String code = request.getParameter("id");
        if((Type.UserReg)session.getAttribute("UserReturn")!=null) u=(Type.UserReg)session.getAttribute("UserReturn");
        session.setAttribute("UserReturn",null);
    %>
    if("<%=u.Name%>"!=="null"){
        document.getElementById("name").value = "<%=u.Name%>";
        document.getElementById("tele").value = "<%=u.Tele%>";
        document.getElementById("email").value = "<%=u.Email%>";
    }
    switch ("<%=code%>"){
        case "null":
            break;
        case "3000":
            alert("验证码输入错误");
            break;
        case "3001":
            alert("你输入的用户名已被注册\n请重试或直接使用手机号进行登录");
            break;
        case "3002":
            alert("你输入的手机号码已被注册\n请重试");
            break;
        case "3003":
            alert("你输入的邮箱已被注册\n请重试");
            break;
        default:
            alert("错误代码：<%=code%>");
    }

    var telebealoon;
    function checkInfo(){
        const a = document.getElementById("firstpassword").value;
        const b = document.getElementById("againpassword").value;
        const email = document.getElementById("email").value;
        const tele = document.getElementById("tele").value;
        const name = document.getElementById("name").value;
        const emailReg = /[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/; //邮箱正则表达式
        const teleReg = /^1(3|4|5|6|7|8|9)\d{9}$/; //手机正则表达式

        if (a=="" || b=="" || name==""||email==""||tele==""){
            alert("请完整填写注册信息");
            return false;
        }else if(document.getElementById("firstpassword").value.length<8 || document.getElementById("firstpassword").value.length<8){
			document.getElementById("firstpassword").value=null;
			document.getElementById("againpassword").value = null;
			alert("密码位数过少,请输入大于八位的密码");
			return false;
		}else if(a!=b){
            document.getElementById("firstpassword").value=null;
            document.getElementById("againpassword").value = null;
            alert("两次密码输入不一致");
            return false;
        }else if(document.getElementById("tele").value.length<11){
            alert("请输入11位手机号码");
            return false;
        }else if(!(teleReg.test(tele))){
            alert("手机号码格式有误,请输入正确的手机号码");
            return false;
        }else if(!emailReg.test(email) ){
            alert("请输入正确的邮箱地址");
            return false;
        }else if(a==b){
            return true;
        }else{
            alert("出现未知错误(&001)");
            return false;
        }
    }
    function checkAgree(){
        if(document.getElementById("ag").checked){
            document.getElementById("butt").removeAttribute("disabled");
            document.getElementById("butt").style.backgroundColor="#66CCFF";
        }else{
            document.getElementById("butt").disabled="disabled";
            document.getElementById("butt").style.backgroundColor="#C2C2C2";
        }
    }


    function update(){ //更新验证码
        var rand = Math.random()*100;
        document.getElementById("verbox").setAttribute("src","${pageContext.request.contextPath}/Verification?id="+rand);
    }


</script>

</html>
