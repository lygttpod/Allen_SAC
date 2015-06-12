package com.allen.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.allen.sacjc.R;

public class SplashActivity extends Activity {
	private Handler handler = new Handler();
	SharedPreferences sp;
	Editor editor;
	boolean isfirst = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
		              WindowManager.LayoutParams. FLAG_FULLSCREEN);//全屏
		
		setContentView(R.layout.splash);

		sp = getSharedPreferences("demo", Context.MODE_PRIVATE);
		editor = sp.edit();
		isfirst = sp.getBoolean("isfirst", true);
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (isfirst) {
					editor.putBoolean("isfirst", false);
					editor.putBoolean("showAd", false);
					editor.commit();
					Intent intent = new Intent(SplashActivity.this,
							MainActivity.class);
					startActivity(intent);
					SplashActivity.this.finish();
				} else {
					editor.putBoolean("showAd", true);
					editor.putBoolean("isfirst", true);
					
					editor.commit();
					Intent intent = new Intent(SplashActivity.this,
							MainActivity.class);
					startActivity(intent);
					SplashActivity.this.finish();
				}

			}
		}, 2000);
	}

}
