package com.zc.managementsystem.common.controller;

import com.zc.managementsystem.common.domain.JsonResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResponse globalException(HttpServletResponse response, Exception ex){

        JsonResponse result = new JsonResponse(
                JsonResponse.RetCodeLoginFailed,
                "request error:"+ex.getMessage()
        );
        return result;
    }

}