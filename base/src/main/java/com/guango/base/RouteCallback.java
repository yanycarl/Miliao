package com.guango.base;

public interface RouteCallback {
    void jumpToMain();

    void jumpToLogin();

    void jumpToMessageDetail(int index);

    void jumpToMessageDetail(String name);

    void jumpToNewFriendActivity();
}
