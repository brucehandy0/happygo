package com.Lee.ExchangeGo.UserData;

import android.content.Context;
import android.widget.TextView;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.FindListener;

import java.util.List;

/**
 * Created by Administrator on 2015/7/30.
 */
public class UserGoods  extends BmobObject{
    private Myuser user;
    private String nickname;
    private String head_image;
    private String  good_tittle;
    private String  good_content;
    private Integer good_price;
    private Integer collectnumber=0;
    private Boolean saled=false;

    public BmobRelation getLikes() {
        return likes;
    }

    public void setLikes(BmobRelation likes) {
        this.likes = likes;
    }

    private BmobRelation likes;

    public Myuser getUser() {
        return user;
    }

    public void setUser(Myuser user) {
        this.user = user;
    }

    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }

    public String getGood_tittle() {
        return good_tittle;
    }

    public void setGood_tittle(String good_tittle) {
        this.good_tittle = good_tittle;
    }

    public String getGood_content() {
        return good_content;
    }

    public void setGood_content(String good_content) {
        this.good_content = good_content;
    }

    public Integer getGood_price() {
        return good_price;
    }

    public void setGood_price(Integer good_price) {
        this.good_price = good_price;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getSaled() {
        return saled;
    }

    public void setSaled(Boolean saled) {
        this.saled = saled;
    }

    public Integer getCollectnumber() {
        return collectnumber;
    }

    public void setCollectnumber(Integer collectnumber) {
        this.collectnumber = collectnumber;
    }


  /*  public void getCurrentgood(Context context,UserGoods good,TextView tv_tittle, TextView tv_content){
        BmobQuery<UserGoods> query =new BmobQuery<UserGoods>();
        query.addWhereEqualTo("good_tittle",tv_tittle.getText().toString());
        query.addWhereEqualTo("good_content",tv_content.getText().toString());
        query.findObjects(context,new FindListener<UserGoods>() {


            @Override
            public void onSuccess(List<UserGoods> userGoodses) {
                for (good:userGoodses){

                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }*/

}
