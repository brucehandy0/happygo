package com.Lee.ExchangeGo.UserData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.Lee.ExchangeGo.LoginActivity;
import com.Lee.ExchangeGo.R;

/**
 * Created by Administrator on 2015/9/9.
 */
public class TheFirstPage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thefirstpage);

        new Handler().postDelayed(new Runnable(){

            public void run() {

                //execute the task
                Intent i = new Intent(TheFirstPage.this, LoginActivity.class);
                startActivity(i);
                finish();
            }

        }, 1000);


    }
}
