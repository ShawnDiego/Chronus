package com.example.chronus;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemindersItemFragment extends Fragment {
    private View view;
    int mCurrentPosition = -1;
    final static String EVENTS_POSITION = "position";
    private static final String ARG_SHOW_TEXT = "text";
    private String[] item_name = new String[]{"事项1","事项2","事项1","事项1","事项1","事项1","事项1"};
    private int imgIds =  R.drawable.radio_unselected;
    private int imgAdd =  R.drawable.item_add;
    private int checked[] ={0,1,0,1,0,0,1};
    private int seleted_img = R.drawable.radio_selected;
    private ViewPager mViewPager;

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
            if(checked[i] == 0){
                showitem.put("item_img", imgIds);
            }else{
                showitem.put("item_img",seleted_img);
            }

            showitem.put("item_name", item_name[i]);
            listitem.add(showitem);
        }
        //最后加一个添加item
        Map<String, Object> showitem = new HashMap<String, Object>();

        showitem.put("item_img", imgAdd);
        showitem.put("item_name", "添加新事项");
        listitem.add(showitem);
        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getContext(), listitem, R.layout.layout_item_view, new String[]{"item_img", "item_name"},
                new int[]{R.id.choose_img, R.id.item_name});
        ListView listView = (ListView) view.findViewById(R.id.item_list);
        listView.setAdapter(myAdapter);

        RelativeLayout.LayoutParams params_lv;
        params_lv = (RelativeLayout.LayoutParams) listView.getLayoutParams();
        params_lv.height = (int) (item_name.length+1) * params_lv.height + 5;
        System.out.println("params_lv.height"+params_lv.height);
        listView.setLayoutParams(params_lv);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ImageView sel_img = view.findViewById(R.id.choose_img);
                TextView sel_tv = view.findViewById(R.id.item_name);
                if(sel_tv.getText().equals("添加新事项")){
                    //弹出新建
                } else if(sel_img.getTag().toString().equals("1") || checked[i] == 1){
                    sel_img.setImageResource(imgIds);
                    sel_img.setTag("0");
                    checked[i]=0;
                } else if(sel_img.getTag().toString().equals("0") || checked[i] == 0){
                    sel_img.setImageResource(seleted_img);
                    sel_img.setTag("1");
                    checked[i]=1;
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            //弹出事件详情
                Log.d("Scroll", "当前长按" + i);


                return false;
            }
        });
    }



}
