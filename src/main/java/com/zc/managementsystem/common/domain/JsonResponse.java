package com.zc.managementsystem.common.domain;

import java.io.Serializable;

public class JsonResponse implements Serializable {
    public static int RetCodeUnknownError = -1;
    public static int RetCodeNoPermission = 9000;
    public static int RetCodeLoginFailed = 10001;
    public static int RetCodeNotFound = 10002;

    private int retCode;
    private String message;
    private Object body;

    public JsonResponse(int retCode, String message) {
        this.setRetCode(retCode);
        this.setMessage(message);
    }
    public JsonResponse(Object body) {
        this.setBody(body);
        this.setMessage("");
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public int getRetCode() {
        return retCode;
    }

    public Object getBody() {
        return body;
    }

    public String getMessage() {
        return message;
    }
}
