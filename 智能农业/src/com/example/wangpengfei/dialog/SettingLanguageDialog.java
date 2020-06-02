package com.example.wangpengfei.dialog;

import java.util.Locale;

import com.example.wangpengfei.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class SettingLanguageDialog extends BaseDialog {
	Context mcontext;
	RadioGroup mgroup;
	Button mCancelbtn,mOkbtn;
	Locale mlocale=Locale.ENGLISH;//默认英文
	Handler mHandler;

	

	public SettingLanguageDialog(Context mcontect,Handler mHandler) 
	{
		super(mcontect);
		this.mcontext=mcontect;
		this.mHandler=mHandler;
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		initView();
		
		
	}

	private void initView() {
		mCancelbtn=(Button)getView().findViewById(R.id.Cancel_language_btn);//取消按钮
		mOkbtn=(Button)getView().findViewById(R.id.Ok_language_btn);
		//确定按钮
		mgroup=(RadioGroup)getView().findViewById(R.id.radioGroup1);//单选按钮组

mgroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		if (arg1== R.id.radio0) {
			mlocale=Locale.CHINESE;
		} else if (arg1== R.id.radio1) {
			mlocale=Locale.ENGLISH;
		}
		
	}
});

mCancelbtn.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {

		dismiss();
		
	}
});

mOkbtn.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {

		Message msg=new Message();
		msg.obj=mlocale;mHandler.sendMessage(msg);
		Toast.makeText(getActivity(), "语言更改成功！",Toast.LENGTH_LONG).show();
		dismiss();
	}
});

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉标题
		

		return inflater.inflate(R.layout.setting_language_layout, container, false);
		
	}
	

}
