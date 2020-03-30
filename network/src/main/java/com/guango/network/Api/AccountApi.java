package com.guango.network.Api;

import com.guango.base.model.RegisterResponse;
import com.guango.base.model.RequestFriendResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AccountApi {

    @GET("/account/create")
    Call<RegisterResponse> createAccount(@Query("user") String userName, @Query("pass") String password);

    @GET("/account/login")
    Call<RequestFriendResponse> loginAccount(@Query("user") String userName, @Query("pass") String password);

}
