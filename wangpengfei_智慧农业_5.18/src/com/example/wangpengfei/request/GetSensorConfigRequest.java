package com.example.wangpengfei.request;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.wangpengfei.bean.SensorConfigBean;

public class GetSensorConfigRequest extends AgricultureRequest {
	private static final String ACTION="getConfig";
	
	private SensorConfigBean sensorConfig;
	
	
	
	public SensorConfigBean getSensorConfig(){
		
		return sensorConfig;
		
	}
public void setSensorConfig(SensorConfigBean sensorConfig) 
{
	this.sensorConfig=sensorConfig;
	
	
}
	
	
	public static String getAction() {
	return ACTION;
}
	
	
	
	
	public GetSensorConfigRequest(String userName) {
		super(userName);

	}

	@Override
	protected String getActionName() {

		return ACTION;
	}

	
	
	
	@Override
	protected String onGetJasonBody(){
		JSONObject jsonobj=new JSONObject();
		try {
			jsonobj.put("username", userName);
			return jsonobj.toString();
			
		} catch (JSONException e) {
				e.printStackTrace();
				
		}
		
		
		return super.onGetJasonBody();
		
	}
	
	
	@Override
	protected void OnJasonParse(String responseStr2){
		try {
			JSONObject jsonObject=new JSONObject(responseStr2);
			if(jsonObject!=null&&sensorConfig!=null){
				if(jsonObject.has("result")){
					String result=jsonObject.getString("result");
					if(result.equalsIgnoreCase("ok")){
						
						isSuccess=true;
					}else{
						isSuccess=false;
						
					}
				}
				if(jsonObject.has("maxCo2")){
					sensorConfig.maxCo2=jsonObject.getInt("maxCo2");
				}
				if(jsonObject.has("maxLight")){
					sensorConfig.maxLight=jsonObject.getInt("maxLight");
				}		
				if(jsonObject.has("minCo2")){
					sensorConfig.minCo2=jsonObject.getInt("minCo2");
				}		
				
				if(jsonObject.has("minLight")){
					sensorConfig.minLight=jsonObject.getInt("minLight");
				}
				
				if(jsonObject.has("maxSoilHumidity")){
					sensorConfig.maxSoilHumidity=jsonObject.getInt("maxSoilHumidity");
				}
				
				
				if(jsonObject.has("minSoilHumidity")){
					sensorConfig.minSoilHumidity=jsonObject.getInt("minSoilHumidity");
				}
				
				
				
				
				if(jsonObject.has("minAirHumidity")){
					sensorConfig.minAirHumidity=jsonObject.getInt("minAirHumidity");
				}
				if(jsonObject.has("maxAirHumidity")){
					sensorConfig.maxAirHumidity=jsonObject.getInt("maxAirHumidity");
				}
				
				if(jsonObject.has("minAirTemperature")){
					sensorConfig.minAirTemperature=jsonObject.getInt("minAirTemperature");
				}
				
				if(jsonObject.has("maxAirTemperature")){
					sensorConfig.maxAirTemperature=jsonObject.getInt("maxAirTemperature");
				}
				if(jsonObject.has("controlAuto")){
					sensorConfig.controlAuto=jsonObject.getInt("controlAuto");
				}
				
				if(jsonObject.has("maxSoilTemperature")){
					sensorConfig.maxSoilTemperature=jsonObject.getInt("maxSoilTemperature");
				}
				
				if(jsonObject.has("minSoilTemperature")){
					sensorConfig.minSoilTemperature=jsonObject.getInt("minSoilTemperature");
				}
				
				
				
			}else{
				isSuccess=false;
			}
			
		} catch (JSONException e) {
			isSuccess=false;
			e.printStackTrace();
			
		}
		
	}
	

	
	
}
