package com.guango.base.model;

import com.google.gson.annotations.SerializedName;

public class MessageSendResponse {

    public int getCode() {
        return code;
    }

    @SerializedName("code")
    int code;
}
