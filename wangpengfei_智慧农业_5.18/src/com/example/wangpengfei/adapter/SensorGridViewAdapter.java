package com.example.wangpengfei.adapter;

import java.util.ArrayList;

import com.example.wangpengfei.R;
import com.example.wangpengfei.bean.SensorBean;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SensorGridViewAdapter extends ArrayAdapter<SensorBean> {
	
	private Context mcontext;//上下文环境
	private ArrayList<SensorBean> aList;//动态数组保存SensorBean的对象
	private final LayoutInflater mInflater;//定义布局转换器、、把sensoritem的小布局转换成正式布局
	NotificationManager mNotificationManager;
	
	
	public SensorGridViewAdapter(Context mcontext, ArrayList<SensorBean> aList) {
		super(mcontext, 0,aList);
		this.mcontext = mcontext;
		this.aList = aList;
		mInflater=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		mNotificationManager=(NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);
	}



	@Override
	public int getCount() {
		//小单元格数量
		
		return aList.size();
	}



	@Override
	public SensorBean getItem(int position) {

		return aList.get(position);//获取某一动态数组的元素【存储数据】
	}



	@Override
	public long getItemId(int position) {

		return position;//获得每个条目单元格列表的位置
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//单元格索引号/单元格界面的/所在的服务器
		SensorBean itemInfo=(SensorBean)getItem(position);//得到索引号所对应的元素数据
		
		ViewHolder mViewHolder=null;
		if(convertView==null){//小单元格视图有没有被创建
			//布局转换器准换小单元格布局对应的视图
			convertView=mInflater.inflate(R.layout.sensor_grid_item, parent, false);
			mViewHolder=new ViewHolder();
//			界面初始化
//			每个内部类所对应的是小视图布局上的一个控件
			mViewHolder.mBgLayout=(LinearLayout) convertView.findViewById(R.id.sensor_item_layout);
			
			mViewHolder.mNameTV=(TextView) convertView.findViewById(R.id.sensor_name_text);
			
			mViewHolder.mStautsTV=(TextView) convertView.findViewById(R.id.status_text);
			
			mViewHolder.mSetValeuTV=(TextView) convertView.findViewById(R.id.set_value_text);
			
			mViewHolder.mValueTV=(TextView) convertView.findViewById(R.id.sensor_value_text);
			
			convertView.setTag(mViewHolder);
			
			
			
		}else{
			//已经创建---得到标记
			mViewHolder=(ViewHolder) convertView.getTag();
		}
		
		//显示设定内容状态
		if(itemInfo!=null){
			mViewHolder.mNameTV.setText(itemInfo.getmNameString());//***********
			mViewHolder.mValueTV.setText(""+itemInfo.getmValue());//***********
			
			
			//显示设定值内容
			String setStr=mcontext.getString(R.string.set_value);
			final int invalidMin=Integer.MIN_VALUE;//int 
			int minV=itemInfo.getMinValue();
			int maxV=itemInfo.getMaxValue();
			if(minV>invalidMin&&maxV>invalidMin){//有意义数值判断
				setStr+=minV+"~"+maxV;
				
				
			}else if(minV>invalidMin){
				setStr+=minV+"~"+maxV;
				
			}else if(maxV>invalidMin){
				setStr+="<"+maxV;
				
			}
			mViewHolder.mSetValeuTV.setText(setStr);
			
			
			//根据传感器的值判断是否需要报警
			boolean isWarning=false;
			int value=itemInfo.getmValue();
			if(value>invalidMin&&((minV>invalidMin||maxV>invalidMin))){
				if(minV>invalidMin&&value<minV){
					isWarning=true;
					
				}
				if(maxV>invalidMin&&value>maxV){
					isWarning=false;
					
				}
				
			}
			
			if(isWarning){
				mViewHolder.mBgLayout.setBackgroundResource(R.color.card_bg_red);
				
//				mViewHolder.mValueTV.setText(mcontext.getResources().getString(R.string.warning));
				mViewHolder.mStautsTV.setText(mcontext.getResources().getString(R.string.warning));
//				String a=itemInfo.getmNameString();
//			System.out.print(a);
//				Log.i("暂停",position+"*********************8");
			
				sendNotification(position,itemInfo.getmNameString());
				
//				
			}else{
				
				mViewHolder.mBgLayout.setBackgroundResource(R.color.card_bg_green);
//				mViewHolder.mValueTV.setText(mcontext.getResources().getString(R.string.normal));
				mViewHolder.mStautsTV.setText(mcontext.getResources().getString(R.string.normal));

				
			}
			
			
		}

//		return super.getView(position, convertView, parent);
		return convertView;
		
	}
	
	protected void sendNotification(int id,String title) {
		NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(mcontext)
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentTitle(title+"预警通知")
		.setContentText(title+"当前值超过了正常范围")
		.setWhen(System.currentTimeMillis());

		Notification notify=mBuilder.build();
		
		mNotificationManager.notify(id,notify);
		
		
		
		
		
		
	}



	//创建内部视图封装类
	private static class ViewHolder {
		LinearLayout mBgLayout;//背景布局
		TextView mNameTV; //显示传感器名称
		TextView mStautsTV;//显示传感器状态
		TextView mSetValeuTV;//显示传感器的阀值
		TextView mValueTV;//显示传感器的值
		}
}
