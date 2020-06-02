package com.example.wangpengfei.fragment;

import java.util.Locale;

import com.example.wangpengfei.MainActivity;
import com.example.wangpengfei.R;
import com.example.wangpengfei.dialog.SettingLanguageDialog;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SettingFragment extends Fragment {
	LinearLayout mlinearLayout;
	
	Handler mHandler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			UpdateLanguage((Locale)msg.obj);
		}};

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		InitView();
		setListener();
		
	}

	private void UpdateLanguage(Locale obj) {
		
		Resources res =getResources();
		Configuration config =res.getConfiguration();
		config.locale=obj;
		DisplayMetrics dm =res.getDisplayMetrics();
		res.updateConfiguration(config, dm);
		Intent mintent=new Intent(getActivity(),MainActivity.class);
		getActivity().startActivity(mintent);
		getActivity().finish();
		
		
	}
	
	private void InitView() {
		mlinearLayout=(LinearLayout) getView().findViewById(R.id.language_layout);
	}

	private void setListener() {
		mlinearLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				SettingLanguageDialog mdialog=new SettingLanguageDialog(getActivity(),mHandler);
				mdialog.setCancelable(false);
				mdialog.show(getActivity().getFragmentManager(), "…Ë÷√”Ô—‘");
				

				
			}
		});
		
		

		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.setting_fragment_layout, container, false);
		
	}

}
