package com.example.wangpengfei.request;

public abstract class BaseRequest {

	public BaseRequest() {
		

	}
	//enum枚举类型
	public enum RequestResult{
		RESULT_FAIL,RESULT_SUCCESS,RESULT_NO_NET;
	}
	//接口特殊类
	public interface OnResponseEventListener{
		void OnResponse(BaseRequest request,RequestResult result);
	}
	private OnResponseEventListener responseEventListener;
	private String responseStr;
	public void setResponseStr(String responseStr) {
		this.responseStr = responseStr;
	}
	public void setOnResponseEventListener(OnResponseEventListener responseEventListener){
		this.responseEventListener=responseEventListener;
	}
	public void parseResult(RequestResult result){
		if(responseStr!=null){
			OnJasonParse(responseStr);
		}
		if(responseEventListener!=null){
			responseEventListener.OnResponse(this, result);
		}
		
	}
	protected void OnJasonParse(String responseStr2) {

		
	}
	protected abstract String getActionName();
	
	protected String OnGetJasonBody(){
		return null;
	}
	
	
	

}
