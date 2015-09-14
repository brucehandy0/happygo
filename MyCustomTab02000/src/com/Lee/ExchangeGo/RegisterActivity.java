package com.Lee.ExchangeGo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;
import com.Lee.ExchangeGo.UserData.Myuser;

/**
 * Created by Administrator on 2015/7/24.
 */
public class RegisterActivity extends Activity {
    private EditText et_username,et_nickname,et_sex,et_phone,et_password,et_address;
    private Button btn_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_register);

        et_username= (EditText) findViewById(R.id.reg_et_username);
        et_nickname= (EditText) findViewById(R.id.reg_et_nickname);
        et_sex= (EditText) findViewById(R.id.reg_et_sex);
        et_phone= (EditText) findViewById(R.id.reg_et_phone);
        et_password= (EditText) findViewById(R.id.reg_et_password);
        et_address= (EditText) findViewById(R.id.reg_et_address);
        btn_reg= (Button) findViewById(R.id.reg_btn_register);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Myuser user=new Myuser();
                user.setUsername(et_username.getText().toString());
                user.setNickname(et_nickname.getText().toString());
                user.setSex(et_sex.getText().toString());
                user.setMobilePhoneNumber(et_phone.getText().toString());
                user.setPassword(et_password.getText().toString());
                user.setAddress(et_address.getText().toString());
                user.signUp(RegisterActivity.this,new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(),"注册成功!",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent();
                        i.setClass(RegisterActivity.this,LoginActivity.class);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(getApplicationContext(),"注册失败!!"+s,Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
