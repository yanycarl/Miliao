package com.guango.miliao.Activity;

import android.app.Application;
import android.content.IntentFilter;

import androidx.lifecycle.Observer;

import com.guango.base.exception.MyUnCaughtExceptionHandler;
import com.guango.base.model.MessageGroupModel;
import com.guango.base.model.UserInfo;
import com.guango.base.store.DataCenter;
import com.guango.base.store.GlobalInfo;
import com.guango.base.store.ServiceManager;
import com.guango.base.utilities.SharedPreferenceHelper;
import com.guango.miliao.utils.PageSwitchHelper;
import com.guango.phonebook.PhonebookService;
import com.guango.voice.VoiceReceiver;

import java.io.IOException;
import java.util.LinkedList;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());

        VoiceReceiver receiver = new VoiceReceiver();
        IntentFilter intent = new IntentFilter();
        intent.addAction(VoiceReceiver.ACTION);
        registerReceiver(receiver, intent);

        GlobalInfo.dataCenter = new DataCenter();
        GlobalInfo.setGlobalContext(this.getApplicationContext());

        final SharedPreferenceHelper.DataModel model = SharedPreferenceHelper.getModel();

        LinkedList<MessageGroupModel> list = model.getMessageHistory();
        if (list != null) {
            GlobalInfo.dataCenter.messageGroupModels.setValue(list);
        }
        LinkedList<UserInfo> list2 = model.getFriendHistory();
        if (list != null) {
            GlobalInfo.dataCenter.phonebook.setValue(list2);
        }

        try {
            GlobalInfo.myName = model.getmUsername();
        }catch (Exception e){
            //Ignore
        }

        GlobalInfo.dataCenter.messageGroupModels.observeForever(new Observer<LinkedList<MessageGroupModel>>() {
            @Override
            public void onChanged(LinkedList<MessageGroupModel> messageGroupModels) {
                SharedPreferenceHelper.DataModel newModel = SharedPreferenceHelper.getModel();
                newModel.setMessageHistory(messageGroupModels);
                SharedPreferenceHelper.saveData(newModel);
            }
        });

        GlobalInfo.dataCenter.phonebook.observeForever(new Observer<LinkedList<UserInfo>>() {
            @Override
            public void onChanged(LinkedList<UserInfo> userInfos) {
                SharedPreferenceHelper.DataModel newModel = SharedPreferenceHelper.getModel();
                newModel.setFriendHistory(userInfos);
                SharedPreferenceHelper.saveData(newModel);
            }
        });

        PageSwitchHelper.buildRouteMap();

        buildService();
    }

    private void buildService(){
        ServiceManager manager = new ServiceManager();
        manager.addService("phone_book", new PhonebookService());
        GlobalInfo.setServiceManager(manager);
    }
}
