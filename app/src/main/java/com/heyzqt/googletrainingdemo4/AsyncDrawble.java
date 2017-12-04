package com.heyzqt.googletrainingdemo4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.lang.ref.WeakReference;

/**
 * Created by heyzqt on 12/4/2017.
 */

public class AsyncDrawble extends BitmapDrawable {

	private final WeakReference bitmapWorkTaskReference;

	public AsyncDrawble(Resources res, Bitmap bitmap, BitmapAsyncTask bitmapAsyncTask) {
		bitmapWorkTaskReference = new WeakReference(bitmapAsyncTask);
	}

	public BitmapAsyncTask getBitmapAsyncTask() {
		return (BitmapAsyncTask) bitmapWorkTaskReference.get();
	}
}
