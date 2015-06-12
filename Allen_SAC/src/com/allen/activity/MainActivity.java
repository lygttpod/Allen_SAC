package com.allen.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.allen.activity.fragment.Fragment_main;
import com.allen.activity.fragment.Left_Menu_Fragment;
import com.allen.sacjc.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.qq.e.ads.AdListener;
import com.qq.e.ads.AdRequest;
import com.qq.e.ads.AdSize;
import com.qq.e.ads.AdView;
import com.qq.e.ads.InterstitialAd;
import com.qq.e.ads.InterstitialAdListener;

public class MainActivity extends BaseActivity {
	private ActionBar actionBar;

	private ArrayList<Fragment> fragments;

	
	private AdView bannerAD;
	private InterstitialAd iad;
	private Fragment mContent;
	SharedPreferences sp;
	Editor editor;
	boolean showAd;
	boolean flag = false;

	private LinearLayout miniLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// // ��ʼ��ͳ��������ͨ����������APP_ID, APP_PID
		// AppConnect.getInstance("c6201c224dbac1c42e539b5dda72cbf4", "waps",
		// MainActivity.this);
		setContentView(R.layout.activity_main);
		miniLayout = (LinearLayout) findViewById(R.id.miniAdLinearLayout);
		sp = getSharedPreferences("demo", Context.MODE_PRIVATE);
		editor = sp.edit();
		showAd = sp.getBoolean("showAd", false);
		System.out.println("showAd=" + showAd);
		if (showAd) {

			this.showBannerAD();
			this.showInterstitialAd(this);
		}

		actionBar = getActionBar();
		actionBar.setLogo(R.drawable.menu);
		switchContent(new Fragment_main());
		// �����Ƿ���ʾHomeͼ�갴ť
		actionBar.setDisplayHomeAsUpEnabled(true);
		initSlidingMenu(savedInstanceState);// ��ʼ���໬�˵�SlidingMenu

	}

	/**
	 * ��ʼ�������˵�
	 */
	private void initSlidingMenu(Bundle savedInstanceState) {
		// ���õ��򿪻����˵�ʱ��ActionBar���ܹ�������һ�𻬶�
		setSlidingActionBarEnabled(false);
		// ���û����˵�����ͼ
		setBehindContentView(R.layout.left_fragment);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_left, new Left_Menu_Fragment()).commit();

		// ʵ���������˵�����
		SlidingMenu sm = getSlidingMenu();
		// ���û�����Ӱ�Ŀ��
		sm.setShadowWidthRes(R.dimen.shadow_width);
		// ���û�����Ӱ��ͼ����Դ
		sm.setShadowDrawable(R.drawable.shadow);
		// ���û����˵���ͼ�Ŀ��
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset_left);
		// ���ý��뽥��Ч����ֵ
		sm.setFadeDegree(0.35f);
		// ���ô�����Ļ��ģʽ

		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		sm.setMode(SlidingMenu.LEFT);
		// sm.setSecondaryMenu(R.layout.right_fragment);
		// getFragmentManager().beginTransaction()
		// .replace(R.id.menu_right, new MenuLeftFragment()).commit();
		// sm.setSecondaryShadowDrawable(R.drawable.shadowright);
		// sm.setShadowDrawable(R.drawable.shadow);

	}

	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main, fragment).commit();
		getSlidingMenu().showContent();
		if (showAd) {

			this.showBannerAD();
		//	this.showInterstitialAd(this);
		}
	}

	@Override
	protected void onResume() {
		// // ��������÷�ʽ
		// // AppConnect.getInstance(this).setAdBackColor(Color.argb(50, 120,
		// 240,
		// // 120));//���������汳����ɫ
		// //
		// AppConnect.getInstance(this).setAdForeColor(Color.YELLOW);//����������������ɫ
		// LinearLayout miniLayout = (LinearLayout)
		// findViewById(R.id.miniAdLinearLayout);
		// AppConnect.getInstance(this).showMiniAd(this, miniLayout, 10);//
		// 10��ˢ��һ��
		// this.showBannerAD();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// // �ͷ���Դ��ԭfinalize()�������޸�Ϊclose()
		// AppConnect.getInstance(this).close();
		super.onDestroy();
	}

	public void showBannerAD() {
		this.bannerAD = new AdView(this, AdSize.BANNER, Constants.APPId,
				Constants.BannerPosId);
		AdRequest adRequest = new AdRequest();
		adRequest.setShowCloseBtn(true);
		this.bannerAD.setAdListener(new AdListener() {

			@Override
			public void onNoAd() {
				Log.i("admsg:", "Banner AD LoadFail");
			}

			@Override
			public void onBannerClosed() {
				// ���ڿ������ͨ���رհ�ťʱ��Ч
				Log.i("admsg:", "Banner AD Closed");
			}

			@Override
			public void onAdReceiv() {
				Log.i("admsg:", "Banner AD Ready to show");
			}

			@Override
			public void onAdExposure() {
				Log.i("admsg:", "Banner AD Exposured");
			}

			@Override
			public void onAdClicked() {
				// Banner��淢�����ʱ�ص������ڵ��ȥ�ص����ز��ܱ�֤�ص���������������ͳ������һ��
				Log.i("admsg:", "Banner AD Clicked");
			}
		});
		this.miniLayout.removeAllViews();
		this.miniLayout.addView(bannerAD);

		bannerAD.fetchAd(adRequest);
	}

	public void showInterstitialAd(Activity activity) {

		iad = new InterstitialAd(activity, Constants.APPId,
				Constants.InterteristalPosId);
		iad.setAdListener(new InterstitialAdListener() {
			@Override
			public void onBack() {
				// iad.loadAd();
				Log.i("admsg:", "Intertistial AD Closed");
			}

			@Override
			public void onFail() {
				Log.i("admsg:", "Intertistial AD Load Fail");
			}

			@Override
			public void onAdReceive() {
				Log.i("admsg:", "Intertistial AD  ReadyToShow");

				iad.show(MainActivity.this);
			}

			@Override
			public void onClicked() {
				// ������淢�����ʱ�ص������ڵ��ȥ�ص����ز��ܱ�֤�ص���������������ͳ������һ��
				Log.i("admsg:", "Intertistial AD Clicked");
			}

			@Override
			public void onExposure() {
				// ��������ع�ʱ�Ļص�
				Log.i("admsg:", "Intertistial AD Exposured");
			}
		});
		iad.loadAd();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		menu.add(0, 0, 0, "����");
		menu.add(0, 1, 0, "ȫ��");
		menu.add(0, 2, 0, "����");
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
			intent.putExtra(
					Intent.EXTRA_TEXT,
					"�ˣ�������ʹ��֤ȯ��ҵ����ϵ�����ѧϰ����Դ�ḻ���ʺϿ�ǰ��ս�����ص�ַhttp://liyagang.com/android/download/Allen_SAC.apk");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(Intent.createChooser(intent, getTitle()));

			return true;
		case 1:
			getActionBar().hide();
			if (showAd) {
				this.showInterstitialAd(this);
			}
			return true;
		case 2:
        dialog();
			return true;
		case android.R.id.home:
			toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void dialog() {
	 AlertDialog.Builder builder = new Builder(this);
	  builder.setMessage("֤ȯ��ҵ���Ը���֪ʶ��ϵ�л�½�����ϵ��Ƴ��������ڴ�������"+"\n"+"����֧��ʹ�������Ķ�����");
	  builder.setTitle("��ʾ");
	  builder.setPositiveButton("ȷ��", new OnClickListener() {
		
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
	});
	  builder.create().show();
	  }
}
