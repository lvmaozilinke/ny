package com.example.wangpengfei.bean;

public class ControllerDataBean {
	
	private String name = ""; //����������
	private int isOpen= 0; // 0��ʾ�رգ�1��ʾ��
	private int onIcon= 0; //��������ʱ��ͼ��ID
	private int offIcon= 0;//�������ر�ʱ��ͼ��ID
	
	//���췽��
	public ControllerDataBean(String name,  int onIcon, int offIcon) {
		super();
		this.name = name;
		this.onIcon = onIcon;
		this.offIcon = offIcon;
		
	}

//	����get/set����
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
