package com.zc.managementsystem.common.controller;

import com.zc.managementsystem.common.domain.JsonResponse;
import com.zc.managementsystem.common.exception.BaseException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResponse globalException(HttpServletResponse response, Exception ex){
        if (BaseException.class.isInstance(ex)) {
            BaseException currentException = (BaseException)ex;
            JsonResponse result = new JsonResponse(
                    currentException.getResultCode(),
                    "request error:"+currentException.getMessage()
            );
            return result;
        }

        JsonResponse result = new JsonResponse(
                JsonResponse.RetCodeUnknownError,
                "request error:"+ex.getMessage()
        );
        return result;
    }

}