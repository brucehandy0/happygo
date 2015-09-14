package com.Lee.ExchangeGo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;
import com.Lee.ExchangeGo.Fragment.FragmentPageMy;
import com.Lee.ExchangeGo.UserData.Myuser;

/**
 * Created by Administrator on 2015/7/26.
 */
public class PageMyInforActivity extends Activity implements View.OnClickListener{
    private TextView username,tv_nickname,tv_sex,tv_phone,tv_address,tv_update;
    private LinearLayout layout_name,layout_sex,layout_phone,layout_address;
    private EditText et_name,et_sex,et_phone,et_address;
    private Button btn_save,btn_cancle;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_my_infor);

        init();



    }

    private void init(){
        username= (TextView) findViewById(R.id.login_et_username);
        tv_nickname= (TextView) findViewById(R.id.myinfor_nickname);
        tv_sex= (TextView) findViewById(R.id.myinfor_sex);
        tv_phone= (TextView) findViewById(R.id.myinfor_phone);
        tv_address= (TextView) findViewById(R.id.myinfor_address);

        tv_update= (TextView) findViewById(R.id.page_my_infor_update);
        tv_update.setOnClickListener(this);
        iv_back= (ImageView) findViewById(R.id.page_my_infor_iv_back);
        iv_back.setOnClickListener(this);


        layout_name= (LinearLayout) findViewById(R.id.infor_layout_name);
        layout_sex= (LinearLayout) findViewById(R.id.infor_layout_sex);
        layout_phone= (LinearLayout) findViewById(R.id.infor_layout_phone);
        layout_address= (LinearLayout) findViewById(R.id.infor_layout_address);
        et_name= (EditText) findViewById(R.id.myinfor_gone_et_name);
        et_sex= (EditText) findViewById(R.id.myinfor_gone_et_sex);
        et_phone= (EditText) findViewById(R.id.myinfor_gone_et_phone);
        et_address= (EditText) findViewById(R.id.myinfor_gone_et_address);
        btn_save= (Button) findViewById(R.id.myinfor_btn_save);
        btn_cancle= (Button) findViewById(R.id.myinfor_btn_cancle);

        tv_nickname.setVisibility(View.VISIBLE);
        tv_sex.setVisibility(View.VISIBLE);
        tv_phone.setVisibility(View.VISIBLE);
        tv_address.setVisibility(View.VISIBLE);

        layout_name.setVisibility(View.GONE);
        layout_sex.setVisibility(View.GONE);;
        layout_phone.setVisibility(View.GONE);;
        layout_address.setVisibility(View.GONE);
        btn_save.setVisibility(View.GONE);
        btn_cancle.setVisibility(View.GONE);


        Myuser user= BmobUser.getCurrentUser(this,Myuser.class);
        if (user!=null){
            tv_nickname.setText("昵称    ：" + user.getNickname());
            tv_sex.setText     ("性别    ：" + user.getSex());
            tv_phone.setText   ("手机    ：" + user.getMobilePhoneNumber());
            tv_address.setText ("地址    ：" + user.getAddress());
        }
    }

    private void update_infor(){
        tv_nickname.setVisibility(View.GONE);
        tv_sex.setVisibility(View.GONE);
        tv_phone.setVisibility(View.GONE);
        tv_address.setVisibility(View.GONE);

        layout_name.setVisibility(View.VISIBLE);
        layout_sex.setVisibility(View.VISIBLE);;
        layout_phone.setVisibility(View.VISIBLE);;
        layout_address.setVisibility(View.VISIBLE);
        btn_save.setVisibility(View.VISIBLE);
        btn_cancle.setVisibility(View.VISIBLE);



        Myuser user= BmobUser.getCurrentUser(this,Myuser.class);
        if (user!=null){
            et_name.setText( user.getNickname());
            et_sex.setText(user.getSex());
            et_phone.setText( user.getMobilePhoneNumber());
            et_address.setText(user.getAddress());

        }
        btn_save.setOnClickListener(this);
        btn_cancle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==tv_update){
            update_infor();
        }
        else if (v==btn_save){
           Myuser user=Myuser.getCurrentUser(this,Myuser.class);
            user.setNickname(et_name.getText().toString());
            user.setSex(et_sex.getText().toString());
            user.setMobilePhoneNumber(et_phone.getText().toString());
            user.setAddress(et_address.getText().toString());
            user.update(getApplicationContext(),new UpdateListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getApplicationContext(),"更新成功！",Toast.LENGTH_SHORT).show();
                    init();
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(getApplicationContext(),"更新失败！"+s,Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (v==btn_cancle){
            init();
        }
        else if(v==iv_back){
            finish();
        }
    }
}
