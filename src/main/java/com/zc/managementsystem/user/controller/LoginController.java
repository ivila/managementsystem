package com.zc.managementsystem.user.controller;

import com.zc.managementsystem.common.domain.AuthConst;
import com.zc.managementsystem.common.domain.JsonResponse;
import com.zc.managementsystem.user.domain.User;
import com.zc.managementsystem.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

class LoginParams {
    String userName;
    String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "LoginParams{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

@Controller
public class LoginController {
    static Logger logger = LogManager.getLogger("main");
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping(value="/api/auth/login")
    public JsonResponse login(@RequestBody LoginParams loginParams, HttpServletRequest request) {
        logger.info(loginParams);
        // 获取用户信息
        User user = this.userService.getUserByUserName(loginParams.userName);
        logger.info(user);
        // 用户不存在或者是用户密码不正确就锁定
        if ((user==null) || (loginParams.password.equals(user.getPassword()) == false)) {
            request.changeSessionId();
            return new JsonResponse(JsonResponse.RetCodeLoginFailed, "Wrong username or password;");
        }
        // 设置用户
        HttpSession session = request.getSession();
        // 清理敏感信息
        user.setPassword(null);
        session.setAttribute(AuthConst.USER_KEY, user);
        session.setAttribute(AuthConst.USER_ID_KEY, user.getId());
        return new JsonResponse(null);
    }

    @ResponseBody
    @GetMapping(value="/api/auth/check")
    public JsonResponse check(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 清理敏感信息
        User user = (User) session.getAttribute(AuthConst.USER_KEY);
        return new JsonResponse(user);
    }
}
