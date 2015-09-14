package com.Lee.ExchangeGo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import com.Lee.ExchangeGo.Fragment.FragmentPageDeliver;
import com.Lee.ExchangeGo.Fragment.FragmentPageHome;
import com.Lee.ExchangeGo.Fragment.FragmentPageMy;
import com.Lee.ExchangeGo.Fragment.FragmentPageShop;

/**
 * @author yangyu
 *	�����������Զ���TabHost
 */
public class MainTabActivity extends FragmentActivity{	
	private FragmentTabHost mTabHost;
	
	private LayoutInflater layoutInflater;
    /*页面集合*/
	private Class fragmentArray[] = {FragmentPageHome.class,FragmentPageShop.class,FragmentPageDeliver.class,FragmentPageMy.class};
	
	/*tab图片集合*/
    private int mImageViewArray[] = {R.drawable.tab_home_btn,R.drawable.tab_message_btn,R.drawable.tab_selfinfo_btn,
									 R.drawable.tab_square_btn};
	
	private String mTextviewArray[] = {"首页", "逛逛", "发布", "我的"};
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_layout);
        
        initView();
    }
	 
	/**
	 * ��ʼ�����
	 */
	private void initView(){
		layoutInflater = LayoutInflater.from(this);
				
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);	
		
		int count = fragmentArray.length;
				
		for(int i = 0; i < count; i++){
        TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
        mTabHost.addTab(tabSpec, fragmentArray[i], null);
        mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
    }
}
				
	private View getTabItemView(int index){
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);
	
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);
		
		TextView textView = (TextView) view.findViewById(R.id.textview);		
		textView.setText(mTextviewArray[index]);
	
		return view;
	}
}
