package com.Lee.ExchangeGo.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;
import com.Lee.ExchangeGo.R;
import com.Lee.ExchangeGo.UserData.Myuser;
import com.Lee.ExchangeGo.UserData.UserGoods;

public class FragmentPageHome extends Fragment{
     private View rootView;
    private TextView tv_test;
    private Button btn_test;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {



        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_home, null);
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

    }

}
