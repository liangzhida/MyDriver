package com.example.driver;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.driver.fragments.MainFragment;
import com.example.driver.fragments.MeFragment;
import com.example.driver.fragments.ZixunFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private LinearLayout content;
    private RadioButton rb_jiakao;
    private RadioButton rb_zixun;
    private RadioButton rb_me;
    private RadioGroup rg;
    private List<Fragment>fragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
        initpic();
        initviewpager();
    }

    private void initviewpager() {
        fragments.add(new MainFragment());
        fragments.add(new ZixunFragment());
        fragments.add(new MeFragment());

        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        /*viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        rg.check(R.id.rb_jiakao);
                        break;
                    case 1:
                        rg.check(R.id.rb_zixun);
                        break;
                    case 2:
                        rg.check(R.id.rb_me);
                        break;

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_jiakao:
                        viewpager.setCurrentItem(0,false);
                        break;
                    case R.id.rb_zixun:
                        viewpager.setCurrentItem(1,false);
                        break;
                    case R.id.rb_me:
                        viewpager.setCurrentItem(2,false);
                        break;
                }
            }
        });

    }

    private void initpic() {



    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        content = (LinearLayout) findViewById(R.id.content);
        rb_jiakao = (RadioButton) findViewById(R.id.rb_jiakao);
        rb_zixun = (RadioButton) findViewById(R.id.rb_zixun);
        rb_me = (RadioButton) findViewById(R.id.rb_me);
        rg = (RadioGroup) findViewById(R.id.rg);
    }
}
