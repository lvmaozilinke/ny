package com.example.wangpengfei.fragment;

import java.util.ArrayList;

import com.example.wangpengfei.R;
import com.example.wangpengfei.adapter.ViewPagerAdapters;
import com.example.wangpengfei.adapter.ViewPagerAdapters.OnItemOnClick;
import com.example.wangpengfei.bean.ControllerDataBean;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ControlFragment extends Fragment {
	private ViewPager mViewPager;
	
	private ViewPagerAdapters mAdapter;
	
	private ArrayList<ControllerDataBean> mDataList;
	
	private ControllerDataBean mBlowerCtrl;
	private ControllerDataBean mRoadlampCtrl;
	private ControllerDataBean mWaterCtrl;
	private ControllerDataBean mBuzzerCtrl;
	
	private LinearLayout mLayout;
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		initData();
		//se
		initView();
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
		mViewPager=(ViewPager) getView().findViewById(R.id.device_show);
		mLayout=(LinearLayout) getView().findViewById(R.id.indicator);
		
		mAdapter=new ViewPagerAdapters(getActivity(), mDataList, new OnItemOnClick() {
			
			@Override
			public void chick(int position) {

				
			}
		}, getActivity());
		mViewPager.setAdapter(mAdapter);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.control_fragment_layout, container, false);
		
		//return super.onCreateView(inflater, container, savedInstanceState);
	}

}
