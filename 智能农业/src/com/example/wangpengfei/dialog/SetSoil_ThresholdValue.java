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
public class SetSoil_ThresholdValue extends BaseDialog{
	Context mContext;
	EditText soilTempMinEt,soilTempMaxEt,soilHumiMinEt,soilHumiMaxEt;
	Button soilOkBtn;
	ClientApp mApp;
	public SetSoil_ThresholdValue(Context mcontect) {
		super(mcontect);
		this.mcontext=mcontect;
		mApp = (ClientApp) this.getActivity().getApplication();

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		InitView();
		setListener();
	}

	private void setListener() {
		soilOkBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				SensorConfigBean mBean=mApp.getSensorConfig();
				mBean.minSoilTemperature=Integer.parseInt(soilTempMinEt.getText().toString().trim().equals("")? "0":soilTempMinEt.getText().toString().trim());
				mBean.maxSoilTemperature=Integer.parseInt(soilTempMaxEt.getText().toString().trim().equals("")? "0":soilTempMaxEt.getText().toString().trim());
				mBean.minSoilHumidity=Integer.parseInt(soilHumiMinEt.getText().toString().trim().equals("")? "0":soilHumiMinEt.getText().toString().trim());
				mBean.maxSoilHumidity=Integer.parseInt(soilHumiMaxEt.getText().toString().trim().equals("")? "0":soilHumiMaxEt.getText().toString().trim());
				mApp.saveSensorConfig(mBean);
			}
		});
		
		
	}

	private void InitView() {
		soilOkBtn=(Button)getView().findViewById(R.id.soil_setBtn);
		soilTempMinEt=(EditText)getView().findViewById(R.id.soil_temp_min_value_edit_text);
		soilTempMaxEt=(EditText)getView().findViewById(R.id.soil_temp_max_value_edit_text);
		soilHumiMinEt=(EditText)getView().findViewById(R.id.soil_humi_min_value_edit_text);
		soilHumiMaxEt=(EditText)getView().findViewById(R.id.soil_humi_max_value_edit_text);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

//		return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.set_soil_temp_humi_layout, container, false);
		
	}
	

}
