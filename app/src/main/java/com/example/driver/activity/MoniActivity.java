package com.example.driver.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.driver.Bean.Info;
import com.example.driver.Http.HttpUtils;
import com.example.driver.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class MoniActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgfanhui;
    private ListView lv;
    private int score = 0;
    private Button btntijiao;
    private LinearLayout content;
    private LinearLayout ll1;
    private TextView tvscore;
    private int answer=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moni);
        initView();
        initlv();
    }

    private void initlv() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = HttpUtils.get1();
                    final Info info = new Gson().fromJson(s, Info.class);
                    final List<Info.ResultBean> result = info.getResult();
                   /* for (int i = 0; i <result.size() ; i++) {
                        if (result.size()>100){
                        result.remove(i);
                        }
                    }*/

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lv.setAdapter(new BaseAdapter() {

                                class ViewHolder {
                                    public View rootView;
                                    public TextView tvtitle;
                                    public ImageView imgshow;
                                    public RadioButton rb_a;
                                    public RadioButton rb_b;
                                    public RadioButton rb_c;
                                    public RadioButton rb_d;
                                    public RadioGroup rg;
                                    public TextView tvnum;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.tvtitle = (TextView) rootView.findViewById(R.id.tvtitle);
                                        this.tvnum = (TextView) rootView.findViewById(R.id.tvnum);
                                        this.imgshow = (ImageView) rootView.findViewById(R.id.imgshow);
                                        this.rb_a = (RadioButton) rootView.findViewById(R.id.rb_a);
                                        this.rb_b = (RadioButton) rootView.findViewById(R.id.rb_b);
                                        this.rb_c = (RadioButton) rootView.findViewById(R.id.rb_c);
                                        this.rb_d = (RadioButton) rootView.findViewById(R.id.rb_d);
                                        this.rg = (RadioGroup) rootView.findViewById(R.id.rg);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return 100;
                                }

                                @Override
                                public Object getItem(int position) {
                                    return result.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(final int position, View convertView, ViewGroup parent) {
                                    convertView = LayoutInflater.from(MoniActivity.this).inflate(R.layout.item_moni, parent, false);
                                    final ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.tvtitle.setText(info.getResult().get(position).getQuestion() + "");
                                    viewHolder.rb_a.setText("A: " + info.getResult().get(position).getItem1() + "");
                                    viewHolder.rb_b.setText("B: " + info.getResult().get(position).getItem2() + "");
                                    if (info.getResult().get(position).getItem3().equals("")) {
                                        viewHolder.rb_c.setVisibility(View.GONE);
                                        viewHolder.rb_d.setVisibility(View.GONE);

                                    } else {
                                        viewHolder.rb_c.setVisibility(View.VISIBLE);
                                        viewHolder.rb_d.setVisibility(View.VISIBLE);
                                        viewHolder.rb_c.setText("C: " + info.getResult().get(position).getItem3() + "");
                                        viewHolder.rb_d.setText("D: " + info.getResult().get(position).getItem4() + "");
                                    }
                                    if (info.getResult().get(position).getUrl().equals("")) {
                                        viewHolder.imgshow.setVisibility(View.GONE);
                                    } else {
                                        viewHolder.imgshow.setVisibility(View.VISIBLE);
                                        Glide.with(MoniActivity.this).load(info.getResult().get(position).getUrl() + "").into(viewHolder.imgshow);
                                    }
                                    viewHolder.tvnum.setText(position + 1 + ".");
                                    if (result.get(position).getAnswer().equals("1")) {
                                        score=answer+1;
                                       Log.d("score",score+"");
//                                                        Toast.makeText(MoniActivity.this, "成绩", Toast.LENGTH_SHORT).show();
                                    }
//                                    tvscore.setText(score);

                                    return convertView;
                                }
                            });

                            /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    if (result.get(position).getAnswer().equals("1")) {

                                    }
                                }
                            });*/
                          /*  btntijiao.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    lv.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                            int count = radioGroup.getChildCount();
                                            if (result.get(position1).getAnswer().equals("1")) {
                                                score++;
//                                                        Toast.makeText(MoniActivity.this, "成绩", Toast.LENGTH_SHORT).show();
                                                tvscore.setText();
                                            }
                                        }
                                    });
                                }
                            });*/

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initView() {
        imgfanhui = (ImageView) findViewById(R.id.imgfanhui);
        lv = (ListView) findViewById(R.id.lv);
        imgfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btntijiao = (Button) findViewById(R.id.btntijiao);
        btntijiao.setOnClickListener(this);
        content = (LinearLayout) findViewById(R.id.content);
        content.setOnClickListener(this);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll1.setOnClickListener(this);
        tvscore = (TextView) findViewById(R.id.tvscore);
        tvscore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btntijiao:

                break;
        }
    }
}
