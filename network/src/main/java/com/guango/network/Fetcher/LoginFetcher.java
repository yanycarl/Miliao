package com.guango.network.Fetcher;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.guango.base.model.MessageGroupModel;
import com.guango.base.model.MessageModel;
import com.guango.base.model.RegisterResponse;
import com.guango.base.model.RequestFriendResponse;
import com.guango.base.store.GlobalInfo;
import com.guango.base.utilities.SharedPreferenceHelper;
import com.guango.network.Api.AccountApi;
import com.guango.router.RouterMap;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFetcher {

    public static void registerAccount(final String u, String p) {
        RetrofitHelper.getRetrofit().create(AccountApi.class)
                .createAccount(u, p)
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.code() != 200) {
                            Log.d("yanyao -- create", response.message());
                            return;
                        }
                        String id = response.body().getUserId();

                        if (id.equals("-1")) {
                            Toast.makeText(GlobalInfo.getGlobalContext(), "用户名已存在", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        SharedPreferenceHelper.DataModel model = new SharedPreferenceHelper.DataModel();
                        model.setmUserId(id);
                        model.setUserName(u);
                        model.setMessageHistory(GlobalInfo.dataCenter.messageGroupModels.getValue());
                        SharedPreferenceHelper.saveData(model);

                        GlobalInfo.myName = u;
                        RouterMap.openRoute("main", null);
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Log.d("yanyao -- create", t.toString());
                    }
                });
    }

    public static void login(final String user, String pass) {
        RetrofitHelper.getRetrofit().create(AccountApi.class)
                .loginAccount(user, pass)
                .enqueue(new Callback<RequestFriendResponse>() {
                    @Override
                    public void onResponse(Call<RequestFriendResponse> call, Response<RequestFriendResponse> response) {
                        if (response.code() != 200 || response.body().getCode() != 1) {
                            Toast.makeText(GlobalInfo.getGlobalContext(), "登录失败，请稍后再试", Toast.LENGTH_SHORT).show();
                        } else {
                            SharedPreferenceHelper.DataModel model = new SharedPreferenceHelper.DataModel();
                            model.setUserName(user);
                            model.setmUserId("0");
                            SharedPreferenceHelper.saveData(model);

                            LinkedList<MessageGroupModel> list = new LinkedList<>();
                            MessageModel model2 = new MessageModel("我们已经成为好友。", "", 0);
                            MessageGroupModel groupModel = new MessageGroupModel();
                            groupModel.setName("客服小姐姐");
                            groupModel.addData(model2);
                            list.addFirst(groupModel);
                            GlobalInfo.dataCenter.messageGroupModels.postValue(list);

                            GlobalInfo.myName = user;
                            RouterMap.openRoute("main", null);
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestFriendResponse> call, Throwable t) {
                        Toast.makeText(GlobalInfo.getGlobalContext(), "登录失败，请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
