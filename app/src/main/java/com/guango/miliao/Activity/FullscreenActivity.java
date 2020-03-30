package com.guango.miliao.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.guango.account.Activity.task.LoginTask;
import com.guango.base.activity.BaseActivity;
import com.guango.base.store.GlobalInfo;
import com.guango.base.utilities.ThreadHelper;
import com.guango.miliao.R;

public class FullscreenActivity extends BaseActivity {

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_fullscreen);
        addTextAndFont();
    }

    private void addTextAndFont(){
        AssetManager assetManager = getAssets();
        TextView textView = findViewById(R.id.textView);
        Typeface typeface = Typeface.createFromAsset(assetManager, "alibaba_lite.ttf");
        textView.setTypeface(typeface);
        textView.setText("消息，要迅速。更要保密。");
    }

    @Override
    protected void initData() {

        GlobalInfo.setContext(this);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                if (GlobalInfo.myName == null) {
                    ThreadHelper.runTask(new LoginTask());
                    return;
                }

                FullscreenActivity.this.finish();
                Intent intent = new Intent(FullscreenActivity.this
                        , MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}
