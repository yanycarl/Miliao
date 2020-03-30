package com.guango.base.store;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.guango.base.model.MessageGroupModel;
import com.guango.base.model.MessageModel;
import com.guango.base.model.UserInfo;

import java.util.LinkedList;

public class DataCenter extends ViewModel {
    public MutableLiveData<LinkedList<MessageGroupModel>> messageGroupModels = new MutableLiveData<>();

    public MutableLiveData<LinkedList<MessageModel>> currentGroup = new MutableLiveData<>();

    public MutableLiveData<String> voiceAddress = new MutableLiveData<>();

    public MutableLiveData<LinkedList<UserInfo>> phonebook = new MutableLiveData<>();
}
