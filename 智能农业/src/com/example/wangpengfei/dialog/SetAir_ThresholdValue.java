package com.example.wangpengfei.dialog;


import com.example.wangpengfei.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class SetAir_ThresholdValue extends BaseDialog{

	Context mcontext;
	
	
	public SetAir_ThresholdValue(Context mcontect) {
		super(mcontect);
		this.mcontext=mcontect;
		

	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		return super.onCreateView(inflater, container, savedInstanceState);
	return inflater.inflate(R.layout.set_air_temp_humi_layout, container, false);
	
	}
	
	

}
