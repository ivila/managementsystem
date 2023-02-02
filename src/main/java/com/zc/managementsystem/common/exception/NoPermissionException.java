package com.zc.managementsystem.common.exception;

import com.zc.managementsystem.common.domain.JsonResponse;

public class NoPermissionException extends BaseException {
    private String email;
    private String permission;

    public NoPermissionException(String email, String permission) {
        super(JsonResponse.RetCodeNoPermission);
        this.email = email;
        this.permission = permission;

    }

    @Override
    public String toString() {
        return "NoPermissionException{" +
                "email='" + email + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
