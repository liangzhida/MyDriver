package com.example.driver.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.driver.R;
import com.example.driver.activity.MoniActivity;
import com.example.driver.activity.ShunxuActivity;
import com.example.driver.activity.SuijiAcitivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private FragmentActivity activity;
    private LinearLayout llshunxu;
    private LinearLayout llmoni;
    private ImageView imgsuiji;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        activity = getActivity();
        llmoni = view.findViewById(R.id.llmoni);
        imgsuiji=view.findViewById(R.id.imgsuiji);
        llshunxu = view.findViewById(R.id.llshunxu);
        llshunxu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ShunxuActivity.class);
                startActivity(intent);
            }
        });
        imgsuiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SuijiAcitivity.class);
                startActivity(intent);
            }
        });
        llmoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MoniActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }


}
