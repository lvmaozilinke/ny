package com.example.wangpengfei.dialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

@SuppressLint("ValidFragment") public class SetipDialog extends BaseDialog {
	private EditText mIp1ET;
	private EditText mIp2ET;
	private EditText mIp3ET;
	private EditText mIp4ET;
	private Button mCloseBtn;
	private Button mOKBtn;
	public SetipDialog(Context mcontect) {
		super(mcontect);

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		return inflater.inflate(R.layout.set_ip_dialog_layout, container, false);

	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		InitView();//关联控件
		setListener();//给控件设置监听器
		setValue();
	}
	private void setValue() {

		String ipstr_arr[]=mApp.getServerIp().split("\\.");//getIp地址中保存过的ip
		if(ipstr_arr.length>=4){
			mIp1ET.setText(ipstr_arr[0]);
			mIp2ET.setText(ipstr_arr[1]);
			mIp3ET.setText(ipstr_arr[2]);
			mIp4ET.setText(ipstr_arr[3]);
		}
	}
	private void setListener() {

		mCloseBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {

				SetipDialog.this.dismiss();//关闭对话框
				
			}});
		mOKBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {//验证ip地址是否正确， 保存ip

				if (ValidateIp())//验证Ip地址受否有效
				{
					SetipDialog.this.showAltertDialog("信息提示", "已保存！");
					SetipDialog.this.dismiss();
				}
				
				else{
					SetipDialog.this.showAltertDialog("信息提示", "输入的ip地址有误！");
					
				}
			
			
			}});
		
		
	}
	protected boolean ValidateIp() {
		// TODO Auto-generated method stub
		int ip1,ip2,ip3,ip4;
		ip1=Integer.parseInt(mIp1ET.getText().toString().trim());
		ip2=Integer.parseInt(mIp2ET.getText().toString().trim());
		ip3=Integer.parseInt(mIp3ET.getText().toString().trim());
		ip4=Integer.parseInt(mIp4ET.getText().toString().trim());
		String Ipstr=ip1+"."+ip2+"."+ip3+"."+ip4;//构建出ip地址
		if((Ipstr==null)||"".equals(Ipstr)){
			this.showAltertDialog("提示", "无效ip地址！");
			return false;
		}
		else{//正则表达式
			//Pattern p=Pattern.compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2}");
			Pattern p=Pattern.compile("([1-9]|[1-9]\\d{1}|1\\d{2}|2[0-4]\\d{1}|25[0-5])\\.(\\d{1,2}|1\\d{1,2}|2[0-4]\\d{1}|25[0-5])\\.(\\d{1,2}|1\\d{1,2}|2[0,4]\\d{1}|25[0-5])\\.(\\d{1,2}|1\\d{1,2}|2[0-4]\\d{1}|25[0-5])");
			Matcher m=p.matcher(Ipstr);
			if(!m.matches()){
				this.showAltertDialog("提示", "无效的IP地址");
				return false;
			}
		}
		mApp.saveServerIp(Ipstr);//暂时记下来，这个方法还没有定
		return true;
	}
	private void InitView() {
		// TODO Auto-generated method stub
		mIp1ET=(EditText)getView().findViewById(R.id.ip1_edit_et);
		mIp2ET=(EditText)getView().findViewById(R.id.ip2_edit_et);
		mIp3ET=(EditText)getView().findViewById(R.id.ip3_edit_et);
		mIp4ET=(EditText)getView().findViewById(R.id.ip4_edit_et);
		mCloseBtn=(Button)getView().findViewById(R.id.close_btn);
		mOKBtn=(Button)getView().findViewById(R.id.ok_btn);
	}
	

}
