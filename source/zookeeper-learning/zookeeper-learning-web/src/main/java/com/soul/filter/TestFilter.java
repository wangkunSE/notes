package com.soul.filter;

import com.soul.domain.User;
import com.soul.webuser.WebUser;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (Objects.equals("user", name)) {
                    String user = cookie.getValue();
                    if (user != null) {
//            User currentUser = JSON.parseObject(user, User.class);
                        User currentUser = new User();
                        currentUser.setUserName(user);
                        WebUser.setUser(currentUser);
                    }
                }
            }
        }
       /* String user = request.getParameter("user");
        if (user != null) {
//            User currentUser = JSON.parseObject(user, User.class);
            User currentUser = new User();
            currentUser.setUserName(user);
            WebUser.setUser(currentUser);
        }*/
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
