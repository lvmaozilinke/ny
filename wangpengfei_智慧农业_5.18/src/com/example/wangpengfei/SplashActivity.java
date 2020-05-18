package com.example.wangpengfei;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends BaseActivity {
	Handler mhandler=new Handler();
	Runnable mrunnable;

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		this.setContentView(R.layout.splash_activity_layout);//暂时没有界面
		setrunnable();
		mhandler.postDelayed(mrunnable, 3000);
	}
	void setrunnable(){
		mrunnable=new Runnable() {
			
			@Override
			public void run() {

				Intent mintent=new Intent(SplashActivity.this,UserLoginActivity.class);
				SplashActivity.this.startActivity(mintent);
				SplashActivity.this.finish();
				
			}
		};
		
	}
	

}
