package com.example.chronus.TimeLine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chronus.MainActivity;
import com.example.chronus.R;

import java.util.ArrayList;
import java.util.Collections;
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
        list.add(new TimeData(TimeFormat.toDate("2019-09-09 10:00"),"","准备数学建模"));
        list.add(new TimeData(TimeFormat.toDate("2019-09-09 12:00"),"去体育场取快递",""));
        list.add(new TimeData(TimeFormat.toDate("2019-09-09 19:30"),"写实验报告作业",""));
        list.add(new TimeData(TimeFormat.toDate("2019-09-09 12:05"),"找老师签字",""));
        list.add(new TimeData(TimeFormat.toDate("2019-09-10 19:30"),"","准备数学建模"));
        list.add(new TimeData(TimeFormat.toDate("2019-09-11 19:30"),"写实验报告作业",""));
        list.add(new TimeData(TimeFormat.toDate("2019-09-11 19:30"),"写实验报告作业",""));
        list.add(new TimeData(TimeFormat.toDate("2019-09-11 19:30"),"","数学建模开始"));
        list.add(new TimeData(TimeFormat.toDate("2019-09-12 19:50"),"","数学建模结束"));
        //加进来的数据，如果是日历则增加desc;如果是事项加title;
    }
}
