package com.guango.network.Fetcher;

import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.guango.base.Interface.MessageFetchCallback;
import com.guango.base.model.MessageFetchResponse;
import com.guango.base.model.MessageSendResponse;
import com.guango.base.store.GlobalInfo;
import com.guango.network.Api.MessageApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageFetcher {

    public static void fetchMessage(final MessageFetchCallback callback){
        MessageApi messageApi = RetrofitHelper.getRetrofit().create(MessageApi.class);
        int version = 0;
        try {
            version = GlobalInfo.getGlobalContext().getPackageManager().getPackageInfo(GlobalInfo.getGlobalContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Call<MessageFetchResponse> call = messageApi.fetchInfo(GlobalInfo.myName, version);

        call.enqueue(new Callback<MessageFetchResponse>() {
            @Override
            public void onResponse(Call<MessageFetchResponse> call, Response<MessageFetchResponse> response) {
                Log.d("yanyao -- fetchAPi", response.toString());
                if (response.code() == 200) {
                    callback.onMessageGot(response.body());
                }
            }

            @Override
            public void onFailure(Call<MessageFetchResponse> call, Throwable t) {
                Log.d("yanyao -- fetchAPi", t.toString());
            }
        });
    }

    public static void sendMsg(final String toWhom, final String msg, final MessageFetchCallback callback) {
        RetrofitHelper.getRetrofit().create(MessageApi.class).sendMsg(GlobalInfo.myName, toWhom, msg).enqueue(new Callback<MessageSendResponse>() {
            @Override
            public void onResponse(Call<MessageSendResponse> call, Response<MessageSendResponse> response) {
                Log.d("yanyao -- sendAPi", response.toString());
                if (response.code() != 200) {
                    Toast.makeText(GlobalInfo.getGlobalContext(), "消息发送失败", Toast.LENGTH_SHORT).show();
                } else {
                    callback.onMessageSent(toWhom, msg);
                }
            }

            @Override
            public void onFailure(Call<MessageSendResponse> call, Throwable t) {
                Log.d("yanyao -- sendAPi", t.toString());
                Toast.makeText(GlobalInfo.getGlobalContext(), "消息发送失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
