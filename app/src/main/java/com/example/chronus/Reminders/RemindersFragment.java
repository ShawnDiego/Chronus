package com.example.chronus.Reminders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chronus.R;

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
    private TextView delete_tv;
    private String[] list_name = new String[]{"提醒事项","购物单","任务项","作业","每日提醒计划","电影","书单"};
    private String[] list_num = new String[]{"2","3","0","2","1","6","9"};
    private int[] imgIds = new int[]{R.mipmap.lise_icon1, R.mipmap.lise_icon2, R.mipmap.lise_icon3,
            R.mipmap.lise_icon4, R.mipmap.lise_icon5, R.mipmap.lise_icon6,R.mipmap.lise_icon1, R.mipmap.lise_icon2, R.mipmap.lise_icon3};

    private int choose_img = R.drawable.radio_unselected;
    private int inf = R.drawable.ino_img;
    private String[] but_name = new String[]{"今天","计划","全部","星标"};
    private String[] but_num = new String[]{"2","3","0","2"};
    public ListView listView;
    public ArrayList<String> selseted_num = new ArrayList<String>();

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
        //列表获取数据
        final List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list_name.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("list_choose",choose_img);
            showitem.put("list_img", imgIds[i]);
            showitem.put("list_name", list_name[i]);
            showitem.put("list_num", list_num[i]);
            listitem.add(showitem);
        }
        SimpleAdapter myAdapter = new SimpleAdapter(getContext(), listitem, R.layout.layout_listview_a, new String[]{"list_choose","list_img", "list_name", "list_num"},
                new int[]{R.id.choose_img,R.id.list_img, R.id.list_name, R.id.list_num});
        listView = (ListView) view.findViewById(R.id.reminder_list);
        listView.setAdapter(myAdapter);
        //设置列表大小
        RelativeLayout.LayoutParams params_lv;
        params_lv = (RelativeLayout.LayoutParams) listView.getLayoutParams();
        params_lv.height = (int) list_name.length * params_lv.height + 5;
        listView.setLayoutParams(params_lv);
        onClickListView();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //长按显示修改列表名称
                //需要数据库同步
                final  EditText et = new EditText(getActivity());

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("修改列表");
                builder.setView(et);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //数据获取
                        Toast.makeText(getActivity(), et.getText().toString(), Toast.LENGTH_LONG).show();
                        //修改列表的数据库
                        Toast.makeText(getActivity(), "成功修改列表 "+et.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消修改列表", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create();
                builder.show();
                return true;
            }
        });

        SearchView searchView = view .findViewById(R.id.edit_search);
        searchView.setFocusable(false);
        delete_tv = view.findViewById(R.id.delete_tv);
        delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除列表的数据库
                //询问确认对话框
            }
        });
        edit_tv = view.findViewById(R.id.edit_tv);//编辑 标签
        edit_tv.setClickable(true);
        edit_tv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(edit_tv.getText().equals("编辑")){
                     edit_tv.setText("完成");
                     delete_tv.setVisibility(View.VISIBLE);
                     edit_tv.getPaint().setFakeBoldText(true);
                     SearchView searchView = view.findViewById(R.id.edit_search);
                     searchView.clearFocus();
                     searchView.setClickable(false);
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
                             if(edit_tv.getText().equals("完成")){
                                 if(!selseted_num.contains(String.valueOf(position))){
                                     selseted_num.add(String.valueOf(position));//选中后把当前项加入到队列中
                                     ImageView choose_img = view.findViewById(R.id.choose_img);
                                     choose_img.setImageResource(R.drawable.radio_selected);
                                 }else {
                                     selseted_num.remove(String.valueOf(position));//从队列中移除
                                     ImageView choose_img = view.findViewById(R.id.choose_img);
                                     choose_img.setImageResource(R.drawable.radio_unselected);
                                 }
                             }
                         }
                     });
                 }else{
                     edit_tv.setText("编辑");
                     delete_tv.setVisibility(View.INVISIBLE);
                     edit_tv.getPaint().setFakeBoldText(false);
                     selseted_num.clear();
                     onClickListView();
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
            public void onClick(View v) {
                //弹出添加列表菜单或对话框，对话框里面有写列表名字，选择颜色。
                final  EditText et = new EditText(getActivity());

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("新建列表");
                builder.setView(et);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //数据获取
                        Toast.makeText(getActivity(), et.getText().toString(), Toast.LENGTH_LONG).show();
                        //添加列表的数据库
                        Toast.makeText(getActivity(), "成功新建列表 "+et.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消新建列表", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create();
                builder.show();
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        //解决返回activity弹出键盘
        RelativeLayout searcg_ll = view.findViewById(R.id.search_ll);
        searcg_ll.setFocusable(true);
        searcg_ll.setFocusableInTouchMode(true);
        searcg_ll.requestFocus();
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
    public void onClickListView(){
        //列表中的项目被点击
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
                mCallback.onEventsSelected(position);
                TextView edit_tv = view.findViewById(R.id.edit_tv);
                if(edit_tv.getText().equals("编辑")){
                    Intent intent = new Intent(getContext(), ReminderItemsActivity.class);
                    startActivity(intent);

                    TextView tv_back = view.findViewById(R.id.item_back);
                }

            }
        });
    }

}
