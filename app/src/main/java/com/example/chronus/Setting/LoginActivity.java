package com.example.chronus.Setting;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.chronus.R;

public class LoginActivity extends AppCompatActivity   {
    TextInputEditText user_name;
    TextInputEditText password;
    TextInputLayout username_layout;
    TextInputLayout password_layout;
    Button sure_but;
    Button cancel_but;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //进入全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);


        //加载视图
        setContentView(R.layout.login_activity);

        user_name = findViewById(R.id.username_et);
        username_layout = findViewById(R.id.uesrname_layout);
        password = findViewById(R.id.password_et);
        password_layout = findViewById(R.id.password_layout);
        sure_but = findViewById(R.id.sure_button);
        cancel_but = findViewById(R.id.cancel_button);

        setListener();
    }


    private void setListener(){
        sure_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(password.getText())) {
                    password_layout.setError("密码长度不正确");
                    //从这里添加验证用户名和密码是否对应
                    //      1、用户名不存在
                    //            对话框提示：当前用户不存在，是否用当前输入用户名和密码创建账户？---然后数据库处理
                    //      2、用户名和密码不对应
                    Log.d("判断密码：","判断密码已经不正确");
                } else {
                    password_layout.setError(null); // Clear the error
                    Log.d("判断密码：","判断密码正确");
                }
            }
        });
        // Clear the error once more than 8 characters are typed.
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(password.getText())) {

                    password_layout.setError(null); //Clear the error
                }
                return false;
            }
        });
        cancel_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginSuccess();
                finish();
            }
        });
    }
    private boolean isPasswordValid(@Nullable Editable text) {
        //判断密码正确

        return text != null && text.length() >= 8;//这里是判断密码长度的

    }
    private void LoginSuccess(){
        //登陆成功之后的处理
        SettingFragment.setLoginTrue();
    }
}
