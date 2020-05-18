package com.example.wangpengfei.adapter;

import java.util.ArrayList;
import java.util.List;


import com.example.wangpengfei.R;
import com.example.wangpengfei.bean.ControllerDataBean;
import android.content.Context;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPagerAdapters extends PagerAdapter {
	private Context mContext;//上下文环境
	private LayoutInflater mInflater;//布局转换器
	private List<ControllerDataBean>mDatas;//动态数组
	private OnItemOnClick mItemOnClick;//自定义接口对象：目的传递数据
	
	
	public interface OnItemOnClick{//自定义接口抽象方法
		void chick(int position);
		
	}
	
//	public ViewPagerAdapters(Context mContext,List<ControllerDataBean>mDatas,
//			OnItemOnClick onItemOnClick){
//		this.mContext=mContext;
//		this.mDatas=mDatas;
//		this.mItemOnClick=onItemOnClick;
//		this.mInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		
//	}
	
	public ViewPagerAdapters(Context context,
			List<ControllerDataBean> mDatas, OnItemOnClick onItemOnClick, Context mContext) {
		this.mContext=mContext;
		this.mDatas=mDatas;
		this.mItemOnClick=onItemOnClick;
		this.mInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {


		return mDatas.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {

		return arg0.equals((View)arg1);
		
	}

	@Override
	public void destroyItem(View container, int position, Object object) {

//		super.destroyItem(container, position, object);
		((ViewPager)container).removeView((View) object);
		
	}

	@Override
	public int getItemPosition(Object object) {

		return POSITION_NONE;
	}

	@Override
	public Object instantiateItem(ViewGroup container,final int position) {
		
		final ControllerDataBean data=mDatas.get(position);
		View view=mInflater.inflate(R.layout.control_item_layout, container, false);
		ImageView iconIV=(ImageView) view.findViewById(R.id.controller_icon);
		TextView nameTV=(TextView) view.findViewById(R.id.name_text_view);
		TextView statusTV=(TextView) view.findViewById(R.id.status_text_view);
		TextView openTV=(TextView) view.findViewById(R.id.open_text_view);

		
		
		if(data!=null){
			//打开的图标
			nameTV.setText(data.getName());
			String stausStr=mContext.getString(R.string.current_status);
			if(data.getIsOpen()>0){
				iconIV.setImageResource(data.getOnIcon());
				openTV.setText(R.string.close_str);
				stausStr+=mContext.getString(R.string.open_str);
				
			}else{
				//关闭的图标
				iconIV.setImageResource(data.getOffIcon());
				openTV.setText(R.string.open_str);
				stausStr+=mContext.getString(R.string.close_str);
				
			}
			//控制器状态
			statusTV.setText(stausStr);
			openTV.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {

					mItemOnClick.chick(position);
					
				}
			});
			
			
		}
		((ViewPager) container).addView(view);
		


		return view;
	}

	
	
}
