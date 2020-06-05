package com.example.wangpengfei;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends BaseActivity {
	Handler mhandler = new Handler();
	Runnable mrunnable;

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		this.setContentView(R.layout.splash_activity_layout);// 暂时没有界面
		setrunnable();
		mhandler.postDelayed(mrunnable, 3000);
	}

	void setrunnable() {
		mrunnable = new Runnable() {

			@Override
			public void run() {
				if (mApp.getIsShowSlideScreen() == 1) {// 如果为0，则直接进入登录窗口
					Intent mintent1= new Intent(SplashActivity.this,
							GuideScreenActivity.class);
					
					SplashActivity.this.startActivity(mintent1);
					
					
					SplashActivity.this.finish();
					
				} else  if (mApp.getIsShowSlideScreen() == 0) {// 如果为1，则直接进入滑屏界面

					Intent mintent2= new Intent(SplashActivity.this,
							UserLoginActivity.class);
					
					SplashActivity.this.startActivity(mintent2);
					SplashActivity.this.finish();

				}
			}
		};

	}

}
