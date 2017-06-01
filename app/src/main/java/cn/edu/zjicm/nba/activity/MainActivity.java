package cn.edu.zjicm.nba.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import cn.edu.zjicm.nba.R;
import cn.edu.zjicm.nba.fragment.GameFragment;
import cn.edu.zjicm.nba.fragment.GroupFragment;
import cn.edu.zjicm.nba.fragment.MineFragment;


public class MainActivity extends FragmentActivity {

	private IndicatorViewPager indicatorViewPager;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.tabmain_viewPager);

		Indicator indicator = (Indicator) findViewById(R.id.tabmain_indicator);
		indicator.setScrollBar(new ColorBar(getApplicationContext(),
				getResources().getColor(R.color.pink), 5));

		int selectColor = getResources().getColor(R.color.pink);
		int unSelectColor = getResources().getColor(R.color.tab_text_color);
		indicator.setOnTransitionListener(new OnTransitionTextListener()
				.setColor(selectColor, unSelectColor).setSize(17, 17));

		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
	}

	private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
		private String[] tabNames = { "分组", "赛程", "我的"};
		private LayoutInflater inflater;

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
			inflater = LayoutInflater.from(getApplicationContext());
		}

		@Override
		public int getCount() {
			return tabNames.length;
		}

		@Override
		public View getViewForTab(int position, View convertView,
								  ViewGroup container) {
			if (convertView == null) {
				convertView = (TextView) inflater.inflate(R.layout.tab_main,
						container, false);
			}

			TextView textView = (TextView) convertView;
			textView.setText(tabNames[position]);

			return textView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			Fragment fragment = null;
			switch (position) {
				case 0:
					fragment = new GroupFragment();
					break;
				case 1:
					fragment = new GameFragment();
					break;
				case 2:
					fragment = new MineFragment();
					break;
				default:
					break;
			}
			return fragment;
		}
	}

}
