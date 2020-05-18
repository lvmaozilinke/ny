package com.example.wangpengfei.request;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class AgricultureRequest extends BaseRequest {
	protected volatile boolean isSuccess=false;
	protected String userName="";
	

	public AgricultureRequest(String userName) {
		super();
		this.userName = userName;
	}
	


	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isSuccess(){
		return isSuccess;
	}
	@Override
	protected void OnJasonParse(String responseStr2) {

		JSONObject jsonRespObj;
		try {
			jsonRespObj = new JSONObject(responseStr2);

			if (jsonRespObj != null) {
				if (jsonRespObj.has("result")) {
					String result = jsonRespObj.getString("result");
					if (result.equals("OK")) {
						isSuccess = true;
					} else {
						isSuccess = false;
					}

				}
			}
		} catch (JSONException e) {

			isSuccess=false;
			e.printStackTrace();
		}
		
	}



	protected String onGetJasonBody() {
		// TODO Auto-generated method stub
		return null;
	}



	
}
