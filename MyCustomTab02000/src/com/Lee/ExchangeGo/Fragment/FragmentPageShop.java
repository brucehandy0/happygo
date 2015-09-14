package com.Lee.ExchangeGo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import com.Lee.ExchangeGo.GoodsPageActivity;
import com.Lee.ExchangeGo.R;
import com.Lee.ExchangeGo.UserData.Myuser;
import com.Lee.ExchangeGo.UserData.UserGoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentPageShop extends Fragment{
     private View rootView;

    private ListView lv_goods;
    private TextView tv_tittle,tv_content,tv_price;
    private List<Map<String,Object>> list;
    private Map<String,Object> map;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_shop, null);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }



        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       /*事件监听
               ......*/
        lv_goods= (ListView) getActivity().findViewById(R.id.goodlist);
      /*  tv_tittle= (TextView) getActivity().findViewById(R.id.item_tv_tittle);
        tv_content= (TextView) getActivity().findViewById(R.id.item_tv_content);*/


        BmobQuery<UserGoods> query=new BmobQuery<UserGoods>();
        query.findObjects(getActivity(),new FindListener<UserGoods>() {
            @Override
            public void onSuccess(List<UserGoods> userGoodses) {
                list=new ArrayList<Map<String, Object>>();
                for(UserGoods u:userGoodses){
                    map=new HashMap<String, Object>();
                    map.put("nickename",u.getNickname());
                    map.put("tittle",u.getGood_tittle());
                    map.put("content",u.getGood_content());
                    map.put("price",u.getGood_price());
                    list.add(0,map);

                    //添加适配器
                    SimpleAdapter adapter=new SimpleAdapter(getActivity(),list,R.layout.list_item,new String[]{"tittle","content","price","nickname"},new int[]{R.id.item_tv_tittle,R.id.item_tv_content,R.id.list_item_price,R.id.list_item_username});
                    lv_goods.setAdapter(adapter);

                    //item点击事件
                    lv_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            HashMap<String,String> map= (HashMap<String, String>) lv_goods.getItemAtPosition(position);
                            String tittle=map.get("tittle");
                            String content=map.get("content");
                            String nickname=map.get("nickname");

                            //点击之后跳转到....
                            GoodsPageActivity good=new GoodsPageActivity();
                            Intent i=new Intent(getActivity(),GoodsPageActivity.class);
                            //Activity之间传递参数
                            i.putExtra("tittle",tittle);
                            i.putExtra("content",content);
                            i.putExtra("nickname",nickname);
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onError(int i, String s) {
            }
        });

    }
}