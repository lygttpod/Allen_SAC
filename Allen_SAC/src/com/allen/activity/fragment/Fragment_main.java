package com.allen.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;


import com.allen.sacjc.R;

public class Fragment_main extends Fragment {

	WebView webView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_webview, container,
				false);
		
		webView = (WebView) view.findViewById(R.id.webView1);
		webView.loadUrl("file:///android_asset/sac.html");

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ��������÷�ʽ
		// AppConnect.getInstance(this).setAdBackColor(Color.argb(50, 120, 240,
		// 120));//���������汳����ɫ
		// AppConnect.getInstance(this).setAdForeColor(Color.YELLOW);//����������������ɫ
		

	}

	private void getJson() {
	}

}
