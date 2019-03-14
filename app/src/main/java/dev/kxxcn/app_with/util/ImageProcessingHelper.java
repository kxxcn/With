package dev.kxxcn.app_with.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by kxxcn on 2018-08-23.
 */
public class ImageProcessingHelper {

	public static synchronized ArrayList<Bitmap> convertToBitmap(Context context, Constants.TypeFilter typeFilter, int[] resources, ArrayList<String> thumbsData, int req_width, int req_height) {
		final ArrayList<Bitmap> imgList = new ArrayList<>(0);
		final ArrayList<String> copyToThumbsData = new ArrayList<>(0);
		if (typeFilter == Constants.TypeFilter.GALLERY) {
			copyToThumbsData.addAll(thumbsData);
			for (String thumb : copyToThumbsData) {
				imgList.add(decodeBitmapFromFile(thumb, req_width, req_height));
			}
		} else {
			for (int res : resources) {
				imgList.add(decodeBitmapFromResource(context, res, req_width, req_height, typeFilter));
			}
		}

		return imgList;
	}

	public static Bitmap convertToBitmap(Context context, Uri uri, int reqWidth, int reqHeight) throws IOException {
		return decodeBitmapFromUri(context, uri, reqWidth, reqHeight);
	}

	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	private static Bitmap decodeBitmapFromFile(String pathName, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);

		options.inJustDecodeBounds = false;
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inDither = true;

		return BitmapFactory.decodeFile(pathName, options);
	}

	private static Bitmap decodeBitmapFromUri(Context context, Uri imageUri, int reqWidth, int reqHeight) throws FileNotFoundException {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inDither = true;

		InputStream iStream = context.getContentResolver().openInputStream(imageUri);
		return BitmapFactory.decodeStream(iStream, null, options);
	}

	private static Bitmap decodeBitmapFromResource(Context context, int res, int reqWidth, int reqHeight, Constants.TypeFilter typeFilter) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		if (typeFilter != Constants.TypeFilter.FONT) {
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(context.getResources(), res, options);
			options.inJustDecodeBounds = false;
		}
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inDither = true;

		return BitmapFactory.decodeResource(context.getResources(), res, options);
	}

	public static <T> void setGlide(Context context, T t, ImageView view, RequestOptions options) {
		Glide.with(context).load(t).apply(options).into(view);
	}

	public static String convertToJPEG(Context context, Bitmap bitmap, String name) {
		File storage = context.getCacheDir();

		String fileName = name + ".jpg";
		File tempFile = new File(storage, fileName);
		try {
			if (tempFile.createNewFile()) {
				FileOutputStream out = new FileOutputStream(tempFile);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
				out.close();
			}
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}

		return tempFile.getAbsolutePath();
	}

}
