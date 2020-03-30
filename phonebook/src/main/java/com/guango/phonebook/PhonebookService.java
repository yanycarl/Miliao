package com.guango.phonebook;

import android.content.Intent;

import com.guango.base.Interface.IService;
import com.guango.base.store.GlobalInfo;

public class PhonebookService implements IService {

    public static void enterAddFriendpage(){
        Intent intent = new Intent(GlobalInfo.getGlobalContext(), NewFriendActivity.class);
        GlobalInfo.getGlobalContext().startActivity(intent);
    }
}
