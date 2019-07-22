package com.example.chronus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOK = (Button) findViewById(R.id.button);
        btnOK.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText("我爱萱萱");
    }
}
