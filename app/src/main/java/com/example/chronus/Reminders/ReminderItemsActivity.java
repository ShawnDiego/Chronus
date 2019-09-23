package com.example.chronus.Reminders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chronus.MainActivity;
import com.example.chronus.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.security.AccessController.getContext;

public class ReminderItemsActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;

    int mCurrentPosition = -1;
    private int imgIds =  R.drawable.radio_unselected;
    private int imgAdd =  R.drawable.item_add;
    private int checked[] ={0,1,0,1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int seleted_img = R.drawable.radio_selected;
    private ImageView mPopupMenu;
    public static String get_Type;
    public String ListId;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //进入全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        getWindow().setEnterTransition(slide);
        setContentView(R.layout.layout_reminders_item);
        context = this;
        //获取传递过来的事项类型
        Intent intent = getIntent();
        get_Type = intent.getStringExtra("Line");
        ListId = intent.getStringExtra("ListId");
        //将事项类型显示到页面上方
        TextView textView =(TextView)findViewById(R.id.item_list_name);
        textView.setText(get_Type);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.item_back:
                this.finish();
                break;
        }
    }
    @Override
    public void onStart(){
        super.onStart();
//        Bundle args = getArguments();
//        if(args != null){
//            updateItemView(args.getInt(EVENTS_POSITION));
//        }else if (mCurrentPosition != -1){
//            updateItemView(mCurrentPosition);
//        }
        init();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LinearLayout item_ll = findViewById(R.id.item_ll);
                item_ll.setBackgroundColor(Color.BLACK);
                RelativeLayout item_rl = findViewById(R.id.item_rl);
                item_rl.setVisibility(View.VISIBLE);
            }
        },500);

    }
    @Override
    public void onResume(){
        super.onResume();
        LinearLayout item_ll = findViewById(R.id.item_ll);
        item_ll.setBackgroundColor(Color.WHITE);
        RelativeLayout item_rl = findViewById(R.id.item_rl);
        item_rl.setVisibility(View.INVISIBLE);
        getListView("0");
        Log.d("state","onResume");
    }
    @Override
    public void onPause(){
        super.onPause();
        LinearLayout item_ll = findViewById(R.id.item_ll);
        item_ll.setBackgroundColor(Color.WHITE);
        RelativeLayout item_rl = findViewById(R.id.item_rl);
        item_rl.setVisibility(View.INVISIBLE);
        Log.d("state","onPause");
    }
    private void getListView(String t){
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < MainActivity.getCount_By_Type(get_Type,t); i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            if(checked[i] == 0){
                showitem.put("item_img", imgIds);
            }else{
                showitem.put("item_img",seleted_img);
            }

            showitem.put("item_name", MainActivity.ShowLineTitle_In_Type(i,get_Type,t));

            listitem.add(showitem);
        }

        //最后加一个添加item
        Map<String, Object> showitem = new HashMap<String, Object>();
        showitem.put("item_img", imgAdd);
        showitem.put("item_name", "添加新事项");
        listitem.add(showitem);
        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(context, listitem, R.layout.layout_item_view, new String[]{"item_img", "item_name"},
                new int[]{R.id.choose_img, R.id.item_name});
        ListView listView = (ListView) findViewById(R.id.item_list);
        listView.setAdapter(myAdapter);
    }
    private void get_Item(){
        //Type,ID,TITLE,DAY,Content,Checked

    }
    private void init() {
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < MainActivity.getCount_By_Type(get_Type,"0"); i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            if(checked[i] == 0){
                showitem.put("item_img", imgIds);
            }else{
                showitem.put("item_img",seleted_img);
            }

            showitem.put("item_name", MainActivity.ShowLineTitle_In_Type(i,get_Type,"0"));

            listitem.add(showitem);
        }
        //final Integer number = listitem.size();
        final Integer number = MainActivity.getCount();
        //最后加一个添加item
        Map<String, Object> showitem = new HashMap<String, Object>();
        showitem.put("item_img", imgAdd);
        showitem.put("item_name", "添加新事项");
        listitem.add(showitem);
        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(context, listitem, R.layout.layout_item_view, new String[]{"item_img", "item_name"},
                new int[]{R.id.choose_img, R.id.item_name});
        ListView listView = (ListView) findViewById(R.id.item_list);
        listView.setAdapter(myAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ImageView sel_img = view.findViewById(R.id.choose_img);
                TextView sel_tv = view.findViewById(R.id.item_name);
                if(sel_tv.getText().equals("添加新事项")){
                    //弹出新建
                    //Intent intent = new Intent(getActivity(), ADD_DATA_Activity.class);
                    Log.d("id",Integer.toString(MainActivity.getCount()));
                    Intent intent = new Intent(context, ADD_DATA_Activity.class);
                    intent.putExtra("number",number);
                    intent.putExtra("type", get_Type);
                    startActivity(intent);
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
                TextView sel_tv = view.findViewById(R.id.item_name);
                if(sel_tv.getText().equals("添加新事项")){
                    //不操作
                }else{
                    //弹出事件详情
                    Log.d("Scroll", "当前长按" + i);
                    //跳转到事件详情activity
                    //Intent intent = new Intent(context, Item_tosee.class);
                    //根据所长按的索引的序列号也就是数据库中的第几行，找到对应的ID
                    //MainActivity.Line = i;
                    //MainActivity.type = get_Type;
                    //intent.putExtra("Number", i);
                   // startActivity(intent);

                    MainActivity.Edit_ID = MainActivity.ShowLineID(MainActivity.Line);
                    Intent intent = new Intent(getApplicationContext(), Update_DATA_Activity.class);
                    intent.putExtra("item_id",MainActivity.ShowLineID(MainActivity.Line));

                    startActivity(intent);
                }


                return true;
            }
        });
        mPopupMenu = findViewById(R.id.item_top_menu);
        mPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu();
            }
        });

        TextView back = findViewById(R.id.item_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mMediaPlayer;
                mMediaPlayer =  MediaPlayer.create(getApplication(),R.raw.confirm_down);
                mMediaPlayer.start();
                finish();

            }
        });
        ImageView img = findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }

    private void showPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(this,mPopupMenu);
        if(getContext() == null){
            return ;
        }else {
            // popupMenu.inflate(R.menu.reminders_menu);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.reminders_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.rem_delete:
                            //Toast.makeText(context,"Option 1",Toast.LENGTH_SHORT).show();
                            //是否删除，弹出一个对话框问问
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            AlertDialog alertDialog = builder
                                    .setTitle("系统提示")
                                    .setMessage("你确定要删除列表吗？")
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int n) {

                                        }
                                    })
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int n) {
                                            //获取列表id，准备开始删除
                                            MainActivity.DELETE_LIST_By_ID(ListId);
                                            Log.d("delete list id",ListId);
                                            MediaPlayer mMediaPlayer;
                                            mMediaPlayer =  MediaPlayer.create(context,R.raw.delete);
                                            mMediaPlayer.start();
                                            finish();
                                        }
                                    }).create();
                            alertDialog.show();
                            return true;

                        case R.id.rem_finish:
                            //显示全部完成的事项
                            getListView("1");
                            Toast.makeText(context,"此页面为已完成的项目",Toast.LENGTH_SHORT).show();
                            return true;
                        default:
                            //do nothing
                    }

                    return false;
                }
            });
            popupMenu.show();
        }
    }
    public void updateItemView(int position){
        Activity activity = this;
        mCurrentPosition = position;
    }

@Override
    public void onDestroy(){
    super.onDestroy();
    LinearLayout item_ll = findViewById(R.id.item_ll);
    item_ll.setBackgroundColor(Color.WHITE);
    RelativeLayout item_rl = findViewById(R.id.item_rl);
    item_rl.setVisibility(View.INVISIBLE);
}

}
