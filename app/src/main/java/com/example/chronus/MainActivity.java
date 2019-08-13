package com.example.chronus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , RemindersFragment.OnTitleSelectedListener{

    public ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;

    private List<Fragment>  mFragments;
    private FragmentPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        // find view
        mViewPager = findViewById(R.id.fragment_vp);
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        // init layout_fragment 一级碎片
        mFragments = new ArrayList<>();
        mFragments.add(ViewFragment.newInstance("日历"));//Fragment的名字都要修改
        mFragments.add(RemindersFragment.newInstance("事项"));
        mFragments.add(ViewFragment.newInstance("时间轴"));//Fragment的名字都要修改
        mFragments.add(ViewFragment.newInstance("番茄"));//Fragment的名字都要修改
        mFragments.add(ViewFragment.newInstance("设置"));//Fragment的名字都要修改

        //下面是二级碎片
        mFragments.add(RemindersItemFragment.newInstance("提醒事项"));

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);

        // register listener
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);


        findViewById(R.id.timeline_tab).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mViewPager.setCurrentItem(2,false);
                mTabRadioGroup.clearCheck();
                return;
            }
        });


    }
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            float MIN_SCALE=0.75f;
//            float scaleFactor=MIN_SCALE+(1-MIN_SCALE)*(1-Math.abs(position));
//            if (positionOffset>1||positionOffset<-1){
//                mViewPager.setAlpha(0f);
//            }else if (positionOffset<=0){
//                mViewPager.setAlpha(1+positionOffset);
//                mViewPager.setScaleX(scaleFactor);
//                mViewPager.setScaleY(scaleFactor);
//            }else if (positionOffset<=1){
//                mViewPager.setAlpha(1-positionOffset);
//                mViewPager.setScaleX(scaleFactor);
//                mViewPager.setScaleY(scaleFactor);
//            }
// 滚动的时候改变自定义控件的动画
           // Log.d("Scroll", "position：" + position);
            //Log.d("Scroll", "positionOffset：" + positionOffset);
            //Log.d("Scroll", "positionOffsetPixels：" + positionOffsetPixels);

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == 2){
                RadioButton radioButton3 = (RadioButton) findViewById(R.id.tomato_tab);
                RadioButton radioButton1 = (RadioButton) findViewById(R.id.calendar_tab);
                RadioButton radioButton2 = (RadioButton) findViewById(R.id.remainder_tab);
                RadioButton radioButton4 = (RadioButton) findViewById(R.id.settings_tab);
                switch (mViewPager.getCurrentItem()) {
                    case 0:
                        radioButton1.setChecked(true);
                        break;
                    case 1:
                        radioButton2.setChecked(true);
                        break;
                    case 3:
                        radioButton3.setChecked(true);
                        break;
                    case 4:
                        radioButton4.setChecked(true);
                        break;
                }

            }
        }
    };
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mViewPager.setCurrentItem(i,false);
                    return;
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

    }

    @Override
    public void onEventsSelected(int position){
//        RemindersItemFragment newFragment = new RemindersItemFragment();
//        Bundle args = new Bundle();
//        args.putInt(RemindersItemFragment.EVENTS_POSITION,position);
//        newFragment.setArguments(args);

 //       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

//        transaction.replace(R.id.view,mFragments.get(5));
//        transaction.addToBackStack(null);
//        transaction.commit();
        mTabRadioGroup.clearCheck();
        //mViewPager.setPageTransformer(true, new MyPageTransformer());
        TextView edit_tv = findViewById(R.id.edit_tv);
        ListView listView = findViewById(R.id.reminder_list);
        if(edit_tv.getText().equals("编辑")){
            mViewPager.setCurrentItem(5,false);
            TextView tv_back = findViewById(R.id.item_back);
            tv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(1,false);
                    System.out.println("Click Success");
                }
            });
        }else{
           // ImageView choose_img = listView.getSelectedView().findViewById(R.id.choose_img);
//            ImageView choose_img = findViewById(R.id.choose_img);
//            choose_img.setImageAlpha(R.drawable.radio_selected);
        }


    }



}