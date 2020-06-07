package com.guango.base.exception;

import android.widget.TextView;

import com.guango.base.R;
import com.guango.base.activity.BaseActivity;

public class ExceptionActivity extends BaseActivity {

    public static final String STACK_INFO = "stack_info";

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_exception);

        if(getIntent() != null) {
            String info = getIntent().getStringExtra(STACK_INFO);
            TextView textView = ExceptionActivity.this.findViewById(R.id.exception_info);
            textView.setText(info);
        }
    }
}
