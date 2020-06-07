package com.guango.network.Api;

import com.guango.base.model.MessageFetchResponse;
import com.guango.base.model.MessageSendResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MessageApi {

    @GET("/message/send")
    Call<MessageSendResponse> sendMsg(@Query("me") String myName,
                                      @Query("to") String name,
                                      @Query("msg") String msg);

    @GET("/message/fetch")
    Call<MessageFetchResponse> fetchInfo(@Query("me") String myName,
                                         @Query("version") int versionCode);
}
