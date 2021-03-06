package com.guango.message.Presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;

import com.guango.base.Interface.IUserInfo;
import com.guango.base.Interface.MessageFetchCallback;
import com.guango.base.activity.BaseActivity;
import com.guango.base.model.MessageFetchResponse;
import com.guango.base.model.MessageGroupModel;
import com.guango.base.model.MessageModel;
import com.guango.base.model.UserInfo;
import com.guango.base.store.GlobalInfo;
import com.guango.base.utilities.SharedPreferenceHelper;
import com.guango.base.utilities.ThreadHelper;
import com.guango.message.Task.FetchMessageTask;
import com.guango.network.Fetcher.MessageFetcher;
import com.guango.router.RouterMap;

import java.util.LinkedList;

public class MessagePresenter {

    private static String url = "";
    private static boolean versionHasNotShown = true;

    public static void openMessage(int groupId) {
        LinkedList<MessageModel> list = GlobalInfo.dataCenter.messageGroupModels.getValue().get(groupId).getMessageGroup();
        GlobalInfo.dataCenter.currentGroup.setValue(list);
        Bundle bundle = new Bundle();
        bundle.putInt("index", groupId);
        RouterMap.openRoute("message_detail", bundle);
    }

    public static void openMessageByName(String name) {
        LinkedList<MessageGroupModel> list = GlobalInfo.dataCenter.messageGroupModels.getValue();
        int index = 0;
        if (list != null) {
            for (MessageGroupModel groupModel : list) {
                if (groupModel.getName().equals(name)) {
                    GlobalInfo.dataCenter.currentGroup.setValue(groupModel.getMessageGroup());
                    openMessage(index);
                    return;
                }
                index++;
            }
        } else {
            list = new LinkedList<>();
        }
        MessageGroupModel groupModel = new MessageGroupModel();
        groupModel.setName(name);
        groupModel.addData(new MessageModel("我们已成为好友", "", 0));
        list.addFirst(groupModel);
        GlobalInfo.dataCenter.messageGroupModels.setValue(list);
        openMessage(0);
    }

    public static void sendMsg(final String toWhom, final String msg) {
        MessageFetcher.sendMsg(toWhom, msg, new MessageFetchCallback() {
            @Override
            public void onMessageSent(String toWhom, String msg) {
                insertMsg(toWhom, msg);
            }
        });
    }

    private static void insertMsg(String whom, String msg) {
        LinkedList<MessageGroupModel> models = GlobalInfo.dataCenter.messageGroupModels.getValue();
        LinkedList<MessageModel> list = new LinkedList<>();
        for (MessageGroupModel model : models) {
            if (model.getName().equals(whom)) {
                list = model.getMessageGroup();
                list.addLast(new MessageModel(msg, "", 1));
            }
        }
        GlobalInfo.dataCenter.messageGroupModels.postValue(models);
        GlobalInfo.dataCenter.currentGroup.postValue(list);
    }

    public static void startFetchMessage() {
        ThreadHelper.runTask(new FetchMessageTask());
    }

    public static void fetchMessage() {
        MessageFetcher.fetchMessage(new MessageFetchCallback() {
            @Override
            public void onMessageGot(MessageFetchResponse response) {
                resolveResponse(response);
                resolveResponseForRequest(response);
                resolveResponseForVoice(response);
                resolveResponseForNewFriend(response);
                resolveResponseForNewVersion(response);
            }
        });
    }

    private static DialogInterface.OnClickListener backDialogListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == -1) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                GlobalInfo.getGlobalContext().startActivity(intent);
                BaseActivity.closeAllActivity();
            } else {
                BaseActivity.closeAllActivity();
            }
            SharedPreferenceHelper.cleanData();
            Process.killProcess(Process.myPid());
        }
    };

    private static void resolveResponseForNewVersion(MessageFetchResponse response) {
        if (versionHasNotShown && response.getVersion() != null) {
            versionHasNotShown = false;
            AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.activityStack.getFirst());
            builder.setTitle("有新的版本发布，当前版本将不可用");
            url = response.getVersion();
            builder.setCancelable(false);
            builder.setPositiveButton("我要升级", backDialogListener);
            builder.setNegativeButton("不升级了", backDialogListener);
            builder.show();
        }
    }

    private static void resolveResponseForNewFriend(MessageFetchResponse body) {
        if (body.getNewFriends() == null) {
            return;
        }
        LinkedList<String> newFriends = body.getNewFriends();
        LinkedList<UserInfo> address = GlobalInfo.dataCenter.phonebook.getValue();
        if (address == null) {
            return;
        }
        for (String name : newFriends) {
            for (UserInfo info : address) {
                if (info.getName().equals(name)) {
                    return;
                }
            }
            UserInfo userInfo = new UserInfo();
            userInfo.setName(name);
            userInfo.setType(UserInfo.CONTACT_TYPE.DONE);
            address.addFirst(userInfo);
        }
        GlobalInfo.dataCenter.phonebook.postValue(address);
    }

    private static void resolveResponseForVoice(MessageFetchResponse body) {
//        if (body.getVoiceRequests() == null) {
//            return;
//        }
//        String name = body.getVoiceRequests().get(0);
//
//        Intent intent = new Intent();
//        intent.setAction(VoiceReceiver.ACTION);
//        intent.putExtra("name", name);
//        GlobalInfo.getGlobalContext().sendBroadcast(intent);
    }

    private static void resolveResponseForRequest(MessageFetchResponse body) {
        if (body.getRequests() == null) {
            return;
        }
        LinkedList<String> requests = body.getRequests();
        LinkedList<UserInfo> address = GlobalInfo.dataCenter.phonebook.getValue();
        if (address == null) {
            return;
        }
        for (String name : requests) {
            for (UserInfo info : address) {
                if (info.getName().equals(name)) {
                    return;
                }
            }
            UserInfo userInfo = new UserInfo();
            userInfo.setName(name);
            userInfo.setType(UserInfo.CONTACT_TYPE.PENDING);
            address.addFirst(userInfo);
        }
        GlobalInfo.dataCenter.phonebook.postValue(address);
    }

    private static void resolveResponse(MessageFetchResponse response) {
        if (response.getNewGroup() == null) {
            return;
        }
        LinkedList<MessageGroupModel> messageGroupModels = response.getNewGroup();
        for (MessageGroupModel messageGroupModel : messageGroupModels) {
            for (MessageModel model : messageGroupModel.getMessageGroup()) {
                insertOthersMsg(messageGroupModel.getName(), model.getInfo());
            }
        }
    }

    private static void insertOthersMsg(String whom, String msg) {
        LinkedList<MessageGroupModel> models = GlobalInfo.dataCenter.messageGroupModels.getValue();
        LinkedList<MessageModel> list = null;
        assert models != null;
        for (MessageGroupModel model : models) {
            if (model.getName().equals(whom)) {
                list = model.getMessageGroup();
                list.addLast(new MessageModel(msg, "", 0));
            }
        }

        if (list == null) {
            MessageModel newMM = new MessageModel(msg, "", 0);
            MessageGroupModel newGM = new MessageGroupModel();
            newGM.setName(whom);
            LinkedList<MessageModel> newLMM = newGM.getMessageGroup();
            newGM.addData(newMM);
            list = newLMM;
            models.addFirst(newGM);
        }

        GlobalInfo.dataCenter.messageGroupModels.postValue(models);
        GlobalInfo.dataCenter.currentGroup.postValue(list);
    }
}
