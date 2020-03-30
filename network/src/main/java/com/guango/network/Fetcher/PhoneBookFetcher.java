package com.guango.network.Fetcher;

import android.util.Log;
import android.widget.Toast;

import com.guango.base.Interface.PhoneBookCallback;
import com.guango.base.model.RequestFriendResponse;
import com.guango.base.model.SearchFriendResponse;
import com.guango.base.store.GlobalInfo;
import com.guango.network.Api.PhonebookApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneBookFetcher {

    public static void searchFriend(String name, String requestCode, final PhoneBookCallback callback) {
        RetrofitHelper.getRetrofit().create(PhonebookApi.class)
                .searchFriend(name, requestCode)
                .enqueue(new Callback<SearchFriendResponse>() {
                    @Override
                    public void onResponse(Call<SearchFriendResponse> call, Response<SearchFriendResponse> response) {
                        if (response.code() != 200) {
                            Log.d("yanyao -- friend search", response.message());
                            Toast.makeText(GlobalInfo.getContext(), "没有查到此人", Toast.LENGTH_SHORT).show();
                        } else {
                            callback.onGotSearchResult(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchFriendResponse> call, Throwable t) {
                        Log.d("yanyao -- friend search", t.toString());
                    }
                });
    }

    public static void requestFriend(String toWhom) {
        RetrofitHelper.getRetrofit().create(com.guango.network.Api.PhonebookApi.class)
                .requestFriend(GlobalInfo.myName, toWhom)
                .enqueue(new Callback<RequestFriendResponse>() {
                    @Override
                    public void onResponse(Call<RequestFriendResponse> call, Response<RequestFriendResponse> response) {
                        if (response.code() != 200) {
                            Log.d("yanyao -- friend request", response.message());
                            Toast.makeText(GlobalInfo.getContext(), "申请失败", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GlobalInfo.getContext(), "申请成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestFriendResponse> call, Throwable t) {
                        Log.d("yanyao -- friend request", t.toString());
                    }
                });
    }

    public static void replyFriendRequest(String name, final boolean status, final PhoneBookCallback callback) {
        RetrofitHelper.getRetrofit().create(com.guango.network.Api.PhonebookApi.class)
                .replyRequest(GlobalInfo.myName, name, status)
                .enqueue(new Callback<RequestFriendResponse>() {
                    @Override
                    public void onResponse(Call<RequestFriendResponse> call, Response<RequestFriendResponse> response) {
                        if (response.code() != 200) {
                            Toast.makeText(GlobalInfo.getContext(), "回复失败", Toast.LENGTH_SHORT).show();
                        } else {
                            if(status) {
                                callback.onGotReplyResult(true);
                            }else {
                                callback.onGotReplyResult(true);
                            }
                            Toast.makeText(GlobalInfo.getContext(), "回复成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestFriendResponse> call, Throwable t) {
                        Toast.makeText(GlobalInfo.getContext(), "回复失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
