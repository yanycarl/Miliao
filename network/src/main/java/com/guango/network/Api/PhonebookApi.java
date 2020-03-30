package com.guango.network.Api;

import com.guango.base.model.RequestFriendResponse;
import com.guango.base.model.SearchFriendResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhonebookApi {

    @GET("/friend/search")
    Call<SearchFriendResponse> searchFriend(@Query("name") String name
            , @Query("request_code") String code);

    @GET("/friend/request")
    Call<RequestFriendResponse> requestFriend(@Query("from") String name
            , @Query("to_whom") String toWhom);

    @GET("/friend/reply")
    Call<RequestFriendResponse> replyRequest(@Query("who") String who,
                                             @Query("whom") String whom,
                                             @Query("status") boolean toWhom);
}
