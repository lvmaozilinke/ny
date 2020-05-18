package com.example.wangpengfei.fragment;



import java.util.ArrayList;
import java.util.Random;

import com.example.wangpengfei.ClientApp;
import com.example.wangpengfei.R;
import com.example.wangpengfei.adapter.SensorGridViewAdapter;
import com.example.wangpengfei.bean.SensorBean;
import com.example.wangpengfei.bean.SensorConfigBean;
import com.example.wangpengfei.bean.SensorValueBean;
import com.example.wangpengfei.request.BaseRequest;
import com.example.wangpengfei.request.BaseRequest.OnResponseEventListener;
import com.example.wangpengfei.request.GetSensorConfigRequest;
import com.example.wangpengfei.request.GetSensorValueRequest;
import com.example.wangpengfei.request.RequestThread;
import com.example.wangpengfei.request.BaseRequest.RequestResult;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class EnvirFragment extends Fragment {
	private GridView mGridView;
	private SensorGridViewAdapter mAdapter;
	private ArrayList<SensorBean> mList;
	
	private ClientApp mApp;
	private GetSensorConfigRequest mConfigRequest;
	private GetSensorValueRequest mSensorRequest;
	private RequestThread mConfigThread,mSensorThread;
	
	
	//��ʱ����һ���߳�
	TempThread mTempThread;

Handler mhandler=new Handler(new Handler.Callback() {
	
	@Override
	public boolean handleMessage(Message arg0) {
		EnvirFragment.this.initData1();
		return false;
	}
});
	
	String[] mSensorName;//����������
	
	


	
	
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		mApp=(ClientApp) getActivity().getApplication();
		
		initData();
		initView();
		setGridView();
		
//		updateView();
//		
//		setRequest();
//		setGetConfigThread();
		
		startTempThread();//��ʱ�߳�
	}

	private void startTempThread() {
		mTempThread=new TempThread(mhandler);
		mTempThread.start();
		
		

		
	}

	private void setGetConfigThread() {//������ȡ���������õ��߳�
		mConfigThread=new RequestThread(mApp.getMhandler(), this.getActivity());
		mConfigThread.setRequest(mConfigRequest);
		mConfigThread.start();
		

		
	}

	private void setRequest() {//��ȡ����������ֵ������
		

		mConfigRequest=new GetSensorConfigRequest("");
		mConfigRequest.setSensorConfig(mApp.getSensorConfig());
		mConfigRequest.setOnResponseEventListener(new OnResponseEventListener() {
			
			@Override
			public void OnResponse(BaseRequest request, RequestResult result) {
				if(mConfigRequest.isSuccess()){
					mConfigThread.interrupt();
					mApp.saveSensorConfig(mConfigRequest.getSensorConfig());
					
					setGetSensorThread();//������ȡ��������ǰֵ�߳�
				}
				
			}
		});
	}

	private void setGetSensorThread() {
		
		mSensorThread=new RequestThread(mApp.getMhandler(),this.getActivity());
		mSensorRequest=new GetSensorValueRequest("");
		mSensorRequest.setSensorValue(mApp.getCurSensorValue());
		
		mSensorRequest.setOnResponseEventListener(new OnResponseEventListener() {
			
			@Override
			public void OnResponse(BaseRequest request, RequestResult result) {

				mApp.saveSensorValue(mSensorRequest.getSensorValue());
				updateView();//��ȡ���˴�������ֵ��ʱ�����
				
				
			}
		});
		
		mSensorThread.setRequest(mConfigRequest);
		mSensorThread.start();		
	}

	private void updateView() {

		mList.clear();
		SensorBean mBean;
		
		SensorValueBean mValue=mApp.getCurSensorValue();
		SensorConfigBean mConfig=mApp.getSensorConfig();
		
		
		//����co2������������
		mBean=new SensorBean(mSensorName[0]);
		mBean.setMaxValue(mConfig.maxCo2);
		mBean.setMinValue(mConfig.minCo2);
		mBean.setmValue(mValue.getCo2());
		
		mList.add(mBean);
		
		//���ù��մ�����������
		mBean=new SensorBean(mSensorName[1]);
		mBean.setMaxValue(mConfig.maxLight);
		mBean.setMinValue(mConfig.minLight);
		mBean.setmValue(mValue.getLight());
		mList.add(mBean);
		
		//���ÿ����¶ȴ�����������
				mBean=new SensorBean(mSensorName[2]);
				mBean.setMaxValue(mConfig.maxAirTemperature);
				mBean.setMinValue(mConfig.minAirTemperature);
				mBean.setmValue(mValue.getAirTemper());
				mList.add(mBean);
		//���ÿ���ʪ�ȴ�����������
				mBean=new SensorBean(mSensorName[3]);
				mBean.setMaxValue(mConfig.maxAirHumidity);
				mBean.setMinValue(mConfig.minAirHumidity);
				mBean.setmValue(mValue.getAirHumid());
				mList.add(mBean);			
				
				//���������¶ȴ�����������
				mBean=new SensorBean(mSensorName[4]);
				mBean.setMaxValue(mConfig.maxSoilTemperature);
				mBean.setMinValue(mConfig.minSoilTemperature);
				mBean.setmValue(mValue.getSoilTemper());
				mList.add(mBean);
				
				//��������ʪ�ȴ�����������
				mBean=new SensorBean(mSensorName[2]);
				mBean.setMaxValue(mConfig.maxAirTemperature);
				mBean.setMinValue(mConfig.minAirTemperature);
				mBean.setmValue(mValue.getAirTemper());
				mList.add(mBean);
				
//				mAdapter.notifyDataSetChanged();
				
	}

	private void setGridView() {

		mAdapter=new SensorGridViewAdapter(getActivity(), mList);
		mGridView.setAdapter(mAdapter);
		
	}
	

	private void initView() {

		mGridView=(GridView) getView().findViewById(R.id.envir_gridview);
	}

	
	private void initData() {
		mList=new ArrayList<SensorBean>();
		SensorBean mBean;
		
		mSensorName=getResources().getStringArray(R.array.sensor);
		for(int i =0;i<mSensorName.length;i++){
			mBean=new SensorBean(mSensorName[i]);
			mBean.setmValue(3*i);
			mBean.setMaxValue(2*i+3);
			mBean.setMinValue(2*i);
			mList.add(mBean);
			

			
			
		}
		
	}
	
	private void initData1(){
		
		mList.clear();
		SensorBean mBean;
		
		mSensorName=getResources().getStringArray(R.array.sensor);
		for(int i =0;i<mSensorName.length;i++){
			mBean=new SensorBean(mSensorName[i]);
			
			Random mrandom=new Random();
			int mRang=mrandom.nextInt(3*(i+1));
			mBean.setmValue(mRang);
		
			mBean.setMaxValue(2*i+3);
			mBean.setMinValue(2*i);
			mList.add(mBean);
			
		}
		mAdapter.notifyDataSetChanged();
		
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.envir_fragment_layout, container, false);

		//return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onStop() {

		super.onStop();
		Log.i("��ͣ","��ͣ��");
		mTempThread.mLoop=false;
		mTempThread.interrupt();
		
	}

	@Override
	public void onDestroy() {

		super.onDestroy();
		if(mTempThread.isAlive()){
			mTempThread.mLoop=false;
			mTempThread.interrupt();
			
			
		}
	}

	
	
	
}

//������ʱ�߳���
	class TempThread extends Thread{
		boolean mLoop=true;
		Handler mhandler;
		
		public TempThread(Handler mhandler) {
			
			super();
			this.mhandler=mhandler;
			
			
		}
		@Override
		public void run(){
			try {
				while(mLoop){
					Thread.sleep(2*1000);
					mhandler.sendEmptyMessage(1);
					
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
		
	}
