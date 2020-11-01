package com.Rolin.filter;

import com.Rolin.type.UserInfo;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.IOException;
@WebFilter(filterName = "/validateLogin", urlPatterns = { "/testCodePage.jsp" })
public class validateLogin implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        String username;
        String id = "-1";
        UserInfo.User user = (UserInfo.User) session.getAttribute("user");
        if (user==null){
            username = "-1";
            response.sendRedirect("/part/tips.jsp");
        }else {
            username = user.username;
            Cookie cookie = new Cookie("JSESSIONID",session.getId());
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
