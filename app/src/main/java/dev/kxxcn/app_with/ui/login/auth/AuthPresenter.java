package dev.kxxcn.app_with.ui.login.auth;

import dev.kxxcn.app_with.data.DataRepository;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by kxxcn on 2018-08-29.
 */
public class AuthPresenter implements AuthContract.Presenter {

	private AuthContract.View mAuthView;
	private DataRepository mDataRepository;

	public AuthPresenter(AuthContract.View authView, DataRepository dataRepository) {
		this.mAuthView = authView;
		this.mDataRepository = dataRepository;
		mAuthView.setPresenter(this);
	}

	@Override
	public void createPairingKey(String uniqueIdentifier, String token) {
		if (mAuthView == null)
			return;

		mAuthView.showLoadingIndicator(true);

		CompositeDisposable compositeDisposable = new CompositeDisposable();

		Disposable disposable = mDataRepository.createPairingKey(uniqueIdentifier, token)
				.subscribe(response -> {
					mAuthView.showSuccessfulPairingKeyRequest(response.getKey());
					mAuthView.showLoadingIndicator(false);
					compositeDisposable.dispose();
				}, throwable -> mAuthView.showFailedRequest(throwable.getMessage()));

		compositeDisposable.add(disposable);
	}

	@Override
	public void authenticate(String uniqueIdentifier, String key, int gender, String token) {
		if (mAuthView == null)
			return;

		mAuthView.showLoadingIndicator(true);

		CompositeDisposable compositeDisposable = new CompositeDisposable();

		Disposable disposable = mDataRepository.authenticate(uniqueIdentifier, key, gender, token)
				.subscribe(response -> {
					if (response.getRc() == 200) {
						mAuthView.showSuccessfulAuthenticate();
					} else if (response.getRc() == 201) {
						mAuthView.showFailedAuthenticate(response.getStat());
					}
					mAuthView.showLoadingIndicator(false);
					compositeDisposable.dispose();
				}, throwable -> mAuthView.showFailedRequest(throwable.getMessage()));

		compositeDisposable.add(disposable);
	}

}
