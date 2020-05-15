package com.example.wangpengfei;

import com.example.wangpengfei.dialog.BaseDialog;
import com.example.wangpengfei.dialog.SetipDialog;
import com.example.wangpengfei.dialog.UserRegisterDialog;
import com.example.wangpengfei.request.BaseRequest;
import com.example.wangpengfei.request.BaseRequest.OnResponseEventListener;
import com.example.wangpengfei.request.LoginRequest;
import com.example.wangpengfei.request.RequestThread;
import com.example.wangpengfei.request.BaseRequest.RequestResult;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserLoginActivity extends BaseActivity {
	private TextView mSetIpTv;
	private TextView mRegisterTv;
	private EditText mUserNameEt;
	private EditText mPassWordEt;
	private Button mLoginBtn;
	
	private LoginRequest mRequest;
	private RequestThread mThread;
	private ProgressDialog mDialog;//加载进度条
	
	

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		this.setContentView(R.layout.user_login_layout);
		InitView();
		setListener();
		setText();
		mDialog=new ProgressDialog(this);
		setRequsest();
	}

	private void setRequsest() {
		mRequest=new LoginRequest("");
		mRequest.setOnResponseEventListener(new OnResponseEventListener() {
			
			@Override
			public void OnResponse(BaseRequest request, RequestResult result) {
				mDialog.dismiss();
				Intent mintent=new Intent(UserLoginActivity.this,MainActivity.class);
				UserLoginActivity.this.startActivity(mintent);
				UserLoginActivity.this.finish();
				if(mRequest.isSuccess()){
					Intent mintent1=new Intent(UserLoginActivity.this,MainActivity.class);
					UserLoginActivity.this.startActivity(mintent1);
					UserLoginActivity.this.finish();
					
				}else{
					showAlertDialog(getString(R.string.prompt),getString(R.string.failed));
				}
			}
		});

		
	}

	public void setThread() {
		if(mRequest!=null){
			mThread=new RequestThread(mApp.getMhandler(),this);
			
		}
	}

	private void setText() {
		// TODO Auto-generated method stub
		mUserNameEt.setText(mApp.getUserName());
		
	}

	private void setListener() {
		// TODO Auto-generated method stub
		mSetIpTv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SetipDialog mIpdialog=new SetipDialog(UserLoginActivity.this);
				mIpdialog.show(getFragmentManager(), "dialog");
			}
		});
		mRegisterTv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				//注册代码未完，待后续
				BaseDialog dialog=new UserRegisterDialog(UserLoginActivity.this);
				dialog.show(getFragmentManager(), "dialog");
				
			}
		});
		mLoginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent mintent=new Intent(UserLoginActivity.this,MainActivity.class);
				UserLoginActivity.this.startActivity(mintent);
				UserLoginActivity.this.finish();
				//userLoginEntry();
				//setThread();此处临时屏蔽，待后续沙盘
			}
		});
	}
protected void userLoginEntry(){
	String userName=mUserNameEt.getText().toString().trim();
	String password=mUserNameEt.getText().toString().trim();
	if(userName.equals("")){
		showAlertDialog(getString(R.string.prompt), getString(R.string.username_no_empty));
		return;
	}
	if(password.equals("")){
		showAlertDialog(getString(R.string.prompt), getString(R.string.password_no_empty));
		return;
	}
	mApp.setUserName(userName);
	mRequest.setUserName(userName);
	mRequest.setPassword(password);
	setLoadingDialog();
	
}
	
	
	private void InitView() {

		mSetIpTv=(TextView)findViewById(R.id.set_ip_text_view);
		mRegisterTv=(TextView)findViewById(R.id.register_text_view);
		mUserNameEt=(EditText)findViewById(R.id.account_edit_text);
		mPassWordEt=(EditText)findViewById(R.id.password_edit_text);
		mLoginBtn=(Button)findViewById(R.id.login_button);
		
	}
	
	private void setLoadingDialog(){
		mDialog.setMessage(getString(R.string.login_str));
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setIndeterminate(false);
		mDialog.show();
		}
	
	private void showAlertDialog(String title,String mMessage){
		AlertDialog.Builder mbuilder=new AlertDialog.Builder(this);
		mbuilder.setTitle(title);
		mbuilder.setMessage(mMessage);
		mbuilder.setPositiveButton(getString(android.R.string.ok), null);
		mbuilder.show();
		
		
	}
	

}
