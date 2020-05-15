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
	
	private Context mcontext;//�����Ļ���
	private ArrayList<SensorBean> aList;//��̬���鱣��SensorBean�Ķ���
	private final LayoutInflater mInflater;//���岼��ת����������sensoritem��С����ת������ʽ����
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
		//С��Ԫ������
		
		return aList.size();
	}



	@Override
	public SensorBean getItem(int position) {

		return aList.get(position);//��ȡĳһ��̬�����Ԫ�ء��洢���ݡ�
	}



	@Override
	public long getItemId(int position) {

		return position;//���ÿ����Ŀ��Ԫ���б��λ��
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//��Ԫ��������/��Ԫ������/���ڵķ�����
		SensorBean itemInfo=(SensorBean)getItem(position);//�õ�����������Ӧ��Ԫ������
		
		ViewHolder mViewHolder=null;
		if(convertView==null){//С��Ԫ����ͼ��û�б�����
			//����ת����׼��С��Ԫ�񲼾ֶ�Ӧ����ͼ
			convertView=mInflater.inflate(R.layout.sensor_grid_item, parent, false);
			mViewHolder=new ViewHolder();
//			�����ʼ��
//			ÿ���ڲ�������Ӧ����С��ͼ�����ϵ�һ���ؼ�
			mViewHolder.mBgLayout=(LinearLayout) convertView.findViewById(R.id.sensor_item_layout);
			
			mViewHolder.mNameTV=(TextView) convertView.findViewById(R.id.sensor_name_text);
			
			mViewHolder.mStautsTV=(TextView) convertView.findViewById(R.id.status_text);
			
			mViewHolder.mSetValeuTV=(TextView) convertView.findViewById(R.id.set_value_text);
			
			mViewHolder.mValueTV=(TextView) convertView.findViewById(R.id.sensor_value_text);
			
			convertView.setTag(mViewHolder);
			
			
			
		}else{
			//�Ѿ�����---�õ����
			mViewHolder=(ViewHolder) convertView.getTag();
		}
		
		//��ʾ�趨����״̬
		if(itemInfo!=null){
			mViewHolder.mNameTV.setText(itemInfo.getmNameString());//***********
			mViewHolder.mValueTV.setText(""+itemInfo.getmValue());//***********
			
			
			//��ʾ�趨ֵ����
			String setStr=mcontext.getString(R.string.set_value);
			final int invalidMin=Integer.MIN_VALUE;//int 
			int minV=itemInfo.getMinValue();
			int maxV=itemInfo.getMaxValue();
			if(minV>invalidMin&&maxV>invalidMin){//��������ֵ�ж�
				setStr+=minV+"~"+maxV;
				
				
			}else if(minV>invalidMin){
				setStr+=minV+"~"+maxV;
				
			}else if(maxV>invalidMin){
				setStr+="<"+maxV;
				
			}
			mViewHolder.mSetValeuTV.setText(setStr);
			
			
			//���ݴ�������ֵ�ж��Ƿ���Ҫ����
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
//				Log.i("��ͣ",position+"*********************8");
			
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
		.setContentTitle(title+"Ԥ��֪ͨ")
		.setContentText(title+"��ǰֵ������������Χ")
		.setWhen(System.currentTimeMillis());

		Notification notify=mBuilder.build();
		
		mNotificationManager.notify(id,notify);
		
		
		
		
		
		
	}



	//�����ڲ���ͼ��װ��
	private static class ViewHolder {
		LinearLayout mBgLayout;//��������
		TextView mNameTV; //��ʾ����������
		TextView mStautsTV;//��ʾ������״̬
		TextView mSetValeuTV;//��ʾ�������ķ�ֵ
		TextView mValueTV;//��ʾ��������ֵ
		}
}
