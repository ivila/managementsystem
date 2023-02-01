package com.zc.managementsystem.common.controller;

import com.zc.managementsystem.common.domain.AuthConst;
import com.zc.managementsystem.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AuthConst.USER_KEY);
        if (user == null) {
            response.setStatus(401);
            return false;
        }
        return true;
    }
}

