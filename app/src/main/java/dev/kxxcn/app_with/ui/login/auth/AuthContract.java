package dev.kxxcn.app_with.ui.login.auth;

import dev.kxxcn.app_with.ui.BasePresenter;
import dev.kxxcn.app_with.ui.BaseView;

/**
 * Created by kxxcn on 2018-08-29.
 */
public interface AuthContract {
	interface View extends BaseView<Presenter> {
		void showSuccessfulPairingKeyRequest(String key);

		void showFailuredRequest(String throwable);

		void showSuccessfulAuthenticate();

		void showFailuredAuthenticate(String stat);
	}

	interface Presenter extends BasePresenter {
		void onCreatePairingKey(String uniqueIdentifier);

		void onAuthenticate(String uniqueIdentifier, String key, int gender);
	}
}