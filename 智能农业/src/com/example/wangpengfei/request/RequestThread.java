package com.example.wangpengfei.request;

import com.example.wangpengfei.ClientApp;
import com.example.wangpengfei.net.NetUtil;
import com.example.wangpengfei.request.BaseRequest.RequestResult;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class RequestThread extends Thread {
	public static final int MSG_REQUEST_RESULT=0x10;
	private Handler mHandler;
	private Context mContext;
	private BaseRequest mRequest;
	private ClientApp mApp;
	private volatile boolean isLoop=false;
	private volatile int mLoopPeriod=1000;
	private volatile boolean mCancel=false;
	public RequestThread(Handler mHandler, Context mContext) {
		super();
		this.mHandler = mHandler;
		this.mContext = mContext;
		mApp=(ClientApp)mContext.getApplicationContext();
	}
	public void setRequest(BaseRequest mRequest){
		this.mRequest=mRequest;
		
	}
	public void stopRequestThread(){
		mCancel=true;
		isLoop=false;
	}
	public boolean isCancel(){
		return mCancel;
	}
	@Override
	public void run() {

		super.run();
		do{
			RequestResult result=RequestResult.RESULT_FAIL;
			if(NetUtil.isNetWorkAvailabe(mContext)){
				try{
				if(mRequest!=null&&mApp!=null){
					String actionName=mRequest.getActionName();
					String requestBody=mRequest.OnGetJasonBody();
					if(actionName!=null&&requestBody!=null){
						String url="http://"+mApp.getServerIp()+":8890";
						url+="/type/jason";
						url+="/action/"+actionName;
						String response="";
						response=NetUtil.SendData(url, requestBody);
						mRequest.setResponseStr(response);
						result=RequestResult.RESULT_SUCCESS;
						
					}
				}
				}catch(Exception e){
					result=RequestResult.RESULT_FAIL;
					e.printStackTrace();
				}
				
			}else{
				result=RequestResult.RESULT_NO_NET;
			}
			if(!mCancel&&mHandler!=null){
				Message msg=new Message();
				msg.what=MSG_REQUEST_RESULT;
				msg.obj=this;
				msg.arg1=result.ordinal();
				mHandler.sendMessage(msg);
			}
			if(isLoop){
				try {
					Thread.sleep(mLoopPeriod);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}while(isLoop);
	}
	public void handlerResult(RequestResult result){
		if(!mCancel&&mRequest!=null){
			mRequest.parseResult(result);
		}
		
	}
	public void setLoop(boolean isLoop,int loopPeriod){
		this.isLoop=isLoop;
		this.mLoopPeriod=loopPeriod;
		
	}
	

}
