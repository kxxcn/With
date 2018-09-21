package dev.kxxcn.app_with.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dev.kxxcn.app_with.ui.login.gender.GenderFragment;
import dev.kxxcn.app_with.ui.main.letter.female.FemaleFragment;
import dev.kxxcn.app_with.ui.main.letter.male.MaleFragment;
import dev.kxxcn.app_with.ui.main.plan.PlanFragment;
import dev.kxxcn.app_with.ui.main.setting.SettingFragment;
import dev.kxxcn.app_with.ui.main.write.WriteFragment;

/**
 * Created by kxxcn on 2018-08-13.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

	private static final int COUNT = 5;

	public static final int PLAN = 0;
	public static final int FEMALE = 1;
	public static final int WRITE = 2;
	public static final int MALE = 3;
	public static final int SETTING = 4;

	private boolean gender;
	private String identifier;

	private MainContract.OnPageChangeListener onPageChangeListener;

	private MainContract.OnRegisteredDiary onRegisteredDiary;

	public MainPagerAdapter(FragmentManager fm, int gender, String identifier, MainContract.OnPageChangeListener onPageChangeListener, MainContract.OnRegisteredDiary onRegisteredDiary) {
		super(fm);
		this.gender = gender == GenderFragment.FEMALE;
		this.identifier = identifier;
		this.onPageChangeListener = onPageChangeListener;
		this.onRegisteredDiary = onRegisteredDiary;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case PLAN:
				return PlanFragment.newInstance();
			case FEMALE:
				FemaleFragment femaleFragment = FemaleFragment.newInstance(gender, identifier);
				femaleFragment.setOnPageChangeListener(onPageChangeListener);
				return femaleFragment;
			case WRITE:
				WriteFragment writeFragment = WriteFragment.newInstance(gender, identifier);
				writeFragment.setOnPageChangeListener(onPageChangeListener);
				writeFragment.setOnRegisteredDiary(onRegisteredDiary);
				return writeFragment;
			case MALE:
				MaleFragment maleFragment = MaleFragment.newInstance(!gender, identifier);
				maleFragment.setOnPageChangeListener(onPageChangeListener);
				return maleFragment;
			case SETTING:
				return SettingFragment.newInstance();
		}
		return null;
	}

	@Override
	public int getCount() {
		return COUNT;
	}

}
