package dev.kxxcn.app_with.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.kxxcn.app_with.R;
import dev.kxxcn.app_with.ui.login.gender.GenderFragment;
import dev.kxxcn.app_with.ui.main.letter.female.FemaleFragment;
import dev.kxxcn.app_with.ui.main.letter.male.MaleFragment;
import dev.kxxcn.app_with.util.DialogUtils;
import dev.kxxcn.app_with.util.SwipeViewPager;
import dev.kxxcn.app_with.util.SystemUtils;
import dev.kxxcn.app_with.util.TransitionUtils;
import dev.kxxcn.app_with.util.threading.UiThread;

import static dev.kxxcn.app_with.util.Constants.DELAY_REGISTRATION;

/**
 * Created by kxxcn on 2018-08-13.
 */
public class MainActivity extends AppCompatActivity {

	public static final String EXTRA_GENDER = "GENDER";
	public static final String EXTRA_IDENTIFIER = "IDENTIFIER";

	@BindView(R.id.vp_main)
	SwipeViewPager vp_main;

	@BindView(R.id.bottomBar)
	BottomBar bottomBar;

	private MainPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TransitionUtils.fade(this);
		ButterKnife.bind(this);

		adapter = new MainPagerAdapter(getSupportFragmentManager(), getIntent().getIntExtra(EXTRA_GENDER, GenderFragment.MALE),
				getIntent().getStringExtra(EXTRA_IDENTIFIER), type -> {
			vp_main.setCurrentItem(type);
			bottomBar.selectTabAtPosition(type);
		}, (isFemale, type) -> {
			if (isFemale) {
				FemaleFragment femaleFragment = (FemaleFragment) adapter.getItem(MainPagerAdapter.FEMALE);
				femaleFragment.onRegisteredDiary();
			} else {
				MaleFragment maleFragment = (MaleFragment) adapter.getItem(MainPagerAdapter.MALE);
				maleFragment.onRegisteredDiary();
			}
			UiThread.getInstance().postDelayed(() -> {
				vp_main.setCurrentItem(type);
				bottomBar.selectTabAtPosition(type);
			}, DELAY_REGISTRATION);
		});

		vp_main.setPagingEnabled(false);
		vp_main.setOffscreenPageLimit(3);
		vp_main.setAdapter(adapter);

		bottomBar.setActiveTabColor(getResources().getColor(R.color.tab_active));
		bottomBar.setOnTabSelectListener(tabId -> {
			switch (tabId) {
				case R.id.tab_plan:
					vp_main.setCurrentItem(MainPagerAdapter.PLAN);
					break;
				case R.id.tab_girl:
					vp_main.setCurrentItem(MainPagerAdapter.FEMALE);
					break;
				case R.id.tab_write:
					vp_main.setCurrentItem(MainPagerAdapter.WRITE);
					break;
				case R.id.tab_boy:
					vp_main.setCurrentItem(MainPagerAdapter.MALE);
					break;
				case R.id.tab_setting:
					vp_main.setCurrentItem(MainPagerAdapter.SETTING);
					break;
			}
		});
	}

	@Override
	public void onBackPressed() {
		DialogUtils.showAlertDialog(this, getString(R.string.dialog_want_to_quit), (dialog, which) -> SystemUtils.onFinish(MainActivity.this), null);
	}

}
