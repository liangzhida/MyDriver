package com.example.driver.activity;

import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.driver.Bean.Comment;
import com.example.driver.Bean.Info;
import com.example.driver.Http.HttpUtils;
import com.example.driver.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShunxuActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tvnum;
    private TextView tvtitle;

    private TextView tvdaan;

    private ImageView imgfanhui;
    private ImageView imgshow;
    private ListView lv;
    private ImageView imgshouchang;
    private TextView tvduinum;
    private TextView tvcuonum;
    private Comment comment;
    private TextView tvjiexi;
    private int posion = 1;
    private RadioButton rb_a;
    private RadioButton rb_b;
    private RadioButton rb_c;
    private RadioButton rb_d;
    private RadioGroup rg;
    private LinearLayout llbuttom;
    private boolean run = false;
    private final Handler handler = new Handler();
    private Button btnprevious;
    private Button btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shunxu);
        initView();
        init1();
        init();
        initlv();
//        run = true;
//        handler.postDelayed(task, 1000);
    }

    private void init1() {
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    String s = HttpUtils.get1();
                    final Info info = new Gson().fromJson(s, Info.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            tvtitle.setText(info.getResult().get(posion).getQuestion() + "");
                            rb_a.setText("A: " + info.getResult().get(posion).getItem1() + "");
                            rb_b.setText("B: " + info.getResult().get(posion).getItem2() + "");
                            if (info.getResult().get(posion).getItem3().equals("")) {
                                rb_c.setVisibility(View.GONE);
                                rb_d.setVisibility(View.GONE);

                            } else {
                                rb_c.setVisibility(View.VISIBLE);
                                rb_d.setVisibility(View.VISIBLE);
                                rb_c.setText("C: " + info.getResult().get(posion).getItem3() + "");
                                rb_d.setText("D: " + info.getResult().get(posion).getItem4() + "");
                            }


                            tvjiexi.setText(info.getResult().get(posion).getExplains() + "");
                            if (info.getResult().get(posion).getAnswer().equals("1")) {
                                tvdaan.setText("答案:A");
                            } else if (info.getResult().get(posion).getAnswer().equals("2")) {
                                tvdaan.setText("答案:B");
                            } else if (info.getResult().get(posion).getAnswer().equals("3")) {
                                tvdaan.setText("答案:C");
                            } else {
                                tvdaan.setText("答案:D");
                            }

                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                    switch (checkedId) {
                                        case R.id.rb_a:
                                            if (info.getResult().get(posion).getAnswer().equals("1")) {
                                                posion++;
                                                Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                llbuttom.setVisibility(View.VISIBLE);
                                            }
//
                                            break;
                                        case R.id.rb_b:
                                            if (info.getResult().get(posion).getAnswer().equals("2")) {
                                                posion++;
                                                Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                llbuttom.setVisibility(View.VISIBLE);
                                            }
//                                            Toast.makeText(ShunxuActivity.this, "b", Toast.LENGTH_SHORT).show();
                                            break;
                                        case R.id.rb_c:
                                            if (info.getResult().get(posion).getAnswer().equals("3")) {
                                                Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                posion++;
                                            } else {
                                                llbuttom.setVisibility(View.VISIBLE);
                                            }
//                                            Toast.makeText(ShunxuActivity.this, "c", Toast.LENGTH_SHORT).show();
                                            break;
                                        default:
                                            if (info.getResult().get(posion).getAnswer().equals("4")) {
                                                Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                posion++;
                                            } else {
                                                llbuttom.setVisibility(View.VISIBLE);
                                            }
//                                            Toast.makeText(ShunxuActivity.this, "d", Toast.LENGTH_SHORT).show();
                                            break;

                                    }

                                }
                            });


                            if (info.getResult().get(posion).getUrl().equals("")) {
                                imgshow.setVisibility(View.GONE);
                            } else {
                                imgshow.setVisibility(View.VISIBLE);
                                Glide.with(ShunxuActivity.this).load(info.getResult().get(posion).getUrl() + "").into(imgshow);
                            }


                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initlv() {
        final List<Comment> list = new ArrayList();

        comment = new Comment();
        comment.setName("4223");
        comment.setPinglun("奥术大师大所大所多");
        comment.setDianzuan(66);
        comment.setUrl_touxiang("");
        list.add(comment);


        lv.setAdapter(new BaseAdapter() {

            class ViewHolder {
                public View rootView;
                public ImageView img_touxiang;
                public TextView tvname;
                public TextView tvdianzuannum;
                public TextView tvcomment;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.img_touxiang = (ImageView) rootView.findViewById(R.id.img_touxiang);
                    this.tvname = (TextView) rootView.findViewById(R.id.tvname);
                    this.tvdianzuannum = (TextView) rootView.findViewById(R.id.tvdianzuannum);
                    this.tvcomment = (TextView) rootView.findViewById(R.id.tvcomment);
                }

            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(ShunxuActivity.this).inflate(R.layout.item_comment, parent, false);
                ViewHolder viewHolder = new ViewHolder(convertView);
                viewHolder.tvcomment.setText(list.get(position).getPinglun() + "");
                viewHolder.tvname.setText(list.get(position).getName() + "");
                viewHolder.tvdianzuannum.setText(list.get(position).getDianzuan() + "");
                return convertView;
            }
        });


    }

//    private final Runnable task = new Runnable() {
//        @Override
//        public void run() {
//            // TODO Auto-generated method stub
//            if (run) {
//
//
//
//                handler.postDelayed(this, 1000);
//            }
//        }
//    };


    private void init() {
        btnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posion--;
                llbuttom.setVisibility(View.GONE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        try {
                            String s = HttpUtils.get1();
                            final Info info = new Gson().fromJson(s, Info.class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    tvtitle.setText(info.getResult().get(posion).getQuestion() + "");
                                    rb_a.setText("A: " + info.getResult().get(posion).getItem1() + "");
                                    rb_b.setText("B: " + info.getResult().get(posion).getItem2() + "");
                                    if (info.getResult().get(posion).getItem3().equals("")) {
                                        rb_c.setVisibility(View.GONE);
                                        rb_d.setVisibility(View.GONE);

                                    } else {
                                        rb_c.setVisibility(View.VISIBLE);
                                        rb_d.setVisibility(View.VISIBLE);
                                        rb_c.setText("C: " + info.getResult().get(posion).getItem3() + "");
                                        rb_d.setText("D: " + info.getResult().get(posion).getItem4() + "");
                                    }

                                    tvnum.setText(posion+"/8888");
                                    tvjiexi.setText(info.getResult().get(posion).getExplains() + "");
                                    if (info.getResult().get(posion).getAnswer().equals("1")) {
                                        tvdaan.setText("答案:A");
                                    } else if (info.getResult().get(posion).getAnswer().equals("2")) {
                                        tvdaan.setText("答案:B");
                                    } else if (info.getResult().get(posion).getAnswer().equals("3")) {
                                        tvdaan.setText("答案:C");
                                    } else {
                                        tvdaan.setText("答案:D");
                                    }

                                    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                                            switch (checkedId) {
                                                case R.id.rb_a:
                                                    if (info.getResult().get(posion).getAnswer().equals("1")) {
                                                        posion++;
                                                        Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        llbuttom.setVisibility(View.VISIBLE);
                                                    }
//
                                                    break;
                                                case R.id.rb_b:
                                                    if (info.getResult().get(posion).getAnswer().equals("2")) {
                                                        posion++;
                                                        Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        llbuttom.setVisibility(View.VISIBLE);
                                                    }
//                                            Toast.makeText(ShunxuActivity.this, "b", Toast.LENGTH_SHORT).show();
                                                    break;
                                                case R.id.rb_c:
                                                    if (info.getResult().get(posion).getAnswer().equals("3")) {
                                                        Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                        posion++;
                                                    } else {
                                                        llbuttom.setVisibility(View.VISIBLE);
                                                    }
//                                            Toast.makeText(ShunxuActivity.this, "c", Toast.LENGTH_SHORT).show();
                                                    break;
                                                default:
                                                    if (info.getResult().get(posion).getAnswer().equals("4")) {
                                                        Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                        posion++;
                                                    } else {
                                                        llbuttom.setVisibility(View.VISIBLE);
                                                    }
//                                            Toast.makeText(ShunxuActivity.this, "d", Toast.LENGTH_SHORT).show();
                                                    break;

                                            }

                                        }
                                    });


                                    if (info.getResult().get(posion).getUrl().equals("")) {
                                        imgshow.setVisibility(View.GONE);
                                    } else {
                                        imgshow.setVisibility(View.VISIBLE);
                                        Glide.with(ShunxuActivity.this).load(info.getResult().get(posion).getUrl() + "").into(imgshow);
                                    }


                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llbuttom.setVisibility(View.GONE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        posion++;

                        try {
                            String s = HttpUtils.get1();
                            final Info info = new Gson().fromJson(s, Info.class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    tvtitle.setText(info.getResult().get(posion).getQuestion() + "");
                                    rb_a.setText("A: " + info.getResult().get(posion).getItem1() + "");
                                    rb_b.setText("B: " + info.getResult().get(posion).getItem2() + "");
                                    if (info.getResult().get(posion).getItem3().equals("")) {
                                        rb_c.setVisibility(View.GONE);
                                        rb_d.setVisibility(View.GONE);

                                    } else {
                                        rb_c.setVisibility(View.VISIBLE);
                                        rb_d.setVisibility(View.VISIBLE);
                                        rb_c.setText("C: " + info.getResult().get(posion).getItem3() + "");
                                        rb_d.setText("D: " + info.getResult().get(posion).getItem4() + "");
                                    }


                                    tvjiexi.setText(info.getResult().get(posion).getExplains() + "");
                                    if (info.getResult().get(posion).getAnswer().equals("1")) {
                                        tvdaan.setText("答案:A");
                                    } else if (info.getResult().get(posion).getAnswer().equals("2")) {
                                        tvdaan.setText("答案:B");
                                    } else if (info.getResult().get(posion).getAnswer().equals("3")) {
                                        tvdaan.setText("答案:C");
                                    } else {
                                        tvdaan.setText("答案:D");
                                    }

                                    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                                            switch (checkedId) {
                                                case R.id.rb_a:
                                                    if (info.getResult().get(posion).getAnswer().equals("1")) {
                                                        posion++;
                                                        Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        llbuttom.setVisibility(View.VISIBLE);
                                                    }
//
                                                    break;
                                                case R.id.rb_b:
                                                    if (info.getResult().get(posion).getAnswer().equals("2")) {
                                                        posion++;
                                                        Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        llbuttom.setVisibility(View.VISIBLE);
                                                    }
//                                            Toast.makeText(ShunxuActivity.this, "b", Toast.LENGTH_SHORT).show();
                                                    break;
                                                case R.id.rb_c:
                                                    if (info.getResult().get(posion).getAnswer().equals("3")) {
                                                        Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                        posion++;
                                                    } else {
                                                        llbuttom.setVisibility(View.VISIBLE);
                                                    }
//                                            Toast.makeText(ShunxuActivity.this, "c", Toast.LENGTH_SHORT).show();
                                                    break;
                                                default:
                                                    if (info.getResult().get(posion).getAnswer().equals("4")) {
                                                        Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                        posion++;
                                                    } else {
                                                        llbuttom.setVisibility(View.VISIBLE);
                                                    }
//                                            Toast.makeText(ShunxuActivity.this, "d", Toast.LENGTH_SHORT).show();
                                                    break;

                                            }

                                        }
                                    });


                                    if (info.getResult().get(posion).getUrl().equals("")) {
                                        imgshow.setVisibility(View.GONE);
                                    } else {
                                        imgshow.setVisibility(View.VISIBLE);
                                        Glide.with(ShunxuActivity.this).load(info.getResult().get(posion).getUrl() + "").into(imgshow);
                                    }


                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
       /* new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String s = HttpUtils.get1();
                    final Info info = new Gson().fromJson(s, Info.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            tvtitle.setText(info.getResult().get(posion).getQuestion() + "");
                            rb_a.setText("A: " + info.getResult().get(posion).getItem1() + "");
                            rb_b.setText("B: " + info.getResult().get(posion).getItem2() + "");
                            if (info.getResult().get(posion).getItem3().equals("")) {
                                rb_c.setVisibility(View.GONE);
                                rb_d.setVisibility(View.GONE);

                            } else {
                                rb_c.setVisibility(View.VISIBLE);
                                rb_d.setVisibility(View.VISIBLE);
                                rb_c.setText("C: " + info.getResult().get(posion).getItem3() + "");
                                rb_d.setText("D: " + info.getResult().get(posion).getItem4() + "");
                            }


                            tvjiexi.setText(info.getResult().get(posion).getExplains() + "");
                            if (info.getResult().get(posion).getAnswer().equals("1")) {
                                tvdaan.setText("答案:A");
                            } else if (info.getResult().get(posion).getAnswer().equals("2")) {
                                tvdaan.setText("答案:B");
                            } else if (info.getResult().get(posion).getAnswer().equals("3")) {
                                tvdaan.setText("答案:C");
                            } else {
                                tvdaan.setText("答案:D");
                            }

                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                    switch (checkedId) {
                                        case R.id.rb_a:
                                            if (info.getResult().get(posion).getAnswer().equals("1")) {
                                                posion++;
                                                Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                llbuttom.setVisibility(View.VISIBLE);
                                            }
//
                                            break;
                                        case R.id.rb_b:
                                            if (info.getResult().get(posion).getAnswer().equals("2")) {
                                                posion++;
                                                Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                llbuttom.setVisibility(View.VISIBLE);
                                            }
//                                            Toast.makeText(ShunxuActivity.this, "b", Toast.LENGTH_SHORT).show();
                                            break;
                                        case R.id.rb_c:
                                            if (info.getResult().get(posion).getAnswer().equals("3")) {
                                                Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                posion++;
                                            } else {
                                                llbuttom.setVisibility(View.VISIBLE);
                                            }
//                                            Toast.makeText(ShunxuActivity.this, "c", Toast.LENGTH_SHORT).show();
                                            break;
                                        default:
                                            if (info.getResult().get(posion).getAnswer().equals("4")) {
                                                Toast.makeText(ShunxuActivity.this, "回答正确!", Toast.LENGTH_SHORT).show();
                                                posion++;
                                            } else {
                                                llbuttom.setVisibility(View.VISIBLE);
                                            }
//                                            Toast.makeText(ShunxuActivity.this, "d", Toast.LENGTH_SHORT).show();
                                            break;

                                    }

                                }
                            });


                            if (info.getResult().get(posion).getUrl().equals("")) {
                                imgshow.setVisibility(View.GONE);
                            } else {
                                imgshow.setVisibility(View.VISIBLE);
                                Glide.with(ShunxuActivity.this).load(info.getResult().get(posion).getUrl() + "").into(imgshow);
                            }


                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }

    private void initView() {


        tvtitle = (TextView) findViewById(R.id.tvtitle);


        imgfanhui = (ImageView) findViewById(R.id.imgfanhui);
        imgfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgshow = (ImageView) findViewById(R.id.imgshow);

        lv = (ListView) findViewById(R.id.lv);

        imgshouchang = (ImageView) findViewById(R.id.imgshouchang);

        tvduinum = (TextView) findViewById(R.id.tvduinum);

        tvcuonum = (TextView) findViewById(R.id.tvcuonum);

        tvjiexi = (TextView) findViewById(R.id.tvjiexi);
        tvdaan = (TextView) findViewById(R.id.tvdaan);

        rb_a = (RadioButton) findViewById(R.id.rb_a);

        rb_b = (RadioButton) findViewById(R.id.rb_b);

        rb_c = (RadioButton) findViewById(R.id.rb_c);

        rb_d = (RadioButton) findViewById(R.id.rb_d);

        rg = (RadioGroup) findViewById(R.id.rg);

        llbuttom = (LinearLayout) findViewById(R.id.llbuttom);

        btnprevious = (Button) findViewById(R.id.btnprevious);
        btnprevious.setOnClickListener(this);
        btnnext = (Button) findViewById(R.id.btnnext);
        btnnext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnprevious:

                break;
            case R.id.btnnext:

                break;
        }
    }
}
