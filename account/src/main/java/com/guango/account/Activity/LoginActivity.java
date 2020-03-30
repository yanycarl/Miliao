package com.guango.account.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.guango.account.R;
import com.guango.base.activity.BaseActivity;

import java.util.Objects;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mClose;
    private TextView mTitle;
    private EditText mLoginUser;
    private EditText mLoginPass;
    private EditText mRegUser;
    private EditText mRegPass;
    private Button mLogin;
    private Button mReg;
    private View mFunction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mClose = findViewById(R.id.close);
        mClose.setVisibility(View.INVISIBLE);

        mFunction = findViewById(R.id.function);
        mFunction.setVisibility(View.GONE);

        mTitle = findViewById(R.id.title);

        View login_part = findViewById(R.id.login_part);
        View reg_part = findViewById(R.id.reg_part);
        mLoginUser = login_part.findViewById(R.id.login_input);
        mLoginPass = login_part.findViewById(R.id.login_input_pass);
        mRegUser = reg_part.findViewById(R.id.login_input);
        mRegPass = reg_part.findViewById(R.id.login_input_pass);
        mLogin = login_part.findViewById(R.id.commit);
        mReg = reg_part.findViewById(R.id.commit);
    }

    @Override
    protected void initViews() {
        mTitle.setText("登入您的账户");
        mReg.setText("立    即    注    册");
        mLogin.setText("现    在    登    入");
        mReg.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mLogin){
            handleLogin(mLoginUser.getText().toString(), mLoginPass.getText().toString());
        }else if(v == mReg){
            handleReg(mRegUser.getText().toString(), mRegPass.getText().toString());
        }
    }

    private void handleLogin(String user, String pass){
        LoginPresenter.login(user, pass);
    }

    private void handleReg(String user, String pass){
        LoginPresenter.registerAccount(user, pass);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认退出嘛");
        builder.setPositiveButton("是的", backDialogListener);
        builder.setNegativeButton("不要", backDialogListener);
        builder.show();
    }

    private DialogInterface.OnClickListener backDialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == -1) {
                closeAllActivity();
            } else {
                dialog.dismiss();
            }
        }
    };
}
