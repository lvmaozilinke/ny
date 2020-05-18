package com.example.wangpengfei.bean;

public class ControllerDataBean {
	
	private String name = ""; //控制器名称
	private int isOpen= 0; // 0表示关闭，1表示打开
	private int onIcon= 0; //控制器打开时的图标ID
	private int offIcon= 0;//控制器关闭时的图标ID
	
	//构造方法
	public ControllerDataBean(String name,  int onIcon, int offIcon) {
		super();
		this.name = name;
		this.onIcon = onIcon;
		this.offIcon = offIcon;
		
	}

//	设置get/set方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public int getOnIcon() {
		return onIcon;
	}

	public void setOnIcon(int onIcon) {
		this.onIcon = onIcon;
	}

	public int getOffIcon() {
		return offIcon;
	}

	public void setOffIcon(int offIcon) {
		this.offIcon = offIcon;
	}

	
	

	
	
}
