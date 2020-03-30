package com.guango.base.Interface;

public interface IUserInfo {

    enum CONTACT_TYPE{
        RAW, SENT, PENDING, DONE
    }

    void setType(CONTACT_TYPE type);
}
