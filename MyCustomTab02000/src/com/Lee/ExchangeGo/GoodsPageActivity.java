package com.Lee.ExchangeGo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UploadFileListener;
import com.Lee.ExchangeGo.UserData.Myuser;
import com.Lee.ExchangeGo.UserData.UserGoods;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2015/8/1.
 */
public class GoodsPageActivity extends Activity implements View.OnClickListener{
    private TextView tv_tittle,tv_content,tv_price,tv_nickname;
    private Button btn_collection;
    private ImageView iv_connectbyqq;
    static int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_good_infor);

         init();

    }










    private void init(){
        tv_tittle= (TextView) findViewById(R.id.page_goodinfor_tittle);
        tv_content= (TextView) findViewById(R.id.page_goodinfor_content);
        tv_price= (TextView) findViewById(R.id.page_goodinfor_price);
        tv_nickname= (TextView) findViewById(R.id.page_goodinfor_owner_name);
        btn_collection= (Button) findViewById(R.id.page_goodinfor_btn_like);
        iv_connectbyqq= (ImageView) findViewById(R.id.page_goodinfor_pic_connectby_qq);

        //获取上一个Activity传递来的参数
        Intent i=getIntent();
        String tittle=i.getStringExtra("tittle");
        String content=i.getStringExtra("content");
        String nickname=i.getStringExtra("nickname");


        BmobQuery<UserGoods> query=new BmobQuery<UserGoods>();
        query.addWhereEqualTo("good_tittle",tittle);
        query.addWhereEqualTo("good_content",content);
        query.addWhereEqualTo("nickname",nickname);
        query.findObjects(this,new FindListener<UserGoods>() {
            @Override
            public void onSuccess(List<UserGoods> userGoodses) {
                for (UserGoods u : userGoodses) {
                    tv_nickname.setText(u.getNickname());
                    tv_tittle.setText(u.getGood_tittle());
                    tv_content.setText(u.getGood_content());
                    tv_price.setText(u.getGood_price().toString()+"/元");
                    btn_collection.setOnClickListener(GoodsPageActivity.this);
                    iv_connectbyqq.setOnClickListener(GoodsPageActivity.this);

                }
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(getApplicationContext(),"error"+s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
         if (v==btn_collection){
             if(count==1){
                 btn_collection.setText("已收藏");
                 BmobQuery<UserGoods> query=new BmobQuery<UserGoods>();
                 query.addWhereEqualTo("good_tittle",tv_tittle.getText().toString());
                 query.addWhereEqualTo("good_content",tv_content.getText().toString());
                 query.findObjects(this,new FindListener<UserGoods>() {
                     @Override
                     public void onSuccess(List<UserGoods> userGoodses) {
                         for(UserGoods u:userGoodses){
                             u.increment("collectnumber");
                             u.update(getApplicationContext());
                         }
                     }

                     @Override
                     public void onError(int i, String s) {

                     }
                 });


                 count=0;
             }
             else if(count==0){
                 btn_collection.setText("收藏");
                 UserLikes();
                 BmobQuery<UserGoods> query=new BmobQuery<UserGoods>();
                 query.addWhereEqualTo("good_tittle",tv_tittle.getText().toString());
                 query.addWhereEqualTo("good_content",tv_content.getText().toString());
                 query.findObjects(this,new FindListener<UserGoods>() {
                     @Override
                     public void onSuccess(List<UserGoods> userGoodses) {
                         for(UserGoods u:userGoodses){
                             u.setCollectnumber(u.getCollectnumber()-1);
                             u.update(getApplicationContext());
                         }
                     }

                     @Override
                     public void onError(int i, String s) {

                     }
                 });
                 Toast.makeText(getApplicationContext(),"收藏已取消",Toast.LENGTH_SHORT).show();
                 count=1;
             }
         }
        else if(v==iv_connectbyqq){
             String url="mqqwpa://im/chat?chat_type=wpa&uin=543200890";
             startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
         }
    }


    public void UserLikes(){
        Myuser user = BmobUser.getCurrentUser(this, Myuser.class);
        UserGoods good = new UserGoods();
        good.setGood_tittle(tv_tittle.getText().toString());
        good.setGood_content(tv_content.getText().toString());
//将当前用户添加到UserGoods表中的likes字段值中，表明当前用户喜欢该good
        BmobRelation relation = new BmobRelation();
//将当前用户添加到多对多关联中
        relation.add(user);
//多对多关联指向`good`的`likes`字段
        good.setLikes(relation);
        good.update(this);
    }
}
