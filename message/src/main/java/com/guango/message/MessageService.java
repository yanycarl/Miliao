package com.guango.message;

import com.guango.message.Presenter.MessagePresenter;

public class MessageService {

    public static void openMessageByName(String name){
        MessagePresenter.openMessageByName(name);
    }
}
