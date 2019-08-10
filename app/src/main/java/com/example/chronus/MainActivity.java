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
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , RemindersFragment.OnTitleSelectedListener{

    private ViewPager mViewPager;
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
                mViewPager.setCurrentItem(2);
                mTabRadioGroup.clearCheck();
                return;
            }
        });


    }
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {


           // RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(position);
            //radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };

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
        mViewPager.setCurrentItem(5);

    }
    private int mCurItem;
    class MyPageTransformer implements ViewPager.PageTransformer {
        private final float MIN_SCALE = 0.5f;
        private final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(@NonNull View page, float position) {
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            float alphaFactor = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - Math.abs(position));
            page.setScaleY(scaleFactor);
            page.setAlpha(alphaFactor);

        }

    }


}