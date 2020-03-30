package com.guango.base.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

public class SearchFriendResponse {

    @SerializedName("code")
    int code;

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String request_code) {
        this.requestCode = requestCode;
    }

    @SerializedName("request_code")
    private String requestCode;

    public LinkedList<SimpleUserInfo> getData() {
        return data;
    }

    public void setData(LinkedList<SimpleUserInfo> data) {
        this.data = data;
    }

    @SerializedName("data")
    private LinkedList<SimpleUserInfo> data;
}
