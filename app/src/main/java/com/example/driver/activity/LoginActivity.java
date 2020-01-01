package com.example.driver.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.driver.Bean.Bean;
import com.example.driver.R;
import com.example.driver.custom.BeanDaoUtils;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText edtname;
    private EditText edtpsw;
    private CheckBox cbrember;
    private Button btndenglu;
    private Button btnregister;
    private BeanDaoUtils beanDaoUtils=new BeanDaoUtils(LoginActivity.this);
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferences1;
    private boolean isHideFirst;
    private SharedPreferences.Editor mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        init();

    }

    private void init() {



    }


    private void initView() {
        edtname = (EditText) findViewById(R.id.edtname);
        edtpsw = (EditText) findViewById(R.id.edtpsw);
        cbrember = (CheckBox) findViewById(R.id.cbrember);
        btndenglu = (Button) findViewById(R.id.btndenglu);
        btnregister = (Button) findViewById(R.id.btnregister);

        btndenglu.setOnClickListener(this);
        btnregister.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("lz", MODE_PRIVATE);
        mEdit = sharedPreferences.edit();

        sharedPreferences1 = getSharedPreferences("position", Context.MODE_PRIVATE);
        boolean ismain_check_remember = sharedPreferences.getBoolean("isjzmm", false);
        String username = sharedPreferences.getString("phone", "");
        String password = sharedPreferences.getString("pwd", "");
        if (ismain_check_remember) {
            cbrember.setChecked(true);
            edtname.setText(username);
            edtpsw.setText(password);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btndenglu:
                /*Intent intent = new Intent();
                intent.putExtra("msg","1");
                setResult(111, intent);
                finish();*/
                String name = edtname.getText().toString().trim();
                String psw = edtpsw.getText().toString().trim();

                if (cbrember.isChecked()) {
                    mEdit.putString("phone", name);
                    mEdit.putString("pwd", psw);
                    mEdit.putBoolean("isjzmm", true);
                    mEdit.commit();
                } else {
                    mEdit.putString("phone", "");
                    mEdit.putString("pwd", "");
                    mEdit.putBoolean("isjzmm", false);
                    mEdit.commit();
                }

                if (name.equals("")){
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                }else if (psw.equals("")){
                    Toast.makeText(this, "请输入的密码", Toast.LENGTH_SHORT).show();
                }else {
                    List<Bean> beans = beanDaoUtils.queryAllBean();
                    int aa=0;
                    for (Bean bean : beans) {
                        if(bean.getName().equals(name)&&bean.getPsw().equals(psw)){
//                            System.out.println("对象:"+people.getName());
                            Intent intent = new Intent();
                            intent.putExtra("msg","1");
                            intent.putExtra("name",name);
                            setResult(111, intent);
                            SharedPreferences.Editor edit = sharedPreferences1.edit();
                            edit.putString("position","1");
                            edit.putString("name",name);
                            edit.commit();
                            finish();
//                            Toast.makeText(this, bean.getName()+"", Toast.LENGTH_SHORT).show();
                        }else {
                            if (aa==0){
                                aa=1;
                                Toast.makeText(this, "账号密码不正确", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                   /* if (beans.get(0).getName().equals(name)){
                        finish();
                    }else {
                        Toast.makeText(this, "账号密码不正确", Toast.LENGTH_SHORT).show();
                    }*/

                }


                break;
            case R.id.btnregister:
                Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;
        }
    }


}
