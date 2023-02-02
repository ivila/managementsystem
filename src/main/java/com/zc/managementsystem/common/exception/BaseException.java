package com.zc.managementsystem.common.exception;

public class BaseException extends Exception {
    protected int resultCode;
    public BaseException(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    @Override
    public String getMessage() {
        return this.toString();
    }
}
