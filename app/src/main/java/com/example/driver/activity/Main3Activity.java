package com.example.driver.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.driver.MainActivity;
import com.example.driver.R;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                resetSprfMain();
                Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                startActivity(intent);
                Main3Activity.this.finish();

                break;
        }
    }

    private void resetSprfMain() {
        sprfMain= PreferenceManager.getDefaultSharedPreferences(this);
        editorMain=sprfMain.edit();
        editorMain.putBoolean("main",false);
        editorMain.commit();


    }
}
