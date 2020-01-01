package com.example.driver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.driver.R;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
        edt = (EditText) findViewById(R.id.edt);
        edt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
               /* Intent intent = new Intent();
                // 获取用户计算后的结果
                int three = Integer.parseInt(edt.getText().toString());
                intent.putExtra("three", three); //将计算的值回传回去
                // 通过intent对象返回结果，必须要调用一个setResult方法，
                // setResult(888, data);第一个参数表示结果返回码，一般只要大于1就可以
                setResult(2, intent);
                finish(); //结束当前的activity的生命周期*/
                Intent intent = new Intent();
                intent.putExtra("msg","1");
                setResult(111, intent);
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String edtString = edt.getText().toString().trim();
        if (TextUtils.isEmpty(edtString)) {
            Toast.makeText(this, "edtString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
