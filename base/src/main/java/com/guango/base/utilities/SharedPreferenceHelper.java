package com.guango.base.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.guango.base.model.MessageGroupModel;
import com.guango.base.model.UserInfo;
import com.guango.base.store.GlobalInfo;

import java.util.LinkedList;

public class SharedPreferenceHelper {

    private static String SP_NAME = "sp_name";
    private static String SP_KEY = "sp_key";
    private static SharedPreferences preferences = GlobalInfo.getGlobalContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    public static Gson gson = new GsonBuilder().create();

    private static DataModel mDataModel = null;
    public static DataModel getModel(){
        if(mDataModel == null){
            mDataModel = getData();
            return mDataModel;
        }else{
            return mDataModel;
        }
    }

    public static class DataModel{
        public String getmUserId() {
            return mUserId;
        }

        public void setmUserId(String mUserId) {
            this.mUserId = mUserId;
        }

        @SerializedName("user_id")
        private String mUserId;

        public String getmUsername() {
            return mUsername;
        }

        public void setUserName(String user){
            this.mUsername = user;
        }

        @SerializedName("user_name")
        private String mUsername;

        public LinkedList<MessageGroupModel> getMessageHistory() {
            return messageHistory;
        }

        public void setMessageHistory(LinkedList<MessageGroupModel> messageHistory) {
            this.messageHistory = messageHistory;
        }

        @SerializedName("message_history")
        private LinkedList<MessageGroupModel> messageHistory;

        public void setFriendHistory(LinkedList<UserInfo> friendHistory) {
            this.friendHistory = friendHistory;
        }

        public LinkedList<UserInfo> getFriendHistory() {
            return friendHistory;
        }

        @SerializedName("friend_history")
        private LinkedList<UserInfo> friendHistory;
    }

    public static void saveData(DataModel model){
        String serializedData = gson.toJson(model);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SP_KEY, serializedData);
        editor.apply();
    }

    public static DataModel getData(){
        String json = preferences.getString(SP_KEY, "{}");
        return gson.fromJson(json, DataModel.class);
    }
}
