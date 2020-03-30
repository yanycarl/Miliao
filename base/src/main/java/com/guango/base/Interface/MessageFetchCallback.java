package com.guango.base.Interface;

import com.guango.base.model.MessageFetchResponse;

public abstract class MessageFetchCallback {
    public void onMessageGot(MessageFetchResponse response){}

    public void onMessageSent(String toWhom, String msg){}
}
