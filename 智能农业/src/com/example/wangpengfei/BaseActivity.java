package com.example.wangpengfei;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends FragmentActivity {
//创建了基类
	ClientApp mApp;
	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//设置窗口全屏
		
		mApp=(ClientApp)this.getApplication();
		
		//调用ClientApp类中的setActivity( )方法。把activity传过去1
		mApp.setActivity(this);
	}
	

}
