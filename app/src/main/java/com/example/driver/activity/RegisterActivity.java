package com.example.driver.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.driver.Bean.Bean;
import com.example.driver.MainActivity;
import com.example.driver.R;
import com.example.driver.custom.BeanDaoUtils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtname;
    private EditText edtpsw;
    private EditText edtpsw2;
    private Button btndenglu;
    private Button btnregister;
    private BeanDaoUtils beanDaoUtils=new BeanDaoUtils(RegisterActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        edtname = (EditText) findViewById(R.id.edtname);
        edtpsw = (EditText) findViewById(R.id.edtpsw);
        edtpsw2 = (EditText) findViewById(R.id.edtpsw2);
        btndenglu = (Button) findViewById(R.id.btndenglu);
        btnregister = (Button) findViewById(R.id.btnregister);

        btndenglu.setOnClickListener(this);
        btnregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btndenglu:
                finish();
                break;
            case R.id.btnregister:
                String name = edtname.getText().toString();
                String psw = edtpsw.getText().toString();
                String psw2 = edtpsw2.getText().toString();
                if (name.equals("")){
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                }else if (psw.equals("")){
                    Toast.makeText(this, "请输入正确的密码", Toast.LENGTH_SHORT).show();
                }else if (psw.equals(psw2)){
//                    beanDaoUtils.insertBean(new Bean(null,name,psw));
                    Bean bean = new Bean();
                    bean.set_id(null);
                    bean.setName(name);
                    bean.setPsw(psw);
                    beanDaoUtils.insertBean(bean);
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(this, "再次密码不正确", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void submit() {
        // validate
        String edtnameString = edtname.getText().toString().trim();
        if (TextUtils.isEmpty(edtnameString)) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }

        String edtpswString = edtpsw.getText().toString().trim();
        if (TextUtils.isEmpty(edtpswString)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String edtpsw2String = edtpsw2.getText().toString().trim();
        if (TextUtils.isEmpty(edtpsw2String)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
