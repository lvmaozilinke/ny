package com.example.wangpengfei.bean;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SensorValueBean {
	private Date updataTime=null;//��������ֵ��ʱ���
	
	private int co2= -1;//CO2Ũ��
			
	private int light= -1;//�ƹ�Ũ��
	
	private int airTemper= -1;//�����¶�
	
	private int airHumid= -1;//����ʪ��
	
	private int soilTemper= -1;//�����¶�
	
	private int soilHumid= -1;//����ʪ��
	
	//���췽��
	public SensorValueBean(){
		
	}
	
@Override
	public String toString(){
	SimpleDateFormat dateFormat=new SimpleDateFormat(
			"yyyy-mm-dd HH:mm:ss",Locale.CHINESE);
	
	StringBuffer buffer=new StringBuffer();
	buffer.append("[");
	if(updataTime!=null){
		buffer.append(dateFormat.format(updataTime));
		buffer.append(",");
		
	}
	buffer.append(",CO2="+co2);
	buffer.append(",light="+light);
	buffer.append(",airTemper="+airTemper);
	buffer.append(",airHumid="+airHumid);
	buffer.append(",soilTemper="+soilTemper);
	buffer.append(",soilHumid="+soilHumid);
	buffer.append("]");
	
	
		return buffer.toString();
		
	}
	
	
	public Date getUpdataTime() {
		return updataTime;
	}

	public void setUpdataTime(Date updataTime) {
		this.updataTime = updataTime;
	}

	public int getCo2() {
		return co2;
	}

	public void setCo2(int co2) {
		this.co2 = co2;
	}

	public int getLight() {
		return light;
	}

	public void setLight(int light) {
		this.light = light;
	}

	public int getAirTemper() {
		return airTemper;
	}

	public void setAirTemper(int airTemper) {
		this.airTemper = airTemper;
	}

	public int getAirHumid() {
		return airHumid;
	}

	public void setAirHumid(int airHumid) {
		this.airHumid = airHumid;
	}

	public int getSoilTemper() {
		return soilTemper;
	}

	public void setSoilTemper(int soilTemper) {
		this.soilTemper = soilTemper;
	}

	public int getSoilHumid() {
		return soilHumid;
	}

	public void setSoilHumid(int soilHumid) {
		this.soilHumid = soilHumid;
	}
	
	
	
	
	
	
	
	
	
	
}
