package com.example.wangpengfei.request;

public class ControlRequest extends AgricultureRequest {
	private static final String ACTION= "control";
	
	//ˮ�ÿ�����
	private int waterPump = 0; // 0��ʾ�رգ�1��ʾ��
	//���ȿ�����
	private int blower = 0;// 0��ʾ�رգ�1��ʾ��
	//·�ƿ�����
	private int roadLamp = 0;// 0��ʾ�رգ�1��ʾ��
	//������������
	private int buzzer = 0;// 0��ʾ�رգ�1��ʾ��
	
	private boolean isCtrWater = false;//�Ƿ�Ҫ����ˮ��
	private boolean isCtrlBlower = false;//�Ƿ�Ҫ���Ʒ���
	private boolean istCtrlRoadLamp = false;//�Ƿ�Ҫ����·��
	private boolean isCtrlBuzzer = false;//�Ƿ�Ҫ���Ʒ�����
	
	
	
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


}
