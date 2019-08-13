package com.example.chronus;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemindersFragment extends Fragment {

    private View view;
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
    private TextView edit_tv;
    private TextView addlist_tv;
    private String[] list_name = new String[]{"提醒事项","购物单","任务项","作业","每日提醒计划","电影","书单"};
    private String[] list_num = new String[]{"2","3","0","2","1","6","9"};
    private int[] imgIds = new int[]{R.mipmap.lise_icon1, R.mipmap.lise_icon2, R.mipmap.lise_icon3,
            R.mipmap.lise_icon4, R.mipmap.lise_icon5, R.mipmap.lise_icon6,R.mipmap.lise_icon1, R.mipmap.lise_icon2, R.mipmap.lise_icon3};
    //private int imgIds =  R.mipmap.listico;
    private int choose_img = R.drawable.radio_unselected;
    private int inf = R.drawable.ino_img;
    private String[] but_name = new String[]{"今天","计划","全部","星标"};
    private String[] but_num = new String[]{"2","3","0","2"};
    public ListView listView;
    public RemindersFragment() {
        // Required empty public constructor
    }
    public ArrayList<String> selseted_num = new ArrayList<String>();
    public static RemindersFragment newInstance(String param1){
        RemindersFragment fragment = new RemindersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mContentText = getArguments().getString(ARG_SHOW_TEXT);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.layout_reminders,container, false);

        String agrs1="text";
        if (getArguments() != null){
            Bundle bundle = getArguments();
            agrs1 = bundle.getString(ARG_SHOW_TEXT);
        }

        init();
        return view;
    }

    public void init(){
        final RelativeLayout.LayoutParams params;

        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list_name.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("list_choose",choose_img);
            showitem.put("list_img", imgIds[i]);
            showitem.put("list_name", list_name[i]);
            showitem.put("list_num", list_num[i]);
            listitem.add(showitem);
        }
        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getContext(), listitem, R.layout.layout_listview_a, new String[]{"list_choose","list_img", "list_name", "list_num"},
                new int[]{R.id.choose_img,R.id.list_img, R.id.list_name, R.id.list_num});
        listView = (ListView) view.findViewById(R.id.reminder_list);
        listView.setAdapter(myAdapter);

        RelativeLayout.LayoutParams params_lv;
        params_lv = (RelativeLayout.LayoutParams) listView.getLayoutParams();
        params_lv.height = (int) list_name.length * params_lv.height + 5;
        listView.setLayoutParams(params_lv);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mCallback.onEventsSelected(position);
                switch (view.getId()){
                    case R.id.inf:
                        System.out.println("inf");
                }

            }
        });

        SearchView searchView = view .findViewById(R.id.edit_search);

         edit_tv = view.findViewById(R.id.edit_tv);
         edit_tv.setClickable(true);
         edit_tv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(edit_tv.getText().equals("编辑")){
                     edit_tv.setText("完成");
                     edit_tv.getPaint().setFakeBoldText(true);
                     final List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
                     for (int i = 0; i < list_name.length; i++) {
                         Map<String, Object> showitem = new HashMap<String, Object>();
                         showitem.put("list_choose",choose_img);
                         showitem.put("list_img", imgIds[i]);
                         showitem.put("list_name", list_name[i]);
                         showitem.put("list_num", list_num[i]);
                         showitem.put("inf", inf);
                         listitem.add(showitem);
                     }
                     //创建一个simpleAdapter
                     SimpleAdapter myAdapter = new SimpleAdapter(getContext(), listitem, R.layout.layout_listview, new String[]{"list_choose","list_img", "list_name", "list_num","inf"},
                             new int[]{R.id.choose_img,R.id.list_img, R.id.list_name, R.id.list_num,R.id.inf});
                     listView = (ListView) view.findViewById(R.id.reminder_list);
                     listView.setAdapter(myAdapter);

                     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                         @Override
                         public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                             mCallback.onEventsSelected(position);

                             int seleted_img = R.drawable.radio_selected;
                             Object img1 = R.drawable.radio_unselected;
                                if(!selseted_num.contains(String.valueOf(position))){
                                    selseted_num.add(String.valueOf(position));

                                    Map<String, Object> showitem = new HashMap<String, Object>();
                                    showitem.put("list_choose",seleted_img);
                                    showitem.put("list_img", imgIds[position]);
                                    showitem.put("list_name", list_name[position]);
                                    showitem.put("list_num", list_num[position]);
                                    listitem.set(position,showitem);
                                    SimpleAdapter myAdapter = new SimpleAdapter(getContext(), listitem, R.layout.layout_listview, new String[]{"list_choose","list_img", "list_name", "list_num"},
                                            new int[]{R.id.choose_img,R.id.list_img, R.id.list_name, R.id.list_num});
                                    listView.setAdapter(myAdapter);
                                }else {
                                    selseted_num.remove(String.valueOf(position));
                                    Map<String, Object> showitem = new HashMap<String, Object>();
                                    showitem.put("list_choose",choose_img);
                                    showitem.put("list_img", imgIds[position]);
                                    showitem.put("list_name", list_name[position]);
                                    showitem.put("list_num", list_num[position]);
                                    listitem.set(position,showitem);
                                    SimpleAdapter myAdapter = new SimpleAdapter(getContext(), listitem, R.layout.layout_listview, new String[]{"list_choose","list_img", "list_name", "list_num"},
                                            new int[]{R.id.choose_img,R.id.list_img, R.id.list_name, R.id.list_num});
                                    listView.setAdapter(myAdapter);
                                }





                         }
                     });
                 }else{
                     edit_tv.setText("编辑");
                     edit_tv.getPaint().setFakeBoldText(false);
                     selseted_num.clear();
                     List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
                     for (int i = 0; i < list_name.length; i++) {
                         Map<String, Object> showitem = new HashMap<String, Object>();
                         showitem.put("list_choose",choose_img);
                         showitem.put("list_img", imgIds[i]);
                         showitem.put("list_name", list_name[i]);
                         showitem.put("list_num", list_num[i]);
                         listitem.add(showitem);
                     }
                     //创建一个simpleAdapter
                     SimpleAdapter myAdapter = new SimpleAdapter(getContext(), listitem, R.layout.layout_listview_a, new String[]{"list_choose","list_img", "list_name", "list_num"},
                             new int[]{R.id.choose_img,R.id.list_img, R.id.list_name, R.id.list_num});
                     listView = (ListView) view.findViewById(R.id.reminder_list);
                     listView.setAdapter(myAdapter);
                 }
             }
         });
        addlist_tv = view.findViewById(R.id.addlist_tv);
        addlist_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出添加列表菜单或对话框，对话框里面有写列表名字，选择颜色。
            }
        });



    }



    OnTitleSelectedListener mCallback;
    public interface OnTitleSelectedListener{
        public void onEventsSelected(int position);
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try{
            mCallback = (OnTitleSelectedListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "必须实现OnTitleSelectedListener接口");
        }
    }


}
