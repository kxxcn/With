package dev.kxxcn.app_with.ui.main.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.kxxcn.app_with.R;

/**
 * Created by kxxcn on 2018-08-13.
 */
public class SettingFragment extends Fragment {

	public static SettingFragment newInstance() {
		return new SettingFragment();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setting, container, false);
		ButterKnife.bind(this, view);

		return view;
	}

	@OnClick({R.id.tv_profile, R.id.iv_profile})
	public void onProfile() {

	}

	@OnClick({R.id.tv_notice, R.id.iv_notice})
	public void onNotice() {

	}

	@OnClick({R.id.tv_information, R.id.iv_information})
	public void onInformation() {

	}

	@OnClick({R.id.tv_sign_out, R.id.iv_sign_out})
	public void onSignOut() {

	}

}
