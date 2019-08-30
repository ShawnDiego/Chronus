package com.example.chronus;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ADD_DATA_Activity extends AppCompatActivity implements View.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener{

    private Integer year, month, day, hour, minute;
    //创建两个StringBuffer变量，用于拼接获取到的时间数据。
    private StringBuffer date, time;

    private Context context;
    private LinearLayout llDate, llTime;
    private TextView tvDate, tvTime;
    private Integer number;
    public static final String INTENT_ALARM_LOG = "intent_alarm_log";
    //在TextView上显示的字符

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //进入全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_toadd);
        Intent intent = getIntent();//获取Intent对象
        number = intent.getIntExtra("number",0);
        context = this;
        date = new StringBuffer();
        time = new StringBuffer();
        initView();
        initDateTime();
    }

    public void Store(View view){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date date = null;

        //找到所有控件
         EditText editText = (EditText ) findViewById(R.id.ADDContent);
          EditText editText1 = (EditText)findViewById(R.id.ADDID) ;
          EditText editText2 = (EditText) findViewById(R.id.AddTitle);
          Spinner  spinner = (Spinner) findViewById(R.id.ADDType);
          //EditText editText2 = (EditText)findViewById(R.id.ADDTime) ;


        //从控件获取输入信息
         String type=String.valueOf(spinner.getSelectedItem());
         String content = editText.getText().toString();
         if(content == null){
             content=" ";
         }
         //String id = editText1.getText().toString();
        Integer tem = number+1;
         String id = tem.toString();
         final String title = editText2.getText().toString();
         Integer Infactmonth = month+1;
         String Alerttime = year.toString()+"-"+(Infactmonth).toString()+"-"+day.toString()+"-"+hour.toString()+"-"+minute.toString();

        if(title != null){
            //将信息写入数据库
            MainActivity.INSERT(type,id,title,content,Alerttime);
            this.finish();
        }
        Calendar settingTime = Calendar.getInstance();
        settingTime.set(Calendar.HOUR_OF_DAY,hour);
        settingTime.set(Calendar.MINUTE,minute);
        settingTime.set(Calendar.SECOND,0);
        settingTime.set(Calendar.MONTH,month+1);
        settingTime.set(Calendar.MILLISECOND, 0);
        settingTime.set(Calendar.YEAR,year);

        System.out.println(TimeCounter.getSecondsOfSetTime(settingTime));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Notify nm = new Notify(getApplicationContext());
                nm.setNotification("提醒事项", title);
            }
        },TimeCounter.getSecondsOfSetTime(settingTime));
        AlarmManager am;
        Intent intent = new Intent(INTENT_ALARM_LOG);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, settingTime.getTimeInMillis(), pi);
    }
        //初始化控件，并为两个LinearLayout设置监听事件：
    private void initView() {
       llDate = (LinearLayout) findViewById(R.id.ll_date);
         tvDate = (TextView) findViewById(R.id.tv_date);
         llTime = (LinearLayout) findViewById(R.id.ll_time);
         tvTime = (TextView) findViewById(R.id.tv_time);
        llDate.setOnClickListener(this);
        llTime.setOnClickListener(this);
        EditText editText1 = (EditText)findViewById(R.id.ADDID) ;
        Integer tem = number+1;
        editText1.setText(tem.toString());
    }
    //使用Calendar类获取当前的日期时间。
    private void initDateTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_date:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (date.length() > 0) { //清除上次记录的日期
                            date.delete(0, date.length());
                        }
                        tvDate.setText(date.append(String.valueOf(year)).append("年").append(String.valueOf(month+1)).append("月").append(day).append("日"));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog dialog = builder.create();
                View dialogView = View.inflate(context, R.layout.dialog_date, null);
                final DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datePicker);

                dialog.setTitle("设置日期");
                dialog.setView(dialogView);
                dialog.show();
                //初始化日期监听事件
                datePicker.init(year, month-1 , day, this);
                break;
            case R.id.ll_time:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                builder2.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (time.length() > 0) { //清除上次记录的日期
                            time.delete(0, time.length());
                        }
                        tvTime.setText(time.append(String.valueOf(hour)).append("时").append(String.valueOf(minute)).append("分"));
                        dialog.dismiss();
                    }
                });
                builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog2 = builder2.create();
                View dialogView2 = View.inflate(context, R.layout.dialog_time, null);
                TimePicker timePicker = (TimePicker) dialogView2.findViewById(R.id.timePicker);
                timePicker.setCurrentHour(hour);
                timePicker.setCurrentMinute(minute);
                timePicker.setIs24HourView(true); //设置24小时制
                timePicker.setOnTimeChangedListener(this);
                dialog2.setTitle("设置时间");
                dialog2.setView(dialogView2);
                dialog2.show();
                break;
             case R.id.add_tv:
                 Store(v);
                 break;
        }
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minute = minute;
    }


}