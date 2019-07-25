package com.example.chronus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RemindersFragment extends Fragment {
    private static final String ARG_SHOW_TEXT = "text";
    private  String mContentText;

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
        View view = inflater.inflate(R.layout.layout_reminders,container, false);
        //ImageView imageView = view.findViewById(R.id.imageView);
        String agrs1="text";
        if (getArguments() != null){
            Bundle bundle = getArguments();
            agrs1 = bundle.getString(ARG_SHOW_TEXT);
        }

        TextView tv = view.findViewById(R.id.text);
        tv.setText(agrs1);
        return view;
    }
}
