package com.guango.voice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.guango.base.store.GlobalInfo;


public class VoiceReceiver extends BroadcastReceiver {

    public static String ACTION = "com.guango.broadcast.voice";

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("name");

        Toast.makeText(GlobalInfo.getGlobalContext(), name+"来电", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(GlobalInfo.getGlobalContext(), VoiceActivity.class);
        intent2.putExtra("name", name);
        intent2.putExtra("type", 1);
        GlobalInfo.getContext().startActivity(intent2);
    }
}
