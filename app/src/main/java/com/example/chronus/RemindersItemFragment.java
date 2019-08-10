package com.example.chronus;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemindersItemFragment extends Fragment {
    private View view;
    int mCurrentPosition = -1;
    final static String EVENTS_POSITION = "position";
    private static final String ARG_SHOW_TEXT = "text";
    private String[] item_name = new String[]{"提醒事项","购物单","任务项","作业","每日提醒计划","电影","书单"};
    private int imgIds =  R.drawable.radio_unselected;

    public RemindersItemFragment() {
        // Required empty public constructor
    }
    public static RemindersItemFragment newInstance(String param1){
        RemindersItemFragment fragment = new RemindersItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if(savedInstanceState != null){
            mCurrentPosition = savedInstanceState.getInt(EVENTS_POSITION);
        }

        view = inflater.inflate(R.layout.layout_reminders_item,container,false);

        return  view;
    }

    @Override
    public void onStart(){
        super.onStart();
        Bundle args = getArguments();
        if(args != null){
            updateItemView(args.getInt(EVENTS_POSITION));
        }else if (mCurrentPosition != -1){
            updateItemView(mCurrentPosition);
        }
        init();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(EVENTS_POSITION,mCurrentPosition);
    }

    public void updateItemView(int position){
        Activity activity = this.getActivity();
        mCurrentPosition = position;
    }

    public void init() {
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < item_name.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("item_img", imgIds);
            showitem.put("item_name", item_name[i]);
            listitem.add(showitem);
        }

        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getContext(), listitem, R.layout.layout_item_view, new String[]{"item_img", "item_name"},
                new int[]{R.id.item_img, R.id.item_name});
        ListView listView = (ListView) view.findViewById(R.id.item_list);
        listView.setAdapter(myAdapter);
    }
}
