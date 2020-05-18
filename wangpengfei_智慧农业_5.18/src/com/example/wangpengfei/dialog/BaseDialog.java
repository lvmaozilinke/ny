package com.example.wangpengfei.dialog;

import com.example.wangpengfei.ClientApp;
import com.example.wangpengfei.request.BaseRequest;
import com.example.wangpengfei.request.RequestThread;

import android.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

@SuppressLint("ValidFragment")
public class BaseDialog extends DialogFragment {
	protected Context mcontext;
	protected ClientApp mApp;
	public BaseDialog(Context mcontect) {
		
		//super();
		
		this.mcontext = mcontect;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		mApp=(ClientApp)getActivity().getApplication();
	}
	protected void showAltertDialog(String title,String message){
		AlertDialog.Builder mbuilder=new AlertDialog.Builder(mcontext);
		mbuilder.setTitle(title);
		mbuilder.setMessage(message);
		mbuilder.setPositiveButton(mcontext.getString(android.R.string.ok), null);
		mbuilder.show();
	}
	protected void startRequest(BaseRequest request)
	{
		if(request!=null)
		{
//			RequestThread thread=new RequestThread(mcontext, mApp.getHandler());
//			thread.setRequest(request);
//			thread.start();
			
		}
	}
	
	

}
