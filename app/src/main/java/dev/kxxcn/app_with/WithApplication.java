package dev.kxxcn.app_with;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import dev.kxxcn.app_with.util.AppStatusHelper;

/**
 * Created by kxxcn on 2018-08-13.
 */
public class WithApplication extends Application {

	public static boolean DEBUG = false;

	@Override
	public void onCreate() {
		super.onCreate();
		DEBUG = isDebuggable(this);
		FirebaseApp.initializeApp(this);
		AppStatusHelper.init(this);
		FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.app_topic));
	}

	private boolean isDebuggable(Context context) {
		boolean debuggable = false;
		PackageManager pm = context.getPackageManager();
		try {
			ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), 0);
			debuggable = (0 != (appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
		} catch (PackageManager.NameNotFoundException e) {
			/* debuggable variable will remain false */
		}
		return debuggable;
	}

}
