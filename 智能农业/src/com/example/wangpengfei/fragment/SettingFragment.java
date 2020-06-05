package com.example.wangpengfei.fragment;

import java.util.Locale;

import com.example.wangpengfei.ClientApp;
import com.example.wangpengfei.MainActivity;
import com.example.wangpengfei.R;
import com.example.wangpengfei.dialog.SettingLanguageDialog;

import android.annotation.SuppressLint;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import android.widget.LinearLayout;

@SuppressLint("HandlerLeak")
public class SettingFragment extends Fragment {
	LinearLayout mlinearlayout;
	CheckBox mShowScreenCk;// …Ë÷√ «∑Òœ‘ æª¨∆¡øÿº˛
	ClientApp mApp;
	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			UpdateLanguage((Locale) msg.obj);
		}

	};

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mApp = (ClientApp) this.getActivity().getApplication();
		InitView();
		setListener();
	}

	protected void UpdateLanguage(Locale mlocale) {
		Resources res = getResources();
		Configuration config = res.getConfiguration();
		config.locale = mlocale;
		DisplayMetrics dm = res.getDisplayMetrics();
		res.updateConfiguration(config, dm);
		Intent mintent = new Intent(getActivity(), MainActivity.class);
		getActivity().startActivity(mintent);
		getActivity().finish();
	}

	private void setListener() {
		mlinearlayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				SettingLanguageDialog mdialog = new SettingLanguageDialog(
						getActivity(), mHandler);
				mdialog.setCancelable(false);
				mdialog.show(getActivity().getFragmentManager(), "…Ë÷√”Ô—‘");
			}
		});
		mShowScreenCk.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (mApp.getIsShowSlideScreen() % 2 == 0) {
					mShowScreenCk
							.setBackgroundResource(R.drawable.slide_check_box_on);
					mApp.setIsShowSlideScreen(1);
				} else if (mApp.getIsShowSlideScreen() % 2 == 1) {
					mShowScreenCk
							.setBackgroundResource(R.drawable.slide_check_box_off);
					mApp.setIsShowSlideScreen(0);
				}
			}
		});
	}

	private void InitView() {
		mlinearlayout = (LinearLayout) getView().findViewById(
				R.id.language_layout);

		mShowScreenCk = (CheckBox) getView().findViewById(
				R.id.showStudyCheckBox);
		if (mApp.getIsShowSlideScreen() % 2 == 1) {
			mShowScreenCk.setBackgroundResource(R.drawable.slide_check_box_on);
		} else if (mApp.getIsShowSlideScreen() % 2 == 0) {
			mShowScreenCk.setBackgroundResource(R.drawable.slide_check_box_off);

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.setting_fragment_layout, container,
				false);
	}

}
