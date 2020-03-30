package com.guango.account.Activity.task;

import android.os.Handler;
import android.os.Looper;

import com.guango.base.model.MessageGroupModel;
import com.guango.base.model.MessageModel;
import com.guango.base.store.GlobalInfo;
import com.guango.router.RouterMap;

import java.util.LinkedList;

public class LoginTask implements Runnable {
    @Override
    public void run() {
        LinkedList<MessageGroupModel> list = new LinkedList<>();
        MessageModel model = new MessageModel("我们已经成为好友。", "", 0);
        MessageGroupModel groupModel = new MessageGroupModel();
        groupModel.setName("客服小姐姐");
        groupModel.addData(model);
        list.addFirst(groupModel);
        GlobalInfo.dataCenter.messageGroupModels.postValue(list);

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                RouterMap.openRoute("login",null);
            }
        });
    }
}
