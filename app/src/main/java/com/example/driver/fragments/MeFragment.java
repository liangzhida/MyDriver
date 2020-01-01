package com.example.driver.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.driver.R;
import com.example.driver.activity.LoginActivity;
import com.example.driver.activity.Main4Activity;
import com.example.driver.activity.Main5Activity;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {
    private int a=0;
    private int b=1;


    private TextView tvdenglu;
    private TextView llbuttom;
   private FragmentActivity activity;
   private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mEdit;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
       activity= getActivity();
        llbuttom=view.findViewById(R.id.llbuttom);
        tvdenglu=view.findViewById(R.id.tvdenglu);
//        sharedPreferences = getSharedPreferences("lz", MODE_PRIVATE);

        tvdenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   /* Intent intent = new Intent(activity, LoginActivity.class);
                    intent.putExtra("name","");
                    startActivityForResult(intent,888);
*/
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        sharedPreferences=getActivity().getSharedPreferences("position",MODE_PRIVATE);
        String position = sharedPreferences.getString("position", "");
        String name = sharedPreferences.getString("name", "");

        if (position .equals("1")){
            tvdenglu.setText(name+"");
        }else {
            tvdenglu.setText("未登录");

        }
        Log.d("i111",name);




        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if (resultCode==888&&requestCode==0){
            //处理回调相关操作
            String msg = data.getStringExtra("msg");
            tvdenglu.setText(msg+"324234");

        }*/
      /*  sharedPreferences=getActivity().getSharedPreferences("position",MODE_PRIVATE);
        String position = sharedPreferences.getString("position", "");
        if (backData .equals(position)){
            tvdenglu.setText(name);
        }else {
            tvdenglu.setText("未登录");

        }*/

        if (resultCode == 111) {
            String backData = data.getStringExtra("msg");
            String name = data.getStringExtra("name");

            /*if (backData .equals(position)){
                tvdenglu.setText(name);
            }else {
               tvdenglu.setText("未登录");

            }*/
        }


    }
    @Override
    public void onResume() {
        super.onResume();

    }



}
