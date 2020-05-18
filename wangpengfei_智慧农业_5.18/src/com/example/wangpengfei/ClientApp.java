package com.example.wangpengfei;

import com.example.wangpengfei.bean.SensorConfigBean;
import com.example.wangpengfei.bean.SensorValueBean;
import com.example.wangpengfei.request.BaseRequest.RequestResult;
import com.example.wangpengfei.request.RequestThread;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.widget.EditText;

public class ClientApp extends Application {
	private SharedPreferences msharedPreferences;
	
	private AlertDialog mDialog;
	
	private Handler mhandler;
	
	private SensorConfigBean mSensorConfig;
	private SensorValueBean mCurSensorValue;
	

	@Override
	public void onCreate() {
		super.onCreate();
		msharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
		
		mSensorConfig=new SensorConfigBean();
		mCurSensorValue=new SensorValueBean();
		
		
		
		
		//�����activity���淵�صĴ�����Ϣ
		
		handleMessage();
	
		getSensorConfig();
		saveSensorConfig(mSensorConfig);
		readSensorConfig();
		getCurSensorValue();
		saveSensorValue(mCurSensorValue);
		getMhandler();
		
	}
	public void saveSensorValue(SensorValueBean mSensorValue) {

		mCurSensorValue=mSensorValue;
		
		
		
	}
	public SensorValueBean getCurSensorValue() {
		
		return mCurSensorValue;

		
	}
	private void readSensorConfig() {
//��ȡ����������
		mSensorConfig.minAirTemperature=msharedPreferences.getInt("minAirTemperature", mSensorConfig.minAirTemperature);
		mSensorConfig.maxAirTemperature=msharedPreferences.getInt("maxAirTemperature", mSensorConfig.maxAirTemperature);
		mSensorConfig.minAirHumidity=msharedPreferences.getInt("minAirHumidity", mSensorConfig.minAirHumidity);
		mSensorConfig.maxAirHumidity=msharedPreferences.getInt("maxAirHumidity", mSensorConfig.maxAirHumidity);
		mSensorConfig.minSoilTemperature=msharedPreferences.getInt("minSoilTemperature", mSensorConfig.minSoilTemperature);
		mSensorConfig.maxSoilTemperature=msharedPreferences.getInt("maxSoilTemperature", mSensorConfig.maxSoilTemperature);
		mSensorConfig.minSoilHumidity=msharedPreferences.getInt("minSoilHumidity", mSensorConfig.minSoilHumidity);
		mSensorConfig.maxSoilHumidity=msharedPreferences.getInt("maxSoilHumidity", mSensorConfig.maxSoilHumidity);
		mSensorConfig.minLight=msharedPreferences.getInt("minLight", mSensorConfig.minLight);
		mSensorConfig.maxLight=msharedPreferences.getInt("maxLight", mSensorConfig.maxLight);
		mSensorConfig.minCo2=msharedPreferences.getInt("minCo2", mSensorConfig.minCo2);
		mSensorConfig.maxCo2=msharedPreferences.getInt("maxCo2", mSensorConfig.maxCo2);
		mSensorConfig.controlAuto=msharedPreferences.getInt("controlAuto", mSensorConfig.controlAuto);
//�Զ�����
	}
	public void saveSensorConfig(SensorConfigBean mSensorConfig) {

		//���洫��������
		Editor editor= msharedPreferences.edit();
		editor.putInt("minAirTemperature", mSensorConfig.minAirTemperature);
		editor.putInt("maxAirTemperature", mSensorConfig.maxAirTemperature);
		editor.putInt("maxAirHumidity", mSensorConfig.maxAirHumidity);
		editor.putInt("minSoilTemperature", mSensorConfig.minSoilTemperature);
		editor.putInt("maxSoilTemperature", mSensorConfig.maxSoilTemperature);
		editor.putInt("minSoilHumidity", mSensorConfig.minSoilHumidity);
		editor.putInt("maxSoilHumidity", mSensorConfig.maxSoilHumidity);
		editor.putInt("minLight", mSensorConfig.minLight);
		editor.putInt("maxLight", mSensorConfig.maxLight);
		editor.putInt("minCo2", mSensorConfig.minCo2);		
		editor.putInt("maxCo2", mSensorConfig.maxCo2);
		editor.putInt("controlAuto", mSensorConfig.controlAuto);
		editor.commit();
	}
	public SensorConfigBean getSensorConfig() {


		//��ô���������
		return mSensorConfig;
		
	}
	
	
	
	private void handleMessage() {

		mhandler=new Handler(new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				switch (msg.what) {
				case RequestThread.MSG_REQUEST_RESULT://���󷵻ؽ��
					RequestResult result=RequestResult.values()[msg.arg1];//�õ����кų���ö��ֵ
					RequestThread requestthread=(RequestThread)msg.obj;
					if(requestthread!=null){
						requestthread.handlerResult(result);//��requeatthread����������
					}
					if(result.equals(RequestResult.RESULT_NO_NET)){
						if(mDialog!=null)
						{
							mDialog.setMessage(getString(R.string.no_new_work));
							mDialog.show();	
						}
					}
					if(result.equals(RequestResult.RESULT_FAIL)){
						if(mDialog!=null)
						{
							mDialog.setMessage(getString(R.string.failed));
							mDialog.show();	
						}
						
					}
					break;

				default:
					break;
				}
					
				return false;
			}
		});
	}
	public String getServerIp(){
		String ipstr=msharedPreferences.getString("Ipstr", "");
		if(ipstr.equals(""))
		{
			ipstr="0.0.0.0";//����IP��ַ
		}
		return ipstr;
	}
	public void saveServerIp(String ipstr){
		Editor edit=msharedPreferences.edit();
		edit.putString("Ipstr", ipstr);
		edit.commit();
	}
	//��ȡ�û���
public String getUserName() {
		String username=msharedPreferences.getString("UserName", "");
		return username;
	}
//�����û���
public void setUserName(String username){
	Editor  edit=msharedPreferences.edit();
	edit.putString("UserName",username);
	edit.commit();
	
}
//��ȡ����
public String getPassWord(){
	String password=msharedPreferences.getString("Password", "");
	return password;
}
//��������
public void setPassword(String password){
	Editor  edit=msharedPreferences.edit();
	edit.putString("Password",password);
	edit.commit();
	
}

//����Activity�ķ�����
//�������մ�������activity����
public void setActivity(Activity activity){
	//��������activity��Ϊ����ʾ�Ի����õ�
	showAlertDialog(activity);
	
	
	
}
//��ʾ�Ի���
private void showAlertDialog(Activity activity) {
	AlertDialog.Builder builder=new AlertDialog.Builder(activity);
	builder.setTitle(getString(R.string.prompt));
	builder.setPositiveButton(getString(android.R.string.ok), null);
	mDialog=builder.create();//����Dialog����
	
}


//getHandler( )����
public Handler getMhandler() {
	
	
	
	return mhandler;
}



}






























