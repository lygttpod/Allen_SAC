package com.allen.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.activity.LoginActivity;
import com.allen.activity.MainActivity;
import com.allen.sacjc.R;

/**
 * @author yangyu 功能描述：列表Fragment，用来显示滑动菜单打开后的内容
 */
public class Left_Menu_Fragment extends ListFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		// for (int i = 1; i < 7; i++) {
		// adapter.add(new SampleItem("菜单"+i,
		// android.R.drawable.ic_menu_search));
		// }
		adapter.add(new SampleItem("考试大纲", android.R.drawable.ic_menu_search));
		adapter.add(new SampleItem("第一章 证券市场概述",
				android.R.drawable.ic_menu_search));
		adapter.add(new SampleItem("第二章 股票", android.R.drawable.ic_menu_search));
		adapter.add(new SampleItem("第三章 债券", android.R.drawable.ic_menu_search));
		adapter.add(new SampleItem("第四章 证券投资基金",
				android.R.drawable.ic_menu_search));
		adapter.add(new SampleItem("第五章 金融衍生工具",
				android.R.drawable.ic_menu_search));
		adapter.add(new SampleItem("第六章 证券市场运行 ",
				android.R.drawable.ic_menu_search));
		adapter.add(new SampleItem("第七章 证券中介机构 ",
				android.R.drawable.ic_menu_search));
		adapter.add(new SampleItem("第八章 证券市场法律与监管",
				android.R.drawable.ic_menu_search));
		setListAdapter(adapter);
	}

	private class SampleItem {
		public String tag;
		public int iconRes;

		public SampleItem(String tag, int iconRes) {
			this.tag = tag;
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView
					.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView
					.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Fragment newfragment = null;
		// TODO Auto-generated method stub

		switch (position) {
		case 0:
			newfragment = new Fragment_main();

			break;
		case 1:
			newfragment = new Fragment_one();

			break;
		case 2:
			newfragment = new Fragment_two();

			break;
		case 3:
			newfragment = new Fragment_three();

			break;
		case 4:
			newfragment = new Fragment_four();

			break;
		case 5:
			newfragment = new Fragment_five();

			break;
		case 6:
			newfragment = new Fragment_six();

			break;
		case 7:
			newfragment = new Fragment_seven();

			break;
		case 8:
			newfragment = new Fragment_eight();

			break;

		}
		if (newfragment != null) {
			switchFragment(newfragment);
		}
	}

	private void switchFragment(Fragment fragment) {
		if (getActivity() == null) {
			return;
		}
		if (getActivity() instanceof MainActivity) {
			MainActivity fac = (MainActivity) getActivity();
			fac.switchContent(fragment);
		}
	}
}
