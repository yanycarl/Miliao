package com.guango.base.model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("code")
    private int code;

    public int getCode() {
        return code;
    }

    public String getUserId() {
        return userId;
    }

    @SerializedName("user_id")
    private String userId;
}
