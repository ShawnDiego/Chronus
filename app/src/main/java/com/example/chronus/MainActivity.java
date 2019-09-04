package com.example.chronus;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.chronus.AlarmNotification.AlarmService;
import com.example.chronus.AlarmNotification.DateTimeUtil;
import com.example.chronus.Reminders.ReminderItemsActivity;
import com.example.chronus.Reminders.RemindersFragment;
import com.example.chronus.Setting.SettingFragment;
import com.example.chronus.clendar.CalendarFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener , RemindersFragment.OnTitleSelectedListener{

    public ViewPager mViewPager;

    private List<Fragment>  mFragments;
    private FragmentPagerAdapter mAdapter;

    public static SQDB mDBHelper;
    //事项列表行数，用来跳转到详细信息页
    public static int Line;
    //用来确定所要修改的数据库条目
    public  static String Edit_ID;
    private static Context context;



    private ImageView iv_cal;
    private ImageView iv_rem;
    private ImageView iv_timeline;
    private ImageView iv_tom;
    private ImageView iv_set;


    private int chooseTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //进入全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);

        //getWindow().setExitTransition(slide);

        setContentView(R.layout.activity_main);

        initView();

        //数据库
        mDBHelper = new SQDB(this);

    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);



    }

    private void initView(){
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        // find view
        mViewPager = findViewById(R.id.fragment_vp);

        // init layout_fragment 一级碎片
        mFragments = new ArrayList<>();
        mFragments.add(CalendarFragment.newInstance("日历"));
        mFragments.add(RemindersFragment.newInstance("事项"));
        mFragments.add(ViewFragment.newInstance("时间轴"));//Fragment的名字都要修改
        mFragments.add(TomatoFragment.newInstance("番茄"));
        mFragments.add(SettingFragment.newInstance("设置"));


        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);

        // register listener
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        iv_cal = findViewById(R.id.calendar_tab);
        iv_rem = findViewById(R.id.remainder_tab);
        iv_timeline = findViewById(R.id.timeline_tab);
        iv_tom = findViewById(R.id.tomato_tab);
        iv_set = findViewById(R.id.settings_tab);

        findViewById(R.id.timeline_tab).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mViewPager.setCurrentItem(2,false);
                chooseTab=3;
                iv_set.setImageResource(R.drawable.setting_un);
                iv_cal.setImageResource(R.drawable.cal_un);
                iv_rem.setImageResource(R.drawable.rem_un);
                //iv_timeline.setImageResource(R.drawable.setting);
                iv_tom.setImageResource(R.drawable.tom_un);
                return;
            }
        });

        findViewById(R.id.remainder_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTab=2;
                mViewPager.setCurrentItem(1,false);
                iv_set.setImageResource(R.drawable.setting_un);
                iv_cal.setImageResource(R.drawable.cal_un);
                iv_rem.setImageResource(R.drawable.rem);
                //iv_timeline.setImageResource(R.drawable.setting);
                iv_tom.setImageResource(R.drawable.tom_un);

            }
        });
        findViewById(R.id.tomato_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTab=4;
                mViewPager.setCurrentItem(3,false);
                iv_set.setImageResource(R.drawable.setting_un);
                iv_cal.setImageResource(R.drawable.cal_un);
                iv_rem.setImageResource(R.drawable.rem_un);
                //iv_timeline.setImageResource(R.drawable.setting);
                iv_tom.setImageResource(R.drawable.tom);


            }
        });
        findViewById(R.id.settings_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTab=5;
                mViewPager.setCurrentItem(4,false);
                iv_set.setImageResource(R.drawable.setting);
                iv_cal.setImageResource(R.drawable.cal_un);
               iv_rem.setImageResource(R.drawable.rem_un);
                //iv_timeline.setImageResource(R.drawable.setting);
               iv_tom.setImageResource(R.drawable.tom_un);
               
               

            }
        });
        findViewById(R.id.calendar_tab).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                chooseTab=1;
                mViewPager.setCurrentItem(0,false);
                iv_set.setImageResource(R.drawable.setting_un);
                iv_cal.setImageResource(R.drawable.cal);
                iv_rem.setImageResource(R.drawable.rem_un);
                //iv_timeline.setImageResource(R.drawable.setting);
                iv_tom.setImageResource(R.drawable.tom_un);
               
            }
        });
    }



    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == 2){

                switch (mViewPager.getCurrentItem()) {
                    case 0:
                        chooseTab=1;
                        //设置图标高亮
                        break;
                    case 1:
                        chooseTab=2;
                        //设置图标高亮
                        break;
                    case 2:
                        chooseTab=3;
                        //设置图标高亮
                        break;
                    case 3:
                        chooseTab=4;
                        //设置图标高亮
                        break;
                    case 4:
                        chooseTab=5;
                        //设置图标高亮
                        iv_set.setImageResource(R.drawable.setting);
                        iv_cal.setImageResource(R.drawable.cal_1);
                        // iv_rem.setImageResource(R.drawable.setting);
                        //iv_timeline.setImageResource(R.drawable.setting);
                        // iv_tom.setImageResource(R.drawable.setting);
                       break;
                }

            }
        }
    };

    public class VerticalViewPager extends ViewPager {

        public VerticalViewPager(Context context) {
            super(context);
            init();
        }

        public VerticalViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            // 最重要的设置，将viewpager翻转
            setPageTransformer(true, new VerticalPageTransformer());
            // 设置去掉滑到最左或最右时的滑动效果
            setOverScrollMode(OVER_SCROLL_NEVER);
        }

        private class VerticalPageTransformer implements ViewPager.PageTransformer {

            @Override
            public void transformPage(View view, float position) {

                if (position < -1) { // [-Infinity,-1)
                    // 当前页的上一页
                    view.setAlpha(0);

                } else if (position <= 1) { // [-1,1]
                    view.setAlpha(1);

                    // 抵消默认幻灯片过渡
                    view.setTranslationX(view.getWidth() * -position);

                    //设置从上滑动到Y位置
                    float yPosition = position * view.getHeight();
                    view.setTranslationY(yPosition);

                } else { // (1,+Infinity]
                    // 当前页的下一页
                    view.setAlpha(0);
                }
            }
        }

        /**
         * 交换触摸事件的X和Y坐标
         */
        private MotionEvent swapXY(MotionEvent ev) {
            float width = getWidth();
            float height = getHeight();

            float newX = (ev.getY() / height) * width;
            float newY = (ev.getX() / width) * height;

            ev.setLocation(newX, newY);

            return ev;
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
            swapXY(ev);
            return intercepted; //为所有子视图返回触摸的原始坐标
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            return super.onTouchEvent(swapXY(ev));
        }
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return this.mList == null ? null : this.mList.get(position);
        }

        @Override
        public int getCount() {
            return this.mList == null ? 0 : this.mList.size();
        }
    }



    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.calendar_tab:
                chooseTab=1;
                mViewPager.setCurrentItem(0,false);
               // Toast.makeText(getContext(),"Option 1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remainder_tab:
                chooseTab=2;
                mViewPager.setCurrentItem(1,false);
                break;
            case R.id.timeline_tab:
                chooseTab=3;
                mViewPager.setCurrentItem(2,false);
                break;
            case R.id.tomato_tab:
                chooseTab=4;
                mViewPager.setCurrentItem(3,false);
                break;
            case R.id.settings_tab:
                chooseTab=5;
                mViewPager.setCurrentItem(4,false);
                break;
        }
    }

    @Override
    public void onEventsSelected(int position){




    }

//数据库操作
//初始化化以及格式化时间
private static SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
    private static Date date = new Date();
    private static String Day = formatt.format(date);

    //插入数据
    public static void INSERT(String type, String id, String title,String m,String time) {

        SQLiteDatabase db = mDBHelper.getWritableDatabase();//获取可写数据库实例

        db.execSQL("INSERT INTO Remind_List(Type,ID,TITLE,DAY,Content) values(?,?,?,?,?)",
                new String[]{type, id,title,time,m,});

    }

    //删除数据
    public static void DELETE(String id) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("DELETE FROM Remind_List WHERE ID = ?", new String[]{id});
    }

    //修改数据
    public static void update(String type, String  id,String content) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("UPDATE Remind_List SET Content =?,Type =? WHERE ID = ?",
                new String[]{content,type,id});
    }

    //根据ID查询数据
    public static String FIND(String id) {

        StringBuilder result = new StringBuilder();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Remind_List WHERE ID = ?",
                new String[]{id});
        //存在数据才返回
        if (cursor.moveToFirst()) {
            String content = cursor.getString(cursor.getColumnIndex("Content"));


            cursor.close();
            return  result.append(content).toString();
        } else {
            cursor.close();
            return result.append("该ID下没有信息，出现未知错误").toString();
        }
    }
    //显示数据库里指定行的ID
    public  static String ShowLineID(int i){

        StringBuilder result = new StringBuilder();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.query("Remind_List",null,null,null,null,null,null,null);

        if (cursor.getCount()<=0){
            result.append("无信息");
            cursor.close();
            return result.toString();
        }
        else if(i<cursor.getCount())
        {
            cursor.moveToPosition(i);
            String id = cursor.getString(cursor.getColumnIndex("ID"));

            cursor.close();
            return id;
        }
        else{
            result.append("无信息");
            cursor.close();
            return result.toString();
        }

    }
    public  static String ShowLineTitle(int i){

        StringBuilder result = new StringBuilder();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.query("Remind_List",null,null,null,null,null,null,null);

        if (cursor.getCount()<=0){
            result.append("无信息");
            cursor.close();
            return result.toString();
        }
        else if(i<cursor.getCount())
        {
            cursor.moveToPosition(i);
            String title = cursor.getString(cursor.getColumnIndex("TITLE"));

            cursor.close();
            return title;
        }
        else{
            result.append("无信息");
            cursor.close();
            return result.toString();
        }

    }

    //根据ID查询时间
    public static String ShowDate(String id) {
        StringBuilder result = new StringBuilder();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Remind_List WHERE ID = ?", new String[]{id});
        //存在数据才返回
        if (cursor.moveToFirst()) {
            String data = cursor.getString(cursor.getColumnIndex("DAY"));

            cursor.close();
            return result.append(data).toString();
        } else {
            cursor.close();
            return result.append("该ID下没有信息，出现未知错误").toString();
        }
    }
    //判断数据库条目数
    public static int getCount( )
    {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT COUNT (*) FROM Remind_List",null);
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
        return result;
    }


}