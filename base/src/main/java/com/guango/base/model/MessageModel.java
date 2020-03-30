package com.guango.base.model;

import com.google.gson.annotations.SerializedName;

public class MessageModel {

    public MessageModel(String info, String time, int type){
        this.info = info;
        this.time = time;
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public String getTime() {
        return time;
    }


    @SerializedName("info")
    private String info;

    @SerializedName("time")
    private String time;

    public int getType() {
        return type;
    }

    @SerializedName("type")
    private int type;
}
