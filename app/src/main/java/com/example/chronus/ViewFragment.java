package com.example.chronus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewFragment extends Fragment {
    private static final String ARG_SHOW_TEXT = "text";
    private  String mContentText;

    public ViewFragment() {
        // Required empty public constructor
    }


    public static ViewFragment newInstance(String param1){
        ViewFragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.layout_fragment, container, false);
        TextView contentTv = rootView.findViewById(R.id.text);
        contentTv.setText(mContentText);
        ImageView imageView = rootView.findViewById(R.id.imageView2);


        return rootView;
    }
}
