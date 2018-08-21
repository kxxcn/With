package dev.kxxcn.app_with.ui.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.kxxcn.app_with.R;
import dev.kxxcn.app_with.util.DialogUtils;
import dev.kxxcn.app_with.util.SwipeViewPager;
import dev.kxxcn.app_with.util.SystemUtils;

/**
 * Created by kxxcn on 2018-08-13.
 */
public class MainActivity extends AppCompatActivity {

	@BindView(R.id.vp_main)
	SwipeViewPager vp_main;

	@BindView(R.id.bottomBar)
	BottomBar bottomBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		vp_main.setPagingEnabled(false);
		vp_main.setOffscreenPageLimit(2);
		vp_main.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

		bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelected(int tabId) {
				switch (tabId) {
					case R.id.tab_plan:
						vp_main.setCurrentItem(MainPagerAdapter.PLAN);
						break;
					case R.id.tab_girl:
						vp_main.setCurrentItem(MainPagerAdapter.GIRL);
						break;
					case R.id.tab_write:
						vp_main.setCurrentItem(MainPagerAdapter.WRITE);
						break;
					case R.id.tab_boy:
						vp_main.setCurrentItem(MainPagerAdapter.BOY);
						break;
					case R.id.tab_setting:
						vp_main.setCurrentItem(MainPagerAdapter.SETTING);
						break;
				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		DialogUtils.showAlertDialog(this, getString(R.string.dialog_want_to_quit), positiveListener, null);
	}

	DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			SystemUtils.onFinish(MainActivity.this);
		}
	};

}
