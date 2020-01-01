package com.example.driver.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.driver.Bean.Infos;
import com.example.driver.custom.ImageBannerFramLayout;
import com.example.driver.R;
import com.example.driver.activity.NewsActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZixunFragment extends Fragment {
    private ImageBannerFramLayout mGroup;
    private int[] ids = new int[]{
            R.drawable.aaa1,//图片资源1
            R.drawable.aaa2
            ,//图片资源1
            R.drawable.aaa3,

    };
    private ListView lv;
    private FragmentActivity activity;
    private ArrayList<Infos> list;

    public ZixunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zixun, container, false);
        mGroup = view.findViewById(R.id.image_group);
        lv = view.findViewById(R.id.lv);
        activity = getActivity();
        initlunbo();
        initlv();
//        Glide.with(activity).load("http://hiphotos.baidu.com/news/crop%3D0%2C0%2C924%2C502%3Bq%3D80%3B/sign=e35f0ebf9382d158afcd03f1bd3a35e8/b219ebc4b74543a9b070729d11178a82b9011473.jpg").into()

        return view;
    }

    private void initlv() {
        list = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Document document = Jsoup.connect("https://news.baidu.com/").get();
                    String attr = document.select("div.imgview").select("a").attr("href");
                    Elements elements = document.select("div.mod-tab-content");
                    Elements li = elements.select("a");
                    for (int i = 0; i < li.size(); i++) {
                        String title = li.get(i).text();
                        String href = li.get(i).attr("href");
//                        Log.d("new", href);
                        Infos infos = new Infos();
                        infos.setTitle(title + "");
                        infos.setUrl(href+"");
                        list.add(infos);
//                        Log.d("aa",list.get(0).getTitle()+"");
                    }
//                    Toast.makeText(activity, , Toast.LENGTH_SHORT).show();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lv.setAdapter(new BaseAdapter() {

                                class ViewHolder {
                                    public View rootView;
                                    public TextView tvtitle;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.tvtitle = (TextView) rootView.findViewById(R.id.tvtitle);
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
                                    convertView = LayoutInflater.from(activity).inflate(R.layout.item_new, parent, false);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.tvtitle.setText(list.get(position).getTitle()+"");
                                    return convertView;
                                }
                            });
                        }
                    });



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(activity, position+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, NewsActivity.class);
                String url = list.get(position).getUrl();
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });

    }

    private void initlunbo() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;


        List<Bitmap> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ids[i]);
            list.add(bitmap);
        }
        mGroup.addBitmaps(list);
    }


}
