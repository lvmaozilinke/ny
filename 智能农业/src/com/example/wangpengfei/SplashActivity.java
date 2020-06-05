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
		this.setContentView(R.layout.splash_activity_layout);// ��ʱû�н���
		setrunnable();
		mhandler.postDelayed(mrunnable, 3000);
	}

	void setrunnable() {
		mrunnable = new Runnable() {

			@Override
			public void run() {
				if (mApp.getIsShowSlideScreen() == 1) {// ���Ϊ0����ֱ�ӽ����¼����
					Intent mintent1= new Intent(SplashActivity.this,
							GuideScreenActivity.class);
					
					SplashActivity.this.startActivity(mintent1);
					
					
					SplashActivity.this.finish();
					
				} else  if (mApp.getIsShowSlideScreen() == 0) {// ���Ϊ1����ֱ�ӽ��뻬������

					Intent mintent2= new Intent(SplashActivity.this,
							UserLoginActivity.class);
					
					SplashActivity.this.startActivity(mintent2);
					SplashActivity.this.finish();

				}
			}
		};

	}

}
