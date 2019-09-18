package com.example.chronus.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chronus.MainActivity;
import com.example.chronus.R;
import com.example.chronus.Server.Syn_From_Server;
import com.example.chronus.Server.Syn_To_Server;

public class SettingFragment extends Fragment{
    private static final String ARG_SHOW_TEXT = "text";
    private  String mContentText;
    private View view;
    private RelativeLayout rl_login;
    private RelativeLayout rl_push;
    private RelativeLayout rl_pull;
    private RelativeLayout rl_clean;
    private ImageView avatar_img;
    private TextView user_name;
    public static boolean isLogin = false;

    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance(String param1){
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.setting_layout, container, false);
        initView();
        init();
        setListener();
        return view;
    }

    private void init(){

    }

    private void initView(){
        //初始化控件
        rl_login = view.findViewById(R.id.rl_login);
        rl_pull = view.findViewById(R.id.rl_pull);
        rl_push = view.findViewById(R.id.rl_push);
        rl_clean = view.findViewById(R.id.rl_clean);
        avatar_img = view.findViewById(R.id.user_avatar);
        user_name = view.findViewById(R.id.user_name);

    }

    private void setListener(){
        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test:","login_success");
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        rl_pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //将数据从服务器端同步到本地
                new Syn_From_Server(MainActivity.mDBHelper.getDBPath()).start();
            }
        });
        rl_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将数据从本地同步到服务器
                Syn_To_Server syn_to_server = new Syn_To_Server( MainActivity.mDBHelper.getDBPath());
                syn_to_server.start();
            }
        });
        rl_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("test:",Boolean.toString(getisLogin()));
            }
        });
    }
    public static void setLoginTrue(){
        isLogin = true;
    }
    public static void setLoginFalse(){
        isLogin = false;
    }
    public static boolean getisLogin(){
        return isLogin;
    }
}
