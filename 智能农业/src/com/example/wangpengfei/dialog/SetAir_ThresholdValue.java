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
public class SetAir_ThresholdValue extends BaseDialog {

	Context mcontext;

	EditText airTempMinEt, airTempMaxEt, airHumiMinEt, airHumiMaxEt;
	Button airOkBtn;

	ClientApp mApp;

	public SetAir_ThresholdValue(Context mcontect) {
		super(mcontect);
		this.mcontext = mcontect;
		mApp = (ClientApp) this.getActivity().getApplication();

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		InitView();
		setListener();
	}

	private void setListener() {
		airOkBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				SensorConfigBean mBean=mApp.getSensorConfig();
				mBean.minAirTemperature=Integer.parseInt(airTempMinEt.getText().toString().trim().equals("")? "0":airTempMinEt.getText().toString().trim());
				mBean.maxAirTemperature=Integer.parseInt(airTempMaxEt.getText().toString().trim().equals("")? "0":airTempMaxEt.getText().toString().trim());
				mBean.minAirHumidity=Integer.parseInt(airHumiMinEt.getText().toString().trim().equals("")? "0":airHumiMinEt.getText().toString().trim());
				mBean.maxAirHumidity=Integer.parseInt(airHumiMaxEt.getText().toString().trim().equals("")? "0":airHumiMaxEt.getText().toString().trim());
				mApp.saveSensorConfig(mBean);
			
			
			
			}
		});
		

		
	}

	private void InitView() {
		airOkBtn = (Button) getView().findViewById(R.id.air_setBtn);
		airTempMinEt = (EditText) getView().findViewById(
				R.id.major_min_value_edit_text);
		airTempMaxEt = (EditText) getView().findViewById(
				R.id.major_max_value_edit_text);
		airHumiMinEt = (EditText) getView().findViewById(
				R.id.slave_min_value_edit_text);
		airHumiMaxEt = (EditText) getView().findViewById(
				R.id.slave_max_value_edit_text);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.set_soil_temp_humi_layout, container,
				false);

	}

}
