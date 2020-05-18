package com.example.wangpengfei.bean;

public class SensorBean {
	//传感器名称
	private String mNameString="";
	
	//传感器的值
	private int mValue=0;
	
	//传感器阈值的最小值
	private int minValue=Integer.MIN_VALUE;
	
	//传感器阈值的最大值
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
