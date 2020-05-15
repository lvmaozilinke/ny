package com.example.wangpengfei.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetUtil {
	private static URL mUrl;
	private static HttpURLConnection mConnection;
	private static BufferedReader mReader=null;
	public static boolean isNetWorkAvailabe(Context context){
		boolean isNewWorkOK=false;
		ConnectivityManager conn=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(null==conn||null==conn.getActiveNetworkInfo()){
			isNewWorkOK=false;
		}
		else{
			isNewWorkOK=true;
		}
		return isNewWorkOK;
	}
	public static String SendData(String UrlString,String Params){
		String result="";
		try {
			createConnection(UrlString);
			setParams();
			writeData(Params);
			result=readData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		if(mReader!=null){
			try {
				mReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}}
	
		return result;
	}
	private static String readData() throws IOException {
		// TODO Auto-generated method stub
		String result="";
		String line="";
		while((line=mReader.readLine())!=null){
			if(result.equals("")){
				result+=line;
			}
			else{
				result="\n"+line;
			}
		}
		return result;
	}
	private static void writeData(String params) throws IOException {
		// TODO Auto-generated method stub
		OutputStream os=mConnection.getOutputStream();
		OutputStreamWriter osw=new OutputStreamWriter(os,"utf-8");
		osw.write(params);
		osw.flush();
		osw.close();
	}
	private static void setParams() {
		// TODO Auto-generated method stub
		mConnection.setConnectTimeout(20*1000);//20ÃëÁªÍø³¬Ê±
		mConnection.setReadTimeout(20*1000);
		mConnection.setDoInput(true);
		mConnection.setDoOutput(true);
	}
	private static void createConnection(String urlString) throws IOException {
		// TODO Auto-generated method stub
		mUrl=new URL(urlString);
		mConnection=(HttpURLConnection)mUrl.openConnection();
	}

}
