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
public class SettingLightDialog extends BaseDialog {
	Context mcontext;
	ClientApp mApp;
	EditText mMax_Light_Et,mMin_Light_Et;
	Button mLightSetting_btn;
	
	public SettingLightDialog(Context mcontect) {
		super(mcontect);
		this.mcontext=mcontect;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mApp=(ClientApp)getActivity().getApplication();

		initView();
	}

	private void initView() {
		mMax_Light_Et=(EditText)getView().findViewById(R.id.light_max);
		mMin_Light_Et=(EditText)getView().findViewById(R.id.light_min);
		mLightSetting_btn=(Button)getView().findViewById(R.id.light_set_btn);
		mLightSetting_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				SensorConfigBean mBean=mApp.getSensorConfig();
			mBean.maxLight=Integer.parseInt(mMin_Light_Et.getText().toString().trim().equals("")? "0":mMin_Light_Et.getText().toString().trim());
			mBean.minLight=Integer.parseInt(mMax_Light_Et.getText().toString().trim().equals("")? "0":mMax_Light_Et.getText().toString().trim());
			mApp.saveSensorConfig(mBean);
			}});
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.set_light_layout, container, false);	}

}
