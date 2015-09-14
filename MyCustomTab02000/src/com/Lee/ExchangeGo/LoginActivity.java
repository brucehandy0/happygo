package com.Lee.ExchangeGo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import com.Lee.ExchangeGo.UserData.Myuser;

public class LoginActivity extends Activity implements View.OnClickListener{
    /**
     * Called when the activity is first created.
     */
    private  final String ApplicationID="5f6f623aa3b4f6cc5cda478c70bbbbe8";
    private Button btn_login;
    private TextView tv_newuser,tv_backuser;
    private EditText username,password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);

        Bmob.initialize(getApplicationContext(), ApplicationID);


        Myuser user=BmobUser.getCurrentUser(this,Myuser.class);
        if (user!=null){
            Intent i=new Intent(LoginActivity.this,MainTabActivity.class);
            startActivity(i);
            finish();
        }


        btn_login= (Button) findViewById(R.id.login_btn_login);
        tv_newuser= (TextView) findViewById(R.id.tv_newuser);
        tv_backuser= (TextView) findViewById(R.id.tv_findpwd);
        username= (EditText) findViewById(R.id.login_et_username);
        password= (EditText) findViewById(R.id.login_et_pwd);

        btn_login.setOnClickListener(this);
        tv_newuser.setOnClickListener(this);
        tv_backuser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
          if(v==btn_login){
              Myuser user=new Myuser();
              user.setUsername(username.getText().toString());
              user.setPassword(password.getText().toString());
              user.login(this,new SaveListener() {
                  @Override
                  public void onSuccess() {
                      Toast.makeText(getApplicationContext(),"登陆成功！即将跳转",Toast.LENGTH_SHORT).show();
                      Intent i=new Intent(LoginActivity.this,MainTabActivity.class);
                      startActivity(i);
                      finish();
                  }

                  @Override
                  public void onFailure(int i, String s) {
                      Toast.makeText(getApplicationContext(),"登陆失败！"+s,Toast.LENGTH_SHORT).show();
                  }
              });
          }
        if(v==tv_newuser){
            Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(i);
        }

    }
}
