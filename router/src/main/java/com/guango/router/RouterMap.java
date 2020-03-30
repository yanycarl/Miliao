package com.guango.router;

import android.os.Bundle;

import com.guango.base.RouteCallback;

public class RouterMap {

    private static RouteCallback mCallback;

    public static void setMap(RouteCallback callback) {
        mCallback = callback;
    }

    public static void openRoute(String route, Bundle args) {
        switch (route) {
            case "main":
                mCallback.jumpToMain();
                break;
            case "login":
                mCallback.jumpToLogin();
                break;
            case "message_detail":
                if (args.containsKey("index") && args.get("index") != null) {
                    mCallback.jumpToMessageDetail(args.getInt("index"));
                } else if (args.containsKey("name") && args.get("name") != null) {
                    mCallback.jumpToMessageDetail((String) args.get("name"));
                }
                break;
            case "new_friend":
                mCallback.jumpToNewFriendActivity();
                break;
        }
    }
}
