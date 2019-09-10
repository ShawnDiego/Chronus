package com.example.chronus;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
    //事项类型，用来跳转到详细信息页
    public static String type;
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
        iv_cal = findViewById(R.id.calendar_tab);
        iv_rem = findViewById(R.id.remainder_tab);
        iv_timeline = findViewById(R.id.timeline_tab);
        iv_tom = findViewById(R.id.tomato_tab);
        iv_set = findViewById(R.id.settings_tab);

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        //默认选择 提醒事项
        mViewPager.setCurrentItem(1);
        iv_rem.setImageResource(R.drawable.lightbulb_fill);
        // register listener
        mViewPager.addOnPageChangeListener(mPageChangeListener);


        findViewById(R.id.calendar_tab).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                chooseTab=1;
                mViewPager.setCurrentItem(0,false);
                iv_set.setImageResource(R.drawable.set);
                iv_cal.setImageResource(R.drawable.calendar_fill);
                iv_rem.setImageResource(R.drawable.lightbulb);
                //iv_timeline.setImageResource(R.drawable.setting);
                iv_tom.setImageResource(R.drawable.clock);

            }
        });

        findViewById(R.id.timeline_tab).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mViewPager.setCurrentItem(2,false);
                chooseTab=3;
                iv_set.setImageResource(R.drawable.set);
                iv_cal.setImageResource(R.drawable.calendar);
                iv_rem.setImageResource(R.drawable.lightbulb);
                //iv_timeline.setImageResource(R.drawable.setting);
                iv_tom.setImageResource(R.drawable.clock);
                return;
            }
        });

        findViewById(R.id.remainder_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTab=2;
                mViewPager.setCurrentItem(1,false);
                iv_set.setImageResource(R.drawable.set);
                iv_cal.setImageResource(R.drawable.calendar);
                iv_rem.setImageResource(R.drawable.lightbulb_fill);
                //iv_timeline.setImageResource(R.drawable.setting);
                iv_tom.setImageResource(R.drawable.clock);

            }
        });
        findViewById(R.id.tomato_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTab=4;
                mViewPager.setCurrentItem(3,false);
                iv_set.setImageResource(R.drawable.set);
                iv_cal.setImageResource(R.drawable.calendar);
                iv_rem.setImageResource(R.drawable.lightbulb);
                //iv_timeline.setImageResource(R.drawable.setting);
                iv_tom.setImageResource(R.drawable.clock_fill);


            }
        });
        findViewById(R.id.settings_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTab=5;
                mViewPager.setCurrentItem(4,false);
                iv_set.setImageResource(R.drawable.set_fill);
                iv_cal.setImageResource(R.drawable.calendar);
               iv_rem.setImageResource(R.drawable.lightbulb);
                //iv_timeline.setImageResource(R.drawable.setting);
               iv_tom.setImageResource(R.drawable.clock);
               
               

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
            //setPageTransformer(true, new VerticalPageTransformer());
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
    //删除数据
    public static void DELETE_LIST(String id) {
        //删除列表和列表里面的值
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("DELETE FROM List WHERE ID = ?", new String[]{id});
        //值
        db.execSQL("DELETE FROM Remind_List WHERE Type = ?", new String[]{Show_List_name(id)});
    }

    //修改数据
    public static void update(String type, String  id,String content) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("UPDATE Remind_List SET Content =?,Type =? WHERE ID = ?",
                new String[]{content,type,id});
    }
    //根据TITLE查询事项内容
    public static String FIND_Content_By_Title(String title) {

        StringBuilder result = new StringBuilder();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Remind_List WHERE TITLE = ?",
                new String[]{title});
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
    //在类型中根据行数查询每一条的TITLE
    public  static String ShowLineTitle_In_Type(int i,String type,String T) {
        StringBuilder title = new StringBuilder();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery
                ("SELECT * FROM Remind_List WHERE Type =? AND Checked=?", new String[]{type,T});


        if (cursor.getCount() <= 0) {
            title.append("无信息");
            cursor.close();
            return title.toString();
        } else {
            cursor.moveToPosition(i);
            title.append(cursor.getString(cursor.getColumnIndex("TITLE")));

            cursor.close();
            return title.toString();
        }
    }


    //根据TITLE查询时间
    public static String ShowDate_By_Title(String title) {
        StringBuilder result = new StringBuilder();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Remind_List WHERE TITLE = ?", new String[]{title});
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

    //判断数据库里相应类型下的条目数
    public static int getCount_By_Type(String type,String T)
    {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT COUNT (*) FROM Remind_List WHERE Type=? AND Checked=?",new String[]{type,T});
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
        return result;
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

    /////////////////////////////////////////////////////////////////////////////分界线
    //判断列表类别数量
    public static int get_ListCount( ){
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT COUNT (*) FROM List",null);
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
        return result;
    }
    //显示列表里指定行的ID
    public  static String ShowLineID_inList(int i){

        StringBuilder result = new StringBuilder();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.query("List",null,null,null,null,null,null,null);

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

    //根据ID查询列表名
    public static String Show_List_name(String id) {
        StringBuilder result = new StringBuilder();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM List WHERE ID = ?",
                new String[]{id});
        //存在数据才返回
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));

            cursor.close();
            return result.append(name).toString();
        } else {
            cursor.close();
            return result.append("该ID下没有信息，出现未知错误").toString();
        }
    }
    //根据ID查询列表下的事项数量
    public static String Show_List_number(String id) {
        StringBuilder result = new StringBuilder();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM List WHERE ID = ?",
                new String[]{id});
        //存在数据才返回
        if (cursor.moveToFirst()) {
            String number = cursor.getString(cursor.getColumnIndex("number"));

            cursor.close();
            return result.append(number).toString();
        } else {
            cursor.close();
            return result.append("该ID下没有信息，出现未知错误").toString();
        }
    }
    //在列表数据表中插入数据
    public static void INSERT_List(String id, String name,String icon_color, String number) {

        SQLiteDatabase db = mDBHelper.getWritableDatabase();//获取可写数据库实例

        db.execSQL("INSERT INTO List(ID,name,icon_color,number) values(?,?,?,?)",
                new String[]{id, name,icon_color,number});
    }
    //修改数据列表书库对应ID的数据
    public static void update_List(String name, String  id) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("UPDATE List SET name =?  WHERE ID = ?",
                new String[]{name,id});
    }
    //每当添加新事项时，相应类型的列表数据库中的事项数就会加1
    public static void Increase_List_Number(String type)
    {
        String number_before;
        SQLiteDatabase db1 = mDBHelper.getReadableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM List WHERE name = ?",
                new String[]{type});//先提取之前的数据再递增
        if (cursor.moveToFirst()) {
            number_before = cursor.getString(cursor.getColumnIndex("number"));

            cursor.close();

        } else {
            number_before = "0";
            cursor.close();

        }
        db1.close();
        Integer i = Integer.parseInt(number_before);
        i++;
        SQLiteDatabase db2 = mDBHelper.getWritableDatabase();
        db2.execSQL("UPDATE List SET number =?  WHERE name = ?",
                new String[]{i.toString(),type});
    }
    //根据ID查询列表的条目前面的图标的颜色
    public static int Show_List_Color_By_ID(String id)
    {
        String color;
        SQLiteDatabase db =mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM List WHERE ID =?", new String[]{id});
        if (cursor.moveToFirst()) {
            color = cursor.getString(cursor.getColumnIndex("icon_color"));

            cursor.close();
            return  Integer.parseInt(color);
        } else {
            cursor.close();
            return 0;
        }
    }

    //删除列表和对应的事项数据
    public static void DELETE_LIST_By_ID(String id) {
        //删除列表和列表里面的值
        //注意这里有顺序问题，要先删除事项的数据，再删除列表数据，不然无法从对应的类型名找到对应的事项
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("DELETE FROM Remind_List WHERE Type = ?", new String[]{Show_List_name(id)});

        db.execSQL("DELETE FROM List WHERE ID = ?", new String[]{id});

    }
}