package com.example.wangpengfei.bean;

public class SensorBean {
	//����������
	private String mNameString="";
	
	//��������ֵ
	private int mValue=0;
	
	//��������ֵ����Сֵ
	private int minValue=Integer.MIN_VALUE;
	
	//��������ֵ�����ֵ
	private int maxValue=Integer.MAX_VALUE;
	
	
		public SensorBean(String name){
			this.mNameString=name;
			
		}


		public String getmNameString() {
			
			return mNameString="";
		}


		public void setmNameString(String mNameString) {
			this.mNameString = mNameString;
		}


		public int getmValue() {
			return mValue;
		}


		public void setmValue(int mValue) {
			this.mValue = mValue;
		}


		public int getMinValue() {
			return minValue;
		}


		public void setMinValue(int minValue) {
			this.minValue = minValue;
		}


		public int getMaxValue() {
			return maxValue;
		}


		public void setMaxValue(int maxValue) {
			this.maxValue = maxValue;
		}	
		

}
