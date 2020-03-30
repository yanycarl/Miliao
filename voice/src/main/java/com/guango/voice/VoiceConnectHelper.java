package com.guango.voice;

import android.util.Log;

import androidx.annotation.Nullable;

import com.guango.base.model.VoiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VoiceConnectHelper {

    private static Retrofit retrofit;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://16p23b6752.51mypc.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Nullable
    public static void buildSocket(String from, String to) {

        SocketSender.buildClient(to);

        getRetrofit().create(VoiceApi.class)
                .call(from, to)
                .enqueue(new Callback<VoiceResponse>() {
                    @Override
                    public void onResponse(Call<VoiceResponse> call, Response<VoiceResponse> response) {
                        if (response.code() == 200) {
                            Log.d("yanyao -- voice", "succees!");
                            VoiceHelper.getVoice();
                        } else {
                            Log.d("yanyao -- voice", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<VoiceResponse> call, Throwable t) {
                        Log.d("yanyao -- voice", t.toString());
                    }
                });
    }
}
