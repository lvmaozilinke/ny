package com.example.wangpengfei.request;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginRequest extends AgricultureRequest {
	private static final String ACTION="login";
	private String UserName;
	private String password;
	public LoginRequest(String userName) {
		super(userName);

	}

	public String getUserName() {
		return UserName;
	}


	public void setUserName(String userName) {
		UserName = userName;
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
	protected String onGetJasonBody(){
		
		try {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("username", userName);
			jsonObj.put("password",password);
			return jsonObj.toString();
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return super.OnGetJasonBody();
		
	}

}
