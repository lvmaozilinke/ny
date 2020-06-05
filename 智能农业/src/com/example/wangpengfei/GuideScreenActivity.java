package com.example.wangpengfei;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GuideScreenActivity extends BaseActivity {
		Button mEnter;
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_layout);
		mEnter=(Button)findViewById(R.id.Enter_btn);
		mEnter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent mintent=new    Intent(GuideScreenActivity.this,UserLoginActivity.class);
				GuideScreenActivity.this.startActivity(mintent);
				GuideScreenActivity.this.finish();
			}
		});
		
		
	}
	

}
