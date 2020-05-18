package com.example.wangpengfei.dialog;

import com.example.wangpengfei.request.BaseRequest;
import com.example.wangpengfei.request.BaseRequest.OnResponseEventListener;
import com.example.wangpengfei.request.RegisterRequest;
import com.example.wangpengfei.request.BaseRequest.RequestResult;

import com.example.wangpengfei.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("ValidFragment")
public class UserRegisterDialog extends BaseDialog {
	private EditText mUserNameEt;
	private EditText mPasswordEt;
	private Button mCloseBtn;
	private Button mOkBtn;
	private RegisterRequest mRequest;
	
	
	public UserRegisterDialog(Context mcontect) {
		super(mcontect);

	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		setRequest();
		initview();
		setListener();
	}


	private void setListener() {

		mCloseBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				UserRegisterDialog.this.dismiss();
			}
		});
		mOkBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				UserRegisterEnter();
			}

			
		});}
		private void UserRegisterEnter() {

			String userName=mUserNameEt.getText().toString().trim();
			
			String	 password=mPasswordEt.getText().toString().trim();
			
			if(userName.equals(""))
			{
				this.showAltertDialog("提示", "用户不能为空！");
				return ;
			}
			if(password.equals(""))
			{
				this.showAltertDialog("提示", "密码不能为空！");
				return ;
			}
			
			mRequest.setUserName(userName);
			mRequest.setPassword(password);
		//	startRequest(mRequest);//该方法暂时未完成
			
		}
	


	private void initview() {

		mCloseBtn=(Button)getView().findViewById(R.id.close);
		mOkBtn=(Button)getView().findViewById(R.id.ok_button);
		mUserNameEt=(EditText)getView().findViewById(R.id.account_edittext);
		mPasswordEt=(EditText)getView().findViewById(R.id.password_edittext);
		
	}


	private void setRequest() {

		mRequest=new RegisterRequest("");
		mRequest.setOnResponseEventListener(new OnResponseEventListener() {
			
			@Override
			public void OnResponse(BaseRequest request, RequestResult result) {

				if(mRequest.isSuccess()){
				UserRegisterDialog.this.showAltertDialog("提示", "注册成功！");
				
					
				}else{
					UserRegisterDialog.this.showAltertDialog("提示", "注册失败！");

				}
			}
		});
		
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		
		this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
		
		return  inflater.inflate(com.example.wangpengfei.R.layout.user_register_dialog, container);
		
				
	}

}
