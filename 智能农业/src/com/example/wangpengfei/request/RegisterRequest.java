package com.example.wangpengfei.request;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterRequest extends AgricultureRequest {
	private static final String ACTION="register";
	private String userName;
	private String password;
	

	public RegisterRequest(String userName) {
		super(userName);

	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	protected String getActionName() {

		return ACTION;
	}

	@Override
	protected String OnGetJasonBody() {

		JSONObject jsonObj=new JSONObject();
	
		try {
			jsonObj.put("username","userName");
			jsonObj.put("password","password");
			return jsonObj.toString();
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return super.OnGetJasonBody();
	}

}
