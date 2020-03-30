package com.guango.base.model;

import com.google.gson.annotations.SerializedName;
import com.guango.base.model.MessageModel;

import java.util.LinkedList;

public class MessageGroupModel {
    public LinkedList<MessageModel> getMessageGroup() {
        return messageGroup;
    }

    public void addData(MessageModel messageModel){
        if(messageGroup == null){
            messageGroup = new LinkedList<>();
        }
        messageGroup.addLast(messageModel);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("message_group")
    private LinkedList<MessageModel> messageGroup;
}
