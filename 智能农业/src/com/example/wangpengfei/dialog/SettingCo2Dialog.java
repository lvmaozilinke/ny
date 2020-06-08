package com.example.wangpengfei.dialog;

import com.example.wangpengfei.ClientApp;
import com.example.wangpengfei.R;
import com.example.wangpengfei.bean.SensorConfigBean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("ValidFragment")
public class SettingCo2Dialog extends BaseDialog {
	Context mcontext;
	ClientApp mApp;
	EditText  mMax_Co2_Et,mMin_Co2_Et;
	Button    mCo2Setting_btn;//2.创建构造方法
	
	public SettingCo2Dialog(Context mcontect) {
		super(mcontect);
		this.mcontext = mcontect;

	}
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		mApp=(ClientApp)getActivity().getApplication();

		initView();
	}



	private void initView() {
		mMax_Co2_Et=(EditText)getView().findViewById(R.id.co2_max);
		mMin_Co2_Et=(EditText)getView().findViewById(R.id.co2_min);
		mCo2Setting_btn=(Button)getView().findViewById(R.id.co2_set_btn);
		
		mCo2Setting_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				SensorConfigBean  mBean=mApp.getSensorConfig();
			mBean.maxCo2=Integer.parseInt(mMin_Co2_Et.getText().
					toString().trim().equals("")? "0":mMin_Co2_Et.getText().toString().trim());
			
			mBean.minCo2=Integer.parseInt(mMax_Co2_Et.getText().
					toString().trim().equals("")? "0":mMax_Co2_Et.getText().toString().trim());
			mApp.saveSensorConfig(mBean);
			}});		
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.set_co2_layout, container, false);
	}

}
