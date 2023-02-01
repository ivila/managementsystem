package com.zc.managementsystem.user.controller;

import com.zc.managementsystem.common.domain.AuthConst;
import com.zc.managementsystem.common.domain.JsonResponse;
import com.zc.managementsystem.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class UserController {
    @ResponseBody
    @GetMapping(value="/api/user/current")
    public JsonResponse check(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 清理敏感信息
        User user = (User) session.getAttribute(AuthConst.USER_KEY);
        return new JsonResponse(user);
    }
}
