package com.example.chronus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Update_DATA_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //进入全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_toedit);

        TextView textView = (TextView) findViewById(R.id.EDITID);

        //显示ID值
        textView.setText(MainActivity.Edit_ID);

    }

    public  void EDIT( View view)
    {


        //找到各个控件

        EditText editText1 = (EditText)findViewById(R.id.EDITContent);

        Spinner spinner = (Spinner)findViewById(R.id.EDITType);

        //从各个控件获取修改后的数值

        String content =  editText1.getText().toString();
        String type = spinner.getSelectedItem().toString();


        MainActivity.update(type , MainActivity.Edit_ID,content);


        this.finish();
    }

}
