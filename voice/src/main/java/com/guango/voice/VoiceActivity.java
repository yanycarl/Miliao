package com.guango.voice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.guango.base.store.GlobalInfo;

import java.util.Objects;

public class VoiceActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTitle;
    View mConnect;
    View mConnectOff;
    String mName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_voice);

        mTitle = findViewById(R.id.caller);
        mConnect = findViewById(R.id.connect);
        mConnectOff = findViewById(R.id.connect_off);
        mConnect.setOnClickListener(this);

        mName = getIntent().getStringExtra("name");
        mTitle.setText(mName);

        if(getIntent().getIntExtra("type",0) == 0){
            mConnect.setVisibility(View.GONE);
            requestPermissions();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.connect){

        }else if(v.getId() == R.id.connect_off){

        }
    }

    public void requestPermissions() {
        int permission = checkSelfPermission(Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {

            String[] PERMISSION_AUDIO = {
                    Manifest.permission.RECORD_AUDIO
            };
            requestPermissions(PERMISSION_AUDIO, 1);
        }
        else {
            VoiceConnectHelper.buildSocket(GlobalInfo.myName, mName);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("yanyao", "granted");
                VoiceConnectHelper.buildSocket(GlobalInfo.myName, mName);
            } else {
                Log.d("yanyao", "granted");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SocketSender.clean();
        VoiceHelper.stopVoice();
    }
}
