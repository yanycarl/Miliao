package com.guango.voice;

import com.guango.base.model.VoiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VoiceApi {

    @GET("/voice/connect")
    Call<VoiceResponse> call(@Query("from") String name, @Query("to") String name2);
}
