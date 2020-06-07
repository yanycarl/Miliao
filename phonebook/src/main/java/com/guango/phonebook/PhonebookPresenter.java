package com.guango.phonebook;

import android.util.Log;
import android.widget.Toast;

import com.guango.base.Interface.PhoneBookCallback;
import com.guango.base.model.RequestFriendResponse;
import com.guango.base.model.SearchFriendResponse;
import com.guango.base.model.UserInfo;
import com.guango.base.store.GlobalInfo;
import com.guango.network.Fetcher.PhoneBookFetcher;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhonebookPresenter {

    private IView mView;

    interface IView {
        void onReceivedResult(SearchFriendResponse response);
    }

    PhonebookPresenter(IView view) {
        mView = view;
    }

    void searchFriend(final String name, String request_code) {
        PhoneBookFetcher.searchFriend(name, request_code, new PhoneBookCallback() {

            @Override
            public void onGotSearchResult(SearchFriendResponse response) {
                mView.onReceivedResult(response);
            }
        });
    }

    void confirmRequest(String toWhom) {
        if(toWhom.equals(GlobalInfo.myName)){
            Toast.makeText(GlobalInfo.getGlobalContext(), "无法添加自己", Toast.LENGTH_SHORT).show();
            return;
        }
        PhoneBookFetcher.requestFriend(toWhom);
    }

    void replyRequest(final String name, final boolean status) {

        PhoneBookFetcher.replyFriendRequest(name, status, new PhoneBookCallback() {
            @Override
            public void onGotReplyResult(boolean success) {
                if(success) {
                    if(status) {
                        LinkedList<UserInfo> linkedList = GlobalInfo.dataCenter.phonebook.getValue();
                        for (UserInfo next : Objects.requireNonNull(linkedList)) {
                            if (next.getName().equals(name)) {
                                next.setType(UserInfo.CONTACT_TYPE.DONE);
                            }
                        }
                        GlobalInfo.dataCenter.phonebook.setValue(linkedList);
                    }else {
                        LinkedList<UserInfo> linkedList = GlobalInfo.dataCenter.phonebook.getValue();
                        Iterator<UserInfo> iterator = Objects.requireNonNull(linkedList).iterator();
                        while (iterator.hasNext()) {
                            UserInfo next = iterator.next();
                            if (next.getName().equals(name)) {
                                iterator.remove();
                            }
                        }
                        GlobalInfo.dataCenter.phonebook.setValue(linkedList);
                    }
                }
            }
        });
    }
}
