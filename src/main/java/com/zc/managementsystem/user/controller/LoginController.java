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
    String email;
    String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LoginParams{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

@Controller
public class LoginController {
    static Logger logger = LogManager.getLogger(LoginController.class);
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping(value="/api/auth/login")
    public JsonResponse login(@RequestBody LoginParams loginParams, HttpServletRequest request) {
        logger.info("request params: {}", loginParams);
        // 获取用户信息
        User user = this.userService.getUserByEmail(loginParams.email);
        // 用户不存在或者是用户密码不正确就锁定
        if ((user==null) || (loginParams.password.equals(user.getPassword()) == false)) {
            return new JsonResponse(JsonResponse.RetCodeLoginFailed, "Wrong email or password;");
        }
        // 设置用户
        HttpSession session = request.getSession();
        // 清理敏感信息
        user.setPassword(null);
        session.setAttribute(AuthConst.USER_KEY, user);
        session.setAttribute(AuthConst.USER_ID_KEY, user.getId());
        return new JsonResponse(null);
    }


}
