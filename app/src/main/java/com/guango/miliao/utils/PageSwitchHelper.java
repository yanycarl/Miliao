package com.guango.miliao.utils;

import android.content.Intent;
import android.os.Bundle;

import com.guango.account.Activity.LoginActivity;
import com.guango.base.RouteCallback;
import com.guango.base.store.GlobalInfo;
import com.guango.message.Activity.MessageActivity;
import com.guango.message.Activity.MessageFragment;
import com.guango.message.MessageService;
import com.guango.miliao.Activity.MainActivity;
import com.guango.phonebook.NewFriendActivity;
import com.guango.router.RouterMap;

public class PageSwitchHelper {

    public static void buildRouteMap(){
        RouterMap.setMap(new RouteCallback() {
            @Override
            public void jumpToMain() {
                realJumpToMain();
            }

            @Override
            public void jumpToLogin() {
                realJumpToLogin();
            }

            @Override
            public void jumpToMessageDetail(int index) {
                Intent intent = new Intent(GlobalInfo.getContext(), MessageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("group_id", index);
                intent.putExtras(bundle);
                GlobalInfo.getContext().startActivity(intent);
            }

            @Override
            public void jumpToMessageDetail(String name) {
                MessageService.openMessageByName(name);
            }

            @Override
            public void jumpToNewFriendActivity() {
                Intent intent = new Intent(GlobalInfo.getGlobalContext(), NewFriendActivity.class);
                GlobalInfo.getGlobalContext().startActivity(intent);
            }
        });
    }

    private static void realJumpToMain() {
        Intent intent = new Intent(GlobalInfo.getContext()
                , MainActivity.class);
        GlobalInfo.getContext().startActivity(intent);
    }

    private static void realJumpToLogin(){
        Intent intent = new Intent(GlobalInfo.getGlobalContext()
                , LoginActivity.class);
        GlobalInfo.getGlobalContext().startActivity(intent);
    }
}
