package com.example.chronus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemindersFragment extends Fragment {

    private View view;
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;

    private String[] list_name = new String[]{"list1","list2","list3"};
    private String[] list_num = new String[]{"2","3","0"};
   // private int[] imgIds = new int[]{R.mipmap.listico, R.mipmap.listico, R.mipmap.listico};
    private int imgIds =  R.mipmap.listico;

    public RemindersFragment() {
        // Required empty public constructor
    }

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
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list_name.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("list_img", imgIds);
            showitem.put("list_name", list_name[i]);
            showitem.put("list_num", list_num[i]);
            listitem.add(showitem);
        }

        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getContext(), listitem, R.layout.layout_listview, new String[]{"list_img", "list_name", "list_num"}, new int[]{R.id.list_img, R.id.list_name, R.id.list_num});
        ListView listView = (ListView) view.findViewById(R.id.reminder_list);
        listView.setAdapter(myAdapter);

        DisplayMetrics dm = getResources().getDisplayMetrics();

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        System.out.println(width);
        RelativeLayout.LayoutParams params;
        Button but1 = view.findViewById(R.id.but_today);
        Button but2 = view.findViewById(R.id.but_plan);
        Button but3 = view.findViewById(R.id.but_all);
        Button but4 = view.findViewById(R.id.but_star);
        params = (RelativeLayout.LayoutParams) but1.getLayoutParams();
        params.width = (int)(width*0.4);
        but1.setLayoutParams(params);
        params = (RelativeLayout.LayoutParams) but2.getLayoutParams();
        params.width = (int)(width*0.4);
        but2.setLayoutParams(params);
        params = (RelativeLayout.LayoutParams) but3.getLayoutParams();
        params.width = (int)(width*0.4);
        but3.setLayoutParams(params);
        params = (RelativeLayout.LayoutParams) but4.getLayoutParams();
        params.width = (int)(width*0.4);
        but4.setLayoutParams(params);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mCallback.onEventsSelected(position);
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
