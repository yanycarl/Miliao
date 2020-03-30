package com.guango.base.model;

import com.google.gson.annotations.SerializedName;
import com.guango.base.Interface.IUserInfo;

public class SimpleUserInfo implements IUserInfo {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("name")
    private String name;

    public UserInfo.CONTACT_TYPE getType() {
        return type;
    }

    public void setType(UserInfo.CONTACT_TYPE type) {
        this.type = type;
    }

    private UserInfo.CONTACT_TYPE type = UserInfo.CONTACT_TYPE.RAW;
}
