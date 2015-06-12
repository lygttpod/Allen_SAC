package com.allen.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.allen.activity.fragment.Fragment_login;
import com.allen.activity.fragment.Fragment_register;
import com.allen.sacjc.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class LoginActivity extends FragmentActivity {
	private ActionBar actionBar;
	private ViewPager viewPager;

	private MyPagerAdapter myPagerAdapter;

	private Fragment_login fragment_login = new Fragment_login();
	private Fragment_register fragment_register = new Fragment_register();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.loginmain);

		viewPager = (ViewPager) findViewById(R.id.viewpager_login);
		initViewPage();

		initActionBar();
	}

	private void initActionBar() {
		actionBar = getActionBar();
		actionBar.setTitle("µÇÂ¼/×¢²á");
		actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);

		actionBar.addTab(actionBar.newTab().setText("µÇÂ¼")
				.setTabListener(new MyTabListener()));
		actionBar.addTab(actionBar.newTab().setText("×¢²á")
				.setTabListener(new MyTabListener()));
	}

	/**
	 * ³õÊ¼»¯ViewPage
	 */
	private void initViewPage() {
		
		myPagerAdapter = new MyPagerAdapter((getSupportFragmentManager()));

		viewPager.setAdapter(myPagerAdapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub

				actionBar.setSelectedNavigationItem(position);

			}

			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int position) {
				// TODO Auto-generated method stub

			}
		});

	}

	/**
	 * PagerAdapterÊÊÅäÆ÷
	 * 
	 */

	private class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub

			switch (position) {
			case 0:
				return fragment_login;
			case 1:
				return fragment_register;

			}
			throw new IllegalStateException("No fragment at position "
					+ position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}

	}

	private class MyTabListener implements TabListener {

		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			viewPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub

		}

	}

}
