package com.heyzqt.googletrainingdemo4;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by heyzqt on 12/4/2017.
 */

public class BitmapAsyncTask extends AsyncTask {

	private final WeakReference mWeakReference;
	private int data = 0;
	private Context mContext;
	private int reqWidth;
	private int reqHeight;

	public BitmapAsyncTask(Context context, ImageView imageView, int reqWidth, int reqHeight) {
		mWeakReference = new WeakReference(imageView);
		mContext = context;
		this.reqWidth = reqWidth;
		this.reqHeight = reqHeight;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Object o) {
		super.onPostExecute(o);
		Bitmap bitmap = (Bitmap) o;
		if (mWeakReference != null && bitmap != null) {
			final ImageView imageView = (ImageView) mWeakReference.get();
			if (imageView != null) {
				imageView.setImageBitmap(bitmap);
			}
		}
	}

	@Override
	protected Object doInBackground(Object[] objects) {
		data = (int) objects[0];
		return MainActivity.decodeSampledBitmapFromResource(mContext.getResources(), data, reqWidth, reqHeight);
	}
}
