function updatepage (username,id) {
    if (username != -1) {
        document.getElementById("login").innerHTML = "欢迎你，"+username;
        document.getElementById("logina").setAttribute("href","../uCenter?id="+id);
        document.getElementById("login").style.color="#66ccff"
        document.getElementById("reg").innerHTML="注销登录";
        document.getElementById("rega").setAttribute("href","../Logout");
		}
}