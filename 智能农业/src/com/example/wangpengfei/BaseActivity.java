package com.example.wangpengfei;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends FragmentActivity {
//�����˻���
	ClientApp mApp;
	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//ȡ��������
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//���ô���ȫ��
		
		mApp=(ClientApp)this.getApplication();
		
		//����ClientApp���е�setActivity( )��������activity����ȥ1
		mApp.setActivity(this);
	}
	

}
