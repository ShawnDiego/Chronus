package com.example.chronus.clendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chronus.MainActivity;
import com.example.chronus.R;
import com.example.chronus.ViewFragment;
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
    static Activity activity;
    private static int mYear,mMonth,mDay;
    static  List<Calendar> schemes= new ArrayList<>();;
    //删除日程的起止范围
    private static int first,last;
    private int mHour;
    //声明Add_General_Activity回调的数据
    private static String title,update;
    //建立日程的起止范围
    public static int start,end,item;
    CalendarLayout mCalendarLayout;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,
            btn15,btn16,btn17,btn18,btn19,btn20,btn21,btn22,btn23,btn24;
    View line1,line2,line3,line4,line5,line6,line7,line8,line9,line10,line11,line12,line13,line14,line15,line16,
         line17,line18,line19,line20,line21,line22,line23;
    View.OnClickListener listener;
    View.OnFocusChangeListener listener1;
    private static final String ARG_SHOW_TEXT = "text";

    public static CalendarFragment newInstance(String param1){
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }
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
                       case R.id.btn_1:    btn1.setText("+新建日程");mHour=0;return;
                       case R.id.btn_2:    btn2.setText("+新建日程");mHour=1;return;
                       case R.id.btn_3:    btn3.setText("+新建日程");mHour=2;return;
                       case R.id.btn_4:    btn4.setText("+新建日程");mHour=3;return;
                       case R.id.btn_5:    btn5.setText("+新建日程");mHour=4;return;
                       case R.id.btn_6:    btn6.setText("+新建日程");mHour=5;return;
                       case R.id.btn_7:    btn7.setText("+新建日程");mHour=6;return;
                       case R.id.btn_8:    btn8.setText("+新建日程");mHour=7;return;
                       case R.id.btn_9:    btn9.setText("+新建日程");mHour=8;return;
                       case R.id.btn_10:    btn10.setText("+新建日程");mHour=9;return;
                       case R.id.btn_11:    btn11.setText("+新建日程");mHour=10;return;
                       case R.id.btn_12:    btn12.setText("+新建日程");mHour=11;return;
                       case R.id.btn_13:    btn13.setText("+新建日程");mHour=12;return;
                       case R.id.btn_14:    btn14.setText("+新建日程");mHour=13;return;
                       case R.id.btn_15:    btn15.setText("+新建日程");mHour=14;return;
                       case R.id.btn_16:    btn16.setText("+新建日程");mHour=15;return;
                       case R.id.btn_17:    btn17.setText("+新建日程");mHour=16;return;
                       case R.id.btn_18:    btn18.setText("+新建日程");mHour=17;return;
                       case R.id.btn_19:    btn19.setText("+新建日程");mHour=18;return;
                       case R.id.btn_20:    btn20.setText("+新建日程");mHour=19;return;
                       case R.id.btn_21:    btn21.setText("+新建日程");mHour=20;return;
                       case R.id.btn_22:    btn22.setText("+新建日程");mHour=21;return;
                       case R.id.btn_23:    btn23.setText("+新建日程");mHour=22;return;
                       case R.id.btn_24:    btn24.setText("+新建日程");mHour=23;return;
                   }
                }
                else {
                    switch (v.getId()){
                        case R.id.btn_1:    if(isAdd( btn1 )) btn1.setText(null);return;
                        case R.id.btn_2:    if(isAdd( btn2)) btn2.setText(null);return;
                        case R.id.btn_3:    if(isAdd( btn3 )) btn3.setText(null);return;
                        case R.id.btn_4:    if(isAdd( btn4 )) btn4.setText(null);return;
                        case R.id.btn_5:    if(isAdd( btn5 )) btn5.setText(null);return;
                        case R.id.btn_6:    if(isAdd( btn6 )) btn6.setText(null);return;
                        case R.id.btn_7:    if(isAdd( btn7 )) btn7.setText(null);return;
                        case R.id.btn_8:    if(isAdd( btn8 )) btn8.setText(null);return;
                        case R.id.btn_9:    if(isAdd( btn9 )) btn9.setText(null);return;
                        case R.id.btn_10:   if(isAdd( btn10 )) btn10.setText(null);return;
                        case R.id.btn_11:   if(isAdd( btn11 )) btn11.setText(null);return;
                        case R.id.btn_12:   if(isAdd( btn12 ))  btn12.setText(null);return;
                        case R.id.btn_13:   if(isAdd( btn13 )) btn13.setText(null);return;
                        case R.id.btn_14:   if(isAdd( btn14 ))  btn14.setText(null);return;
                        case R.id.btn_15:   if(isAdd( btn15 )) btn15.setText(null);return;
                        case R.id.btn_16:   if(isAdd( btn16 )) btn16.setText(null);return;
                        case R.id.btn_17:   if(isAdd( btn17 )) btn17.setText(null);return;
                        case R.id.btn_18:    if(isAdd( btn18 )) btn18.setText(null);return;
                        case R.id.btn_19:    if(isAdd( btn19 )) btn19.setText(null);return;
                        case R.id.btn_20:    if(isAdd( btn20 )) btn20.setText(null);return;
                        case R.id.btn_21:    if(isAdd( btn21 )) btn21.setText(null);return;
                        case R.id.btn_22:   if(isAdd( btn22 ))  btn22.setText(null);return;
                        case R.id.btn_23:    if(isAdd( btn23 )) btn23.setText(null);return;
                        case R.id.btn_24:    if(isAdd( btn24 )) btn24.setText(null);return;
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
                //获取触发事件的activity
                activity=getActivity();
                //弹出新建事项activity
                Button btn=(Button) v;
                if(isAdd( btn )){
                Intent intent=new Intent(activity,Add_General_Activity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("year",mYear);
                bundle.putInt("month",mMonth);
                bundle.putInt("day",mDay);
                bundle.putInt("hour",mHour);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
                }
                else{
                    first=rangFirst( btn );
                    last=rangeLast( btn );
                    Intent intent=new Intent(activity,Delete_General_Activity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("year1",mYear);
                    bundle.putInt("month1",mMonth);
                    bundle.putInt("day1",mDay);
                    bundle.putInt("begin",first);
                    bundle.putInt("end",last);
                    bundle.putString( "id",btn.getText().toString() );
                    intent.putExtras(bundle);
                    startActivityForResult(intent,1);
                }
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

        //获取下划线ID
        line1=view.findViewById( R.id.ul_1 );
        line2=view.findViewById( R.id.ul_2 );
        line3=view.findViewById( R.id.ul_3);
        line4=view.findViewById( R.id.ul_4 );
        line5=view.findViewById( R.id.ul_5 );
        line6=view.findViewById( R.id.ul_6 );
        line7=view.findViewById( R.id.ul_7 );
        line8=view.findViewById( R.id.ul_8);
        line9=view.findViewById( R.id.ul_9 );
        line10=view.findViewById( R.id.ul_10 );
        line11=view.findViewById( R.id.ul_11);
        line12=view.findViewById( R.id.ul_12 );
        line13=view.findViewById( R.id.ul_13 );
        line14=view.findViewById( R.id.ul_14 );
        line15=view.findViewById( R.id.ul_15 );
        line16=view.findViewById( R.id.ul_16 );
        line17=view.findViewById( R.id.ul_17 );
        line18=view.findViewById( R.id.ul_18);
        line19=view.findViewById( R.id.ul_19 );
        line20=view.findViewById( R.id.ul_20 );
        line21=view.findViewById( R.id.ul_21 );
        line22=view.findViewById( R.id.ul_22 );
        line23=view.findViewById( R.id.ul_23 );


        initView();
        initData();
        return view;
    }




    @SuppressLint("SetTextI18n")
    protected void initView() {

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

        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        schemes.add(getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
        schemes.add(getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        schemes.add(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));
        schemes.add(getSchemeCalendar(year, month, 13, 0xFFedc56d, "记"));
        schemes.add(getSchemeCalendar(year, month, 14, 0xFFedc56d, "阅"));
        schemes.add(getSchemeCalendar(year, month, 15, 0xFFaacc44, "影"));
        schemes.add(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记"));
        schemes.add(getSchemeCalendar(year, month, 25, 0xFF13acf0, "假"));

        mCalendarView.setSchemeDate(schemes);
    }



    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Add_General_activity回调
        if(requestCode==0){
          if(resultCode==0){
            //初始化时间表
            Toast.makeText( activity,"关闭",Toast.LENGTH_SHORT).show();
           }
           //新建事项
           if(resultCode==-1){
             new Bundle();
             Bundle bundle1=data.getExtras();
             title=bundle1.getString("title");
             item=bundle1.getInt("scheme");
             start=bundle1.getInt("begin");
             end=bundle1.getInt("end");

              //将建立好的事项在btn上显示
              Log.w(ARG_SHOW_TEXT,"警告信息");
              getBtn(start).setText( title );
              for(int i=start;i<end;i++){
                  getBtn( i ).setBackgroundResource( R.drawable.bg_general);
                  getBtn( i ).setEnabled( false );
               }
               getBtn( start ).setEnabled( true );
               getBtn( start ).setFocusable( false );
               setUnderLine();
               addMark( item );
               Toast.makeText( activity,"建立成功",Toast.LENGTH_SHORT).show();
        }}

       if(requestCode==1){
           //删除日程
           if(resultCode==1){
               getBtn( first ).setText( null );
                getBtn( first ).setFocusableInTouchMode( true );
               getBtn( first ).setFocusable( true );
               for(int i=first;i<last;i++){
                   getBtn( i ).setEnabled( true );
                   getBtn( i ).setBackgroundResource( R.drawable.bg_btn );
               }
               deleteUnderLine();
               deleteMark();
           }
            //更新日程
           if(resultCode==-1){
               new Bundle();
               Bundle bundle2=data.getExtras();
               update=bundle2.getString( "isUpdate" );
               getBtn( first ).setText( update );

           }
           //仅返回
           if(resultCode==0){

           }
       }
    }
    //获取btn的引用
    private Button getBtn(int a) {
        switch (a) {
            case 0: return btn1;
            case 1: return btn2;
            case 2: return btn3;
            case 3: return btn4;
            case 4: return btn5;
            case 5: return btn6;
            case 6: return btn7;
            case 7: return btn8;
            case 8: return btn9;
            case 9: return btn10;
            case 10: return btn11;
            case 11: return btn12;
            case 12: return btn13;
            case 13: return btn14;
            case 14: return btn15;
            case 15: return btn16;
            case 16: return btn17;
            case 17: return btn18;
            case 18: return btn19;
            case 19: return btn20;
            case 20: return btn21;
            case 21: return btn22;
            case 22: return btn23;
            case 23: return btn24;
        }
        return null;
    }
      //获取View（下划线）的引用
      private View getView(int a) {
          switch (a) {
              case 0: return line1;
              case 1: return line2;
              case 2: return line3;
              case 3: return line4;
              case 4: return line5;
              case 5: return line6;
              case 6: return line7;
              case 7: return line8;
              case 8: return line9;
              case 9: return line10;
              case 10: return line11;
              case 11: return line12;
              case 12: return line13;
              case 13: return line14;
              case 14: return line15;
              case 15: return line16;
              case 16: return line17;
              case 17: return line18;
              case 18: return line19;
              case 19: return line20;
              case 20: return line21;
              case 21: return line22;
              case 22: return line23;
          }
          return null;
      }
    public boolean isAdd(Button btn){
        if(btn.getText().toString().equals( "+新建日程" )) return  true;
        else  return false;
    }

    public int rangFirst(Button btn){
        switch (btn.getId()){
            case R.id.btn_1:    return 0;
            case R.id.btn_2:   return 1;
            case R.id.btn_3:    return 2;
            case R.id.btn_4:    return 3;
            case R.id.btn_5:    return 4;
            case R.id.btn_6:    return 5;
            case R.id.btn_7:    return 6;
            case R.id.btn_8:    return 7;
            case R.id.btn_9:    return 8;
            case R.id.btn_10:   return 9;
            case R.id.btn_11:   return 10;
            case R.id.btn_12:   return 11;
            case R.id.btn_13:   return 12;
            case R.id.btn_14:   return 13;
            case R.id.btn_15:   return 14;
            case R.id.btn_16:   return 15;
            case R.id.btn_17:   return 16;
            case R.id.btn_18:    return 17;
            case R.id.btn_19:    return 18;
            case R.id.btn_20:    return 19;
            case R.id.btn_21:    return 20;
            case R.id.btn_22:   return 21;
            case R.id.btn_23:    return 22;
            case R.id.btn_24:    return 23;
        }
        return 0;
    }
    public int rangeLast(Button btn){
        int first=rangFirst( btn );
        int last=first+1;
        while(!getBtn( last ).isEnabled()){
            last++;
        }

        return last;
    }

    //为事项增添标记
    protected void addMark(int str) {

        switch (str){
            case 0: schemes.add(getSchemeCalendar(mYear, mMonth, mDay, 0xFF40db25, "议"));break;
            case 1: schemes.add(getSchemeCalendar(mYear, mMonth, mDay, 0xFFe69138, "假"));break;
            case 2: schemes.add(getSchemeCalendar(mYear, mMonth, mDay, 0xFFdf1356, "事"));break;
            case 3: schemes.add(getSchemeCalendar(mYear, mMonth, mDay, 0xFFaacc44, "记"));break;
            case 4: schemes.add(getSchemeCalendar(mYear, mMonth, mDay, 0xFFbc13f0, "影"));break;
            case 5: schemes.add(getSchemeCalendar(mYear, mMonth, mDay, 0xFF13acf0, "阅"));break;
        }
        mCalendarView.setSchemeDate(schemes);
    }

    //事项消失标记消失
    protected void deleteMark() {
           schemes.remove(getSchemeCalendar(mYear, mMonth, mDay, 0xFF40db25, "议"));
           mCalendarView.setSchemeDate(schemes);
    }

    //建立新日程时判断上下是否存在已有日程，设置相应下划线
     private  void setUnderLine(){
        Button bt1,bt2;
        bt1=getBtn( start -1);
        bt2=getBtn( end );
        //在该日程的上部分是否存在旧事项
        if(!bt1.isEnabled()){
           getView( start-1 ).setBackgroundResource( R.drawable.bg_underline1 );
        }
        else if(!isAdd( bt1 )&&!bt1.getText().toString().equals( null )) {
            getView( start-1 ).setBackgroundResource( R.drawable.bg_underline1 );
        }
        //在该日程的下部分是否存在旧事项
        if(!isAdd( bt2 )&&!bt2.getText().toString().equals( null )){
            getView( end-1 ).setBackgroundResource( R.drawable.bg_underline1 );
        }
     }


     //删除日程时判断上下是否存在已有日程，删除相应下划线
     private void deleteUnderLine(){
         Button bt1,bt2;
         bt1=getBtn( first -1);
         bt2=getBtn( last );
         if(!bt1.isEnabled()){
             getView( first-1 ).setBackgroundResource( R.drawable.bg_underline );
         }
         else if(!isAdd( bt1 )&&!bt1.getText().toString().equals( null )) {
             getView( first-1 ).setBackgroundResource( R.drawable.bg_underline );
         }

         if(!isAdd( bt2 )&&!bt2.getText().toString().equals( null )){
             getView( last-1 ).setBackgroundResource( R.drawable.bg_underline );
         }
     }


    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        //获取被选中的日期：年、月、日
        mYear = calendar.getYear();
        mMonth=calendar.getMonth();
        mDay=calendar.getDay();

        //连接数据库的接口//
        // 日期被点击从数据库获取事项信息刷新时间表//
        if(isClick){

        }
    }
}
