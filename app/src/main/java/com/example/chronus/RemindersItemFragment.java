package com.example.chronus;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RemindersItemFragment extends Fragment {
    int mCurrentPosition = -1;
    final static String EVENTS_POSITION = "position";
    private static final String ARG_SHOW_TEXT = "text";
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
        View v = inflater.inflate(R.layout.layout_reminders_item,container,false);
        return  v;
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
}
