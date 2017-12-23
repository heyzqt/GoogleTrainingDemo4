package com.heyzqt.googletrainingdemo4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by heyzqt on 12/23/2017.
 */

public class BitmapWorkerTask extends AsyncTask {

	private static final String TAG = "BitmapWorkerTask";

	private final WeakReference imageViewReference;

	private final Resources mResources;

	public BitmapWorkerTask(Resources res, ImageView imageView) {
		imageViewReference = new WeakReference(imageView);
		mResources = res;
	}

	@Override
	protected void onPreExecute() {
		Log.i(TAG, "onPreExecute: ");
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Object[] values) {
		Log.i(TAG, "onProgressUpdate: ");
		super.onProgressUpdate(values);
	}

	@Override
	protected void onCancelled() {
		Log.i(TAG, "onCancelled: ");
		super.onCancelled();
	}

	@Override
	protected Object doInBackground(Object[] objects) {
		if (objects[0] != null) {
			return MainActivity.decodeSampledBitmapFromResource(mResources, (Integer) objects[0],
					100, 100);
		}
		return null;
	}

	@Override
	protected void onPostExecute(Object o) {
		Log.i(TAG, "onPostExecute: ");
		Bitmap bitmap = (Bitmap) o;
		if (bitmap != null && imageViewReference != null) {
			final ImageView imageView = (ImageView) imageViewReference.get();
			imageView.setImageBitmap(bitmap);
		}
		super.onPostExecute(o);

	}
}
