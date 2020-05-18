package com.example.wangpengfei.request;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.wangpengfei.bean.SensorValueBean;

public class GetSensorValueRequest extends AgricultureRequest {
	
	private  static final String ACTION="getSensor";
	
	private SensorValueBean mSensorValue;
	
	public SensorValueBean getSensorValue;
	
	
	public SensorValueBean getGetSensorValue() {
		return getSensorValue;
	}

	public void setGetSensorValue(SensorValueBean getSensorValue) {
		this.getSensorValue = getSensorValue;
	}
	
	

	public GetSensorValueRequest(String userName) {
		super(userName);

	}

	@Override
	protected String getActionName() {

		return ACTION;
	}

	@Override
	protected String onGetJasonBody() {
		JSONObject jsonObj=new JSONObject();
		try {
			jsonObj.put("username", userName);
			return jsonObj.toString();
			
		} catch (JSONException e) {
				e.printStackTrace();
				
		}
		return "";
		
		
	}
	
	
	@Override
	protected void OnJasonParse(String responseStr2){
		try {
			JSONObject jsonRespObj=new JSONObject(responseStr2);
			if(jsonRespObj!=null&&mSensorValue!=null){
				if(jsonRespObj.has("result")){
					String result=jsonRespObj.getString("result");
					
					if(result.equalsIgnoreCase("ok")){
						isSuccess=true;
						
					}else{
						isSuccess=false;
						
					}
				}
				mSensorValue.setUpdataTime(new Date());
				if(jsonRespObj.has("airHumidity")){
					mSensorValue.setAirHumid(jsonRespObj.getInt("airHumidity"));
				}
				if(jsonRespObj.has("airTemperature")){
					mSensorValue.setAirTemper(jsonRespObj.getInt("airTemperature"));
					
				}
				
				if(jsonRespObj.has("soilTemperature")){
					mSensorValue.setSoilTemper(jsonRespObj.getInt("soilTemperture"));
					
				}
				
				if(jsonRespObj.has("co2")){
					mSensorValue.setCo2(jsonRespObj.getInt("co2"));
				}
				
				if(jsonRespObj.has("soiHumidity")){
					mSensorValue.setSoilHumid(jsonRespObj.getInt("soilHumidity"));
					
				}
				if(jsonRespObj.has("light")){
					mSensorValue.setLight(jsonRespObj.getInt("light"));
					
				}
				
			}else{
				isSuccess=false;
				
			}
		} catch (JSONException e) {
			isSuccess=false;
			e.printStackTrace();
			
		}
	}

	public void setSensorValue(SensorValueBean curSensorValue) {
		// TODO Auto-generated method stub
		
	}

	public SensorValueBean getSensorValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
