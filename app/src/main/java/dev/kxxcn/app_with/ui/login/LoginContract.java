package dev.kxxcn.app_with.ui.login;

import android.app.Activity;

import dev.kxxcn.app_with.ui.BasePresenter;
import dev.kxxcn.app_with.ui.BaseView;

/**
 * Created by kxxcn on 2018-08-23.
 */
public interface LoginContract {
	interface View extends BaseView<Presenter> {

	}

	interface Presenter extends BasePresenter {
		void setPermission(Activity activity, String... permission);
	}
}
