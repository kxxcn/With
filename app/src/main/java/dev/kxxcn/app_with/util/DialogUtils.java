package dev.kxxcn.app_with.util;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import dev.kxxcn.app_with.R;

/**
 * Created by kxxcn on 2018-08-21.
 */
public class DialogUtils {
	public static void showAlertDialog(Context context, String message, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton(context.getString(R.string.dialog_yes), positiveListener)
				.setNegativeButton(context.getString(R.string.dialog_no), negativeListener).show();
	}

	public static void showTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener timeSetListener, String title) {
		Date hour = new Date();
		String hourOfDay = new SimpleDateFormat("HH", Locale.KOREA).format(hour);
		Context instance = new ContextThemeWrapper(context, android.R.style.Theme_Holo_Light_Dialog);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			instance = context;
		}
		TimePickerDialog timePickerDialog = new TimePickerDialog(instance, android.R.style.Theme_Holo_Dialog, timeSetListener,
				Integer.parseInt(hourOfDay), 0, false);
		Objects.requireNonNull(timePickerDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
		timePickerDialog.setTitle(title);
		timePickerDialog.show();
	}
}
