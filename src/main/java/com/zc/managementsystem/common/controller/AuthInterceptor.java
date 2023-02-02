package com.zc.managementsystem.common.controller;

import com.zc.managementsystem.common.domain.AuthConst;
import com.zc.managementsystem.common.exception.NoPermissionException;
import com.zc.managementsystem.user.annotation.NeedPermission;
import com.zc.managementsystem.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    static Logger logger = LogManager.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AuthConst.USER_KEY);
        if (user == null) {
            response.setStatus(401);
            return false;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        NeedPermission needPermission = AnnotationUtils.findAnnotation(method, NeedPermission.class);

        if (needPermission == null) {
            return true;
        }
        for (String permission: needPermission.value()) {
            if (!user.hasPermission(permission)) {
                logger.info("user {} lack of permission {}", user.getEmail(), permission);
                throw new NoPermissionException(user.getEmail(), permission);
            }
        }


        
        return true;
    }
}
