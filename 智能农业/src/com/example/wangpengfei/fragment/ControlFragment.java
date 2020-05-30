package com.example.wangpengfei.fragment;

import java.util.ArrayList;

import com.example.wangpengfei.ClientApp;
import com.example.wangpengfei.R;
import com.example.wangpengfei.adapter.ViewPagerAdapters;
import com.example.wangpengfei.adapter.ViewPagerAdapters.OnItemOnClick;
import com.example.wangpengfei.bean.ControllerDataBean;
import com.example.wangpengfei.bean.ControllerStatusBean;
import com.example.wangpengfei.request.BaseRequest;
import com.example.wangpengfei.request.BaseRequest.OnResponseEventListener;
import com.example.wangpengfei.request.BaseRequest.RequestResult;
import com.example.wangpengfei.request.ControlRequest;
import com.example.wangpengfei.request.GetControllerStatusRequest;
import com.example.wangpengfei.request.RequestThread;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class ControlFragment extends Fragment  implements OnPageChangeListener{
	private ViewPager mViewPager;
	
	private ViewPagerAdapters mAdapter;
	
	private ArrayList<ControllerDataBean> mDataList;
	
	private ControllerDataBean mBlowerCtrl;
	private ControllerDataBean mRoadlampCtrl;
	private ControllerDataBean mWaterCtrl;
	private ControllerDataBean mBuzzerCtrl;
	
	private LinearLayout mLayout;
	
//	定义ControllerStatusBean对象
	private ControllerStatusBean  mStatusBean;

//	2.定义GetControllerStatusRequest对象
	private GetControllerStatusRequest  mGetControllerStatusRequest;

//	3.定义请求线程对象mThread
	private RequestThread    mThread;

//	4.定义ClientApp全局对象
	private ClientApp        mApp;

//	5.声明进度条
	private ProgressDialog   mDialog;
	
	
	private ControlRequest mControlRequest;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		initData();
		setRequest();
		initView();
	}

	private void setRequest() {
		mGetControllerStatusRequest=new GetControllerStatusRequest("");
		
		mGetControllerStatusRequest.setOnResponseEventListener(new OnResponseEventListener() {
			
			@Override
			public void OnResponse(BaseRequest request, RequestResult result) {
				mDialog.dismiss();//取消进度对规划框
				if(mGetControllerStatusRequest.isSuccess()){
					ControllerStatusBean status=mGetControllerStatusRequest.getmControllerStatus();
					mBlowerCtrl.setIsOpen(status.getBlower());
					mRoadlampCtrl.setIsOpen(status.getRoadLamp());
					mWaterCtrl.setIsOpen(status.getWaterPump());
					mBuzzerCtrl.setIsOpen(status.getBuzzer());
					
					mAdapter.notifyDataSetChanged();//更新滑屏界面的所有界面显示
				
				}}
		});
		
		//设置控制器状态请求对象
		mControlRequest = new ControlRequest("");
		mControlRequest.setOnResponseEventListener(new OnResponseEventListener() {

			@Override
			public void OnResponse(BaseRequest request, RequestResult result) {
				if (mControlRequest.isSuccess()) {
					//控制器请求成功以后，等待1秒钟再向服务器查询控制器状态（因为控制是异步操作）
					RequestThread thread = new RequestThread(mApp.getMhandler(),getActivity());
					thread.setRequest(mGetControllerStatusRequest);
					try{
					Thread.sleep(1000);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				thread.start();
					}
			else {
				mDialog.dismiss();
			}
			}});


		
	}

	private void initData() {
		mDataList=new ArrayList<ControllerDataBean>();
		mBlowerCtrl=new ControllerDataBean(getString(R.string.blower),
				R.drawable.blower_on,
				R.drawable.blower_off);
		mRoadlampCtrl=new ControllerDataBean(getString(R.string.road_lamp), 
				R.drawable.roadlamp_on,
				R.drawable.roadlamp_off);
		mWaterCtrl=new ControllerDataBean(getString(R.string.water_pump),
				R.drawable.water_pump_on,
				R.drawable.water_pump_off);
		mBlowerCtrl=new ControllerDataBean(getString(R.string.buzzer),
				R.drawable.buzzer_on,
				R.drawable.buzzer_off);
		
		mDataList.add(mBlowerCtrl);
		mDataList.add(mRoadlampCtrl);
		mDataList.add(mWaterCtrl);
		mDataList.add(mBuzzerCtrl);
		
		
	}

	private void initView() {
//		mApp=(ClientApp) getActivity().getApplication();
		mViewPager=(ViewPager) getView().findViewById(R.id.device_show);
		mLayout=(LinearLayout) getView().findViewById(R.id.indicator);
		
		mAdapter=new ViewPagerAdapters(getActivity(), mDataList, new OnItemOnClick() {
			
			@Override
			public void chick(int position) {
				mViewPager.setAdapter(mAdapter);
				ControllerDataBean data =mDataList.get(position);
				int oldStatus=data.getIsOpen();
				
				data.setIsOpen((data.getIsOpen()+ 1) % 2);
				mControlRequest.resetRequest();
				
				if (data ==mBlowerCtrl) {
					mControlRequest.setBlower(mBlowerCtrl.getIsOpen());
				} else if (
						data ==mRoadlampCtrl) {mControlRequest.setRoadLamp(mRoadlampCtrl.getIsOpen());
				} else if (
						data ==mWaterCtrl) {mControlRequest.setWaterPump(mWaterCtrl.getIsOpen());
				} else if (
						data ==mBuzzerCtrl) {mControlRequest.setBuzzer(mBuzzerCtrl.getIsOpen());
				}
				startRequest(mControlRequest);
				mDialog.show();
				data.setIsOpen(oldStatus);
				
				
			}

			
		}, getActivity());
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);
		selectIndicator(0);
		startRequest(mGetControllerStatusRequest);
//		启动线程
		mDialog=new ProgressDialog(this.getActivity());
		mDialog.setMessage(getString(R.string.water_pump));
		mDialog.show();
		
	}

	private void startRequest(
			BaseRequest mRequest) {
		if(mRequest!=null){
			mThread=new RequestThread(mApp.getMhandler(),getActivity());
			mThread.setRequest(mRequest);
			mThread.start();
			
		}
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.control_fragment_layout, container, false);
		
		//return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		selectIndicator(arg0);
		
		
	}

	private void selectIndicator(int arg0) {
		mLayout.removeAllViews();
		for(int i=0;i<mDataList.size();i++){
			ImageView imageView=new ImageView(this.getActivity());
			if(i==arg0){
				imageView.setImageResource(R.drawable.page_indicator_focused);
			}else{
				imageView.setImageResource(R.drawable.page_indicator_unfocused);
				
			}
			mLayout.addView(imageView);
			
			
		}

		
	}

}
