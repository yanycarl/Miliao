package com.guango.base.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

public class MessageFetchResponse {

    public int getCode() {
        return code;
    }

    @SerializedName("code")
    int code;

    public LinkedList<MessageGroupModel> getNewGroup() {
        return newGroup;
    }

    @SerializedName("new_group")
    LinkedList<MessageGroupModel> newGroup;

    public LinkedList<String> getRequests() {
        return requests;
    }

    @SerializedName("request")
    LinkedList<String> requests;

    public LinkedList<String> getNewFriends() {
        return newFriends;
    }

    @SerializedName("new_friends")
    LinkedList<String> newFriends;

    public LinkedList<String> getVoiceRequests() {
        return voiceRequests;
    }

    @SerializedName("voice_request")
    LinkedList<String> voiceRequests;

    public String getVersion() {
        return version;
    }

    @SerializedName("version")
    String version;
}
