package com.example.chronus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewFragment extends Fragment {
private static final String ARG_SHOW_TEXT = "text";
    public ViewFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.layout_fragment,null);
        TextView textView = view.findViewById(R.id.text);
        textView.setText("alert");
        return view;
    }
    public static Fragment newInstance(String param1){
        Fragment fragment = new Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          //  mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }
}
