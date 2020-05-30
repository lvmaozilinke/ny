package com.example.wangpengfei.request;

public class ControlRequest extends AgricultureRequest {
	private static final String ACTION= "control";
	
	//水泵控制器
	private int waterPump = 0; // 0表示关闭，1表示打开
	//风扇控制器
	private int blower = 0;// 0表示关闭，1表示打开
	//路灯控制器
	private int roadLamp = 0;// 0表示关闭，1表示打开
	//蜂鸣器控制器
	private int buzzer = 0;// 0表示关闭，1表示打开
	
	private int isOpen=0;
	
	private boolean isCtrWater = false;//是否要控制水泵
	private boolean isCtrlBlower = false;//是否要控制风扇
	private boolean istCtrlRoadLamp = false;//是否要控制路灯
	private boolean isCtrlBuzzer = false;//是否要控制蜂鸣器
	private boolean isCtrlisopen = false;//
	
	
	public ControlRequest(String userName) {
		
		super(userName);
		

	}
	
	
	public void resetRequest(){
		isCtrWater=false;
		isCtrlBlower=false;
		istCtrlRoadLamp=false;
		isCtrlBuzzer=false;
		
	}

	@Override
	protected String getActionName() {

		return null;
	}
	
	public void setBlower(int blower) {
		this.blower = blower;
		this.isCtrlBlower=true;
		
	}



	public void setRoadLamp(int roadLamp) {
		this.roadLamp = roadLamp;
		this.istCtrlRoadLamp=true;
		
	}


	public void setBuzzer(int buzzer) {
		this.buzzer = buzzer;
		this.isCtrlBuzzer=true;
		
	}


	public void setWaterPump(int isOpen) {
		this.isOpen = isOpen;
		this.isCtrlisopen=true;
	}


}
