package com.allen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.allen.sacjc.R;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity extends SlidingFragmentActivity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}

	boolean flag = false;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {

			if (flag) {
				finish();
			} else {
				Toast.makeText(this, "���������˳�����", Toast.LENGTH_SHORT).show();
				flag = true;
				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						flag = false;
						super.run();
					}
				}.start();
			}

		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, 1, 0, "ȫ��");
		menu.add(0, 0, 0, "����");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 0:
			Intent intent = new Intent(Intent.ACTION_SEND);
			// intent.setType("image/*");//intent.setType("text/plain");
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "�����Ƽ�");
			intent.putExtra(Intent.EXTRA_TEXT,
					"�ˣ�������ʹ��֤ȯ��ҵ����ϵ�����ѧϰ����Դ�ḻ���ʺϿ�ǰ��ս�����ص�ַhttp://liyagang.com/android/download/Allen_SAC.apk");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(Intent.createChooser(intent, getTitle()));

			return true;
		case 1:
			getActionBar().hide();
			return true;
		case android.R.id.home:
			toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
