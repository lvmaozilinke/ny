package com.example.wangpengfei.fragment;

import com.example.wangpengfei.R;
import com.example.wangpengfei.dialog.SettingLanguageDialog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SettingFragment extends Fragment {
	LinearLayout mlinearLayout;
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		InitView();
		setListener();
		
	}

	private void InitView() {
		mlinearLayout=(LinearLayout) getView().findViewById(R.id.language_layout);
	}

	private void setListener() {
		mlinearLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				SettingLanguageDialog mdialog=new SettingLanguageDialog(getActivity());
				mdialog.show(getActivity().getFragmentManager(), "��������");
				

				
			}
		});
		
		

		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.setting_fragment_layout, container, false);
		
	}

}
