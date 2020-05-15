package com.example.wangpengfei.request;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.wangpengfei.bean.ControllerStatusBean;

public class GetControllerStatusRequest extends AgricultureRequest {
	private static final String ACTION= "getContorllerStatus";
	private ControllerStatusBean mControllerStatus;

	
	
	public ControllerStatusBean getmControllerStatus() {
		return mControllerStatus;
	}

	public void setmControllerStatus(ControllerStatusBean ControllerStatus) {
		this.mControllerStatus = ControllerStatus;
	}

	public GetControllerStatusRequest(String userName) {
		super(userName);

	}

	@Override
	protected String getActionName() {

		return null;
	}
	
	
	@Override
	protected String OnGetJasonBody(){
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
	protected void OnJasonParse(String responseStr){
		try {
			JSONObject jsonRespObj=new JSONObject(responseStr);
			if(jsonRespObj!=null&&mControllerStatus!=null){
				if(jsonRespObj.has("result")){
					String result=jsonRespObj.getString("result");
					//�ж��Ƿ�ɹ�
					if(result.equals("ok")){
						isSuccess=true;
					}else{
						isSuccess=false;
						
					}
					
				}
				
				
				//������������
				if(jsonRespObj.has("Buzzer")){
					mControllerStatus.setBuzzer(jsonRespObj.getInt("Buzzer"));
					
				}
				//ˮ��
				if(jsonRespObj.has("WaterPump")){
					mControllerStatus.setWaterPump(jsonRespObj.getInt("WaterPump"));
					
				}
				//·�ƿ�����
				if(jsonRespObj.has("Roadlamp")){
					mControllerStatus.setRoadLamp(jsonRespObj.getInt("Roadlamp"));
					
				}
				//����
				if(jsonRespObj.has("Blower")){
					mControllerStatus.setRoadLamp(jsonRespObj.getInt("Blower"));

					
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
