package com.example.wangpengfei;

import com.example.wangpengfei.fragment.ControlFragment;
import com.example.wangpengfei.fragment.EnvirFragment;
import com.example.wangpengfei.fragment.HistoryFragment;
import com.example.wangpengfei.fragment.SettingFragment;

import android.os.Bundle;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private FragmentTabHost mTabhost;
	private TextView mTitleTv;
	private int[] mTabImage;
	private String[] mTabStrings;
	private Class<?>[] mTabFragments;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
//    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
   
        InitData();//初始化数据
        InitView();//初始化视图【关联控件】
        AddTabs();//添加选项【主框架下面部分】
        SetListener();//控件设置监听器【选项标签设置】
    
        
    
    }
  //控件设置监听器【选项标签设置】
  private void SetListener() {
	  //创建选项标签改变的监听器
	  TabHost.OnTabChangeListener mOnTabChangeListener=new TabHost.OnTabChangeListener() {
	
	@Override
	public void onTabChanged(String arg0) {
		mTitleTv.setText(arg0);
	}
};
mTabhost.setOnTabChangedListener(mOnTabChangeListener);
	  
	}
  
//添加选项【主框架下面部分】
  private void AddTabs() {	
	  for(int i=0;i<mTabStrings.length;i++){
		  TabSpec tag=mTabhost.newTabSpec(mTabStrings[i]).setIndicator(getTabItemView(i));
		  
		  mTabhost.addTab(tag, mTabFragments[i], null);
		  mTabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tag_bg);
		  
	  }
	}
private View getTabItemView(int i) {
	View mview=LayoutInflater.from(this).inflate(R.layout.tab_item_layout, mTabhost.getTabWidget(), false);
	TextView mtextview=(TextView) mview.findViewById(R.id.tab_tv);
	mtextview.setText(mTabStrings[i]);
	BitmapDrawable indicatorBd=(BitmapDrawable)this.getResources().getDrawable(mTabImage[i]);
	indicatorBd.setBounds(0, 0, indicatorBd.getMinimumWidth(),indicatorBd.getMinimumHeight());
	mtextview.setCompoundDrawables(null, indicatorBd, null, null);//图片在上，文字在下
	mtextview.setTextColor(Color.BLACK);
	return mtextview;
}
//初始化视图【关联控件】
  private void InitView() {
	  mTabhost=(FragmentTabHost) findViewById(android.R.id.tabhost);
	  mTabhost.setup(this,getSupportFragmentManager(), R.id.real_tabvontent);
	  mTitleTv=(TextView) findViewById(R.id.title_tv);
	  mTitleTv.setText(mTabStrings[0]);
	  
  }

	//初始化数据
    private void InitData() {
    	mTabStrings=this.getResources().getStringArray(R.array.tag_array);
    	mTabImage=new int[]{
    			R.drawable.envir_icon,
    			R.drawable.history_icon,
    			R.drawable.control_icon,
    			R.drawable.setting_icon};
    	
    	mTabFragments=new Class<?>[]{
    		EnvirFragment.class,
    		HistoryFragment.class,
    		ControlFragment.class,
    		SettingFragment.class
    	};
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {

    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
