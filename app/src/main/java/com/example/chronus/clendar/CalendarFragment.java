package com.example.chronus.clendar;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.chronus.R;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;


public class CalendarFragment extends Fragment implements CalendarView.OnDateSelectedListener, CalendarView.OnYearChangeListener{

    private static boolean isMiUi = false;
    TextView mTextMonthDay;
    TextView mTextYear;
    TextView mTextLunar;
    TextView mTextCurrentDay;
    CalendarView mCalendarView;
    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,
            btn15,btn16,btn17,btn18,btn19,btn20,btn21,btn22,btn23,btn24;
    View.OnClickListener listener;
    View.OnFocusChangeListener listener1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_calendar,container,false);
        mTextMonthDay = view.findViewById(R.id.tv_month_day);
        mTextYear = view. findViewById(R.id.tv_year);
        mTextLunar =  view.findViewById(R.id.tv_lunar);
        mRelativeTool =  view.findViewById(R.id.rl_tool);
        mCalendarView = view. findViewById(R.id.calendarView);
        mTextCurrentDay = view.findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarView.showYearSelectLayout(mYear);
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        view.findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarLayout =view. findViewById(R.id.calendarLayout);
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));


       //获取按钮ID
        btn1=view.findViewById(R.id.btn_1);
        btn2=view.findViewById(R.id.btn_2);
        btn3=view.findViewById(R.id.btn_3);
        btn4=view.findViewById(R.id.btn_4);
        btn5=view.findViewById(R.id.btn_5);
        btn6=view.findViewById(R.id.btn_6);
        btn7=view.findViewById(R.id.btn_7);
        btn8=view.findViewById(R.id.btn_8);
        btn9=view.findViewById(R.id.btn_9);
        btn10=view.findViewById(R.id.btn_10);
        btn11=view.findViewById(R.id.btn_11);
        btn12=view.findViewById(R.id.btn_12);
        btn13=view.findViewById(R.id.btn_13);
        btn14=view.findViewById(R.id.btn_14);
        btn15=view.findViewById(R.id.btn_15);
        btn16=view.findViewById(R.id.btn_16);
        btn17=view.findViewById(R.id.btn_17);
        btn18=view.findViewById(R.id.btn_18);
        btn19=view.findViewById(R.id.btn_19);
        btn20=view.findViewById(R.id.btn_20);
        btn21=view.findViewById(R.id.btn_21);
        btn22=view.findViewById(R.id.btn_22);
        btn23=view.findViewById(R.id.btn_23);
        btn24=view.findViewById(R.id.btn_24);

        //给日历时间段按钮添加聚焦事件
        listener1=new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                   switch (v.getId()){
                       case R.id.btn_1:    btn1.setText("+新建日程");return;
                       case R.id.btn_2:    btn2.setText("+新建日程");return;
                       case R.id.btn_3:    btn3.setText("+新建日程");return;
                       case R.id.btn_4:    btn4.setText("+新建日程");return;
                       case R.id.btn_5:    btn5.setText("+新建日程");return;
                       case R.id.btn_6:    btn6.setText("+新建日程");return;
                       case R.id.btn_7:    btn7.setText("+新建日程");return;
                       case R.id.btn_8:    btn8.setText("+新建日程");return;
                       case R.id.btn_9:    btn9.setText("+新建日程");return;
                       case R.id.btn_10:    btn10.setText("+新建日程");return;
                       case R.id.btn_11:    btn11.setText("+新建日程");return;
                       case R.id.btn_12:    btn12.setText("+新建日程");return;
                       case R.id.btn_13:    btn13.setText("+新建日程");return;
                       case R.id.btn_14:    btn14.setText("+新建日程");return;
                       case R.id.btn_15:    btn15.setText("+新建日程");return;
                       case R.id.btn_16:    btn16.setText("+新建日程");return;
                       case R.id.btn_17:    btn17.setText("+新建日程");return;
                       case R.id.btn_18:    btn18.setText("+新建日程");return;
                       case R.id.btn_19:    btn19.setText("+新建日程");return;
                       case R.id.btn_20:    btn20.setText("+新建日程");return;
                       case R.id.btn_21:    btn21.setText("+新建日程");return;
                       case R.id.btn_22:    btn22.setText("+新建日程");return;
                       case R.id.btn_23:    btn23.setText("+新建日程");return;
                       case R.id.btn_24:    btn24.setText("+新建日程");return;
                   }
                }
                else {
                    switch (v.getId()){
                        case R.id.btn_1:    btn1.setText(null);return;
                        case R.id.btn_2:    btn2.setText(null);return;
                        case R.id.btn_3:    btn3.setText(null);return;
                        case R.id.btn_4:    btn4.setText(null);return;
                        case R.id.btn_5:    btn5.setText(null);return;
                        case R.id.btn_6:    btn6.setText(null);return;
                        case R.id.btn_7:    btn7.setText(null);return;
                        case R.id.btn_8:    btn8.setText(null);return;
                        case R.id.btn_9:    btn9.setText(null);return;
                        case R.id.btn_10:    btn10.setText(null);return;
                        case R.id.btn_11:    btn11.setText(null);return;
                        case R.id.btn_12:    btn12.setText(null);return;
                        case R.id.btn_13:    btn13.setText(null);return;
                        case R.id.btn_14:    btn14.setText(null);return;
                        case R.id.btn_15:    btn15.setText(null);return;
                        case R.id.btn_16:    btn16.setText(null);return;
                        case R.id.btn_17:    btn17.setText(null);return;
                        case R.id.btn_18:    btn18.setText(null);return;
                        case R.id.btn_19:    btn19.setText(null);return;
                        case R.id.btn_20:    btn20.setText(null);return;
                        case R.id.btn_21:    btn21.setText(null);return;
                        case R.id.btn_22:    btn22.setText(null);return;
                        case R.id.btn_23:    btn23.setText(null);return;
                        case R.id.btn_24:    btn24.setText(null);return;
                    }
                }
            }
        };
        btn1.setOnFocusChangeListener(listener1);
        btn2.setOnFocusChangeListener(listener1);
        btn3.setOnFocusChangeListener(listener1);
        btn4.setOnFocusChangeListener(listener1);
        btn5.setOnFocusChangeListener(listener1);
        btn6.setOnFocusChangeListener(listener1);
        btn7.setOnFocusChangeListener(listener1);
        btn8.setOnFocusChangeListener(listener1);
        btn9.setOnFocusChangeListener(listener1);
        btn10.setOnFocusChangeListener(listener1);
        btn11.setOnFocusChangeListener(listener1);
        btn12.setOnFocusChangeListener(listener1);
        btn13.setOnFocusChangeListener(listener1);
        btn14.setOnFocusChangeListener(listener1);
        btn15.setOnFocusChangeListener(listener1);
        btn16.setOnFocusChangeListener(listener1);
        btn17.setOnFocusChangeListener(listener1);
        btn18.setOnFocusChangeListener(listener1);
        btn19.setOnFocusChangeListener(listener1);
        btn20.setOnFocusChangeListener(listener1);
        btn21.setOnFocusChangeListener(listener1);
        btn22.setOnFocusChangeListener(listener1);
        btn23.setOnFocusChangeListener(listener1);
        btn24.setOnFocusChangeListener(listener1);


        //给日历时间段按钮添加点击事件
        //新建事项的接口
        listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setText("我被点击了");
            }
        };
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btn10.setOnClickListener(listener);
        btn11.setOnClickListener(listener);
        btn12.setOnClickListener(listener);
        btn13.setOnClickListener(listener);
        btn14.setOnClickListener(listener);
        btn15.setOnClickListener(listener);
        btn16.setOnClickListener(listener);
        btn17.setOnClickListener(listener);
        btn18.setOnClickListener(listener);
        btn19.setOnClickListener(listener);
        btn20.setOnClickListener(listener);
        btn21.setOnClickListener(listener);
        btn22.setOnClickListener(listener);
        btn23.setOnClickListener(listener);
        btn24.setOnClickListener(listener);

        initView();
        initData();
        return view;
    }




    @SuppressLint("SetTextI18n")
    protected void initView() {
//        setStatusBarDarkMode();

        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarView.showYearSelectLayout(mYear);
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });


        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(new Calendar.Scheme());
        calendar.addScheme(0xFF008800, "假");
        calendar.addScheme(0xFF008800, "节");
        return calendar;
    }



    protected void initData() {
        List<Calendar> schemes = new ArrayList<>();
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        schemes.add(getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
        schemes.add(getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        schemes.add(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));
        schemes.add(getSchemeCalendar(year, month, 13, 0xFFedc56d, "记"));
        schemes.add(getSchemeCalendar(year, month, 14, 0xFFedc56d, "记"));
        schemes.add(getSchemeCalendar(year, month, 15, 0xFFaacc44, "假"));
        schemes.add(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记"));
        schemes.add(getSchemeCalendar(year, month, 25, 0xFF13acf0, "假"));
        schemes.add(getSchemeCalendar(year, month, 27, 0xFF13acf0, "多"));
        mCalendarView.setSchemeDate(schemes);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }





}
