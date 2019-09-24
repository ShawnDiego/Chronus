package com.example.chronus.TimeLine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chronus.MainActivity;
import com.example.chronus.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TimeLineFragment extends Fragment {
    private static final String ARG_SHOW_TEXT = "text";
    private  String mContentText;

    private TextView txtDateTime;
    private TextView txt_date_title;
    private RelativeLayout rlTitle;
    private View view;
    private View vLine;
    private int position;
    private TimeData timeData;
    private List<TimeData> data;
    public TextView tv_item;
    List<TimeData> list = new ArrayList<>();

    //存储列表数据
    List<TimeData> list_timeline = new ArrayList<>();
    TimeAdapter adapter;
    public TimeLineFragment() {
        // Required empty public constructor
    }


    public static TimeLineFragment newInstance(String param1){
        TimeLineFragment fragment = new TimeLineFragment();
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
        view = inflater.inflate(R.layout.layout_recyclerview, container, false);
        init();
        return view;
    }
    private void  init(){
        RecyclerView rlView = (RecyclerView) view.findViewById(R.id.activity_rlview);
//        rlTitle = (RelativeLayout) view.findViewById(R.id.rl_title);
//        vLine = view.findViewById(R.id.v_line);
//        txtDateTime = (TextView) view.findViewById(R.id.txt_date_time);
//        txt_date_title = (TextView) view.findViewById(R.id.txt_date_title);
        //初始化数据
        initData();
        // 将数据按照时间排序
        TimeComparator comparator = new TimeComparator();
        Collections.sort(list, comparator);
        // recyclerview绑定适配器
        rlView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TimeAdapter((MainActivity) getActivity(), list);
        rlView.setAdapter(adapter);


    }


    private void initData() {
        list.clear();
        Calendar calendar = Calendar.getInstance();

        //获取系统的日期
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH)+1;
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //获取系统时间
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);

        String today_day = year + "-" + month + "-" + day;//2019-9-9
        Log.d("today",today_day);
        List<String> reminder_today_id= new ArrayList<String>();
        reminder_today_id = MainActivity.find_ID_By_DAY(today_day);
        Iterator iterator=reminder_today_id.iterator();
        String reminder_title, reminder_time;
        while (iterator.hasNext()){
            String id = iterator.next().toString();
            Log.d("id",id);
            reminder_title = MainActivity.Get_Title_by_ID(id);
            reminder_time = MainActivity.Get_Day_by_ID(id);
            list.add(new TimeData(TimeFormat.toDate(reminder_time),reminder_title,""));
        }
        List<String> calendar_today_id= new ArrayList<String>();
        calendar_today_id = MainActivity.find_ID_By_date(today_day);
        Iterator iterator_cal=calendar_today_id.iterator();
        String cal_title, cal_time;
        while (iterator_cal.hasNext()){
            String id = iterator_cal.next().toString();
            Log.d("id",id);
            cal_title = MainActivity.get_Title_In_Schedule(id);
            String date_cal = today_day +"-"+ String.valueOf(MainActivity.get_StartTime_In_Schedule(id))+"-0";
            cal_time = date_cal;
            list.add(new TimeData(TimeFormat.toDate(cal_time),"",cal_title));
        }


        list.add(new TimeData(TimeFormat.toDate("2019-09-24 10:00"),"","准备数学建模竞赛"));
        list.add(new TimeData(TimeFormat.toDate("2019-09-24 12:00"),"去体育场取快递",""));
        list.add(new TimeData(TimeFormat.toDate("2019-09-24 14:00"),"","与李铭的电话会议"));
        list.add(new TimeData(TimeFormat.toDate("2019-09-24 19:30"),"查看花呗账单",""));
        list.add(new TimeData(TimeFormat.toDate("2019-09-24 12:05"),"找老师签字",""));
        list.add(new TimeData(TimeFormat.toDate("2019-09-24 12:35"),"去图书馆还书",""));
        list.add(new TimeData(TimeFormat.toDate("2019-09-24 19:30"),"","去实验室准备器材"));
        list.add(new TimeData(TimeFormat.toDate("2019-09-24 20:30"),"写实验报告作业",""));
        list.add(new TimeData(TimeFormat.toDate("2019-09-24 19:30"),"","数学建模开始"));
        list.add(new TimeData(TimeFormat.toDate("2019-09-24 20:00"),"","小组会议讨论"));

        //加进来的数据，如果是日历则增加desc;如果是事项加title;
    }
}
