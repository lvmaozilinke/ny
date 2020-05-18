package com.example.wangpengfei.bean;

public class ControllerStatusBean {
	//水泵控制器
	private int waterPump = 0; // 0表示关闭，1表示打开
	//风扇控制器
	private int blower = 0;// 0表示关闭，1表示打开
	//路灯控制器
	private int roadLamp = 0;// 0表示关闭，1表示打开
	//蜂鸣器控制器
	private int buzzer = 0;// 0表示关闭，1表示打开
	
	public int getWaterPump() {
		return waterPump;
	}
	public void setWaterPump(int waterPump) {
		this.waterPump = waterPump;
	}
	public int getBlower() {
		return blower;
	}
	public void setBlower(int blower) {
		this.blower = blower;
	}
	public int getRoadLamp() {
		return roadLamp;
	}
	public void setRoadLamp(int roadLamp) {
		this.roadLamp = roadLamp;
	}
	public int getBuzzer() {
		return buzzer;
	}
	public void setBuzzer(int buzzer) {
		this.buzzer = buzzer;
	}
	
	
	
}
