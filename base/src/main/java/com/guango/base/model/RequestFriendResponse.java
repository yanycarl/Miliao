package com.guango.base.model;

import com.google.gson.annotations.SerializedName;

public class RequestFriendResponse {

    public int getCode() {
        return code;
    }

    @SerializedName("code")
    int code;
}
