package com.heyzqt.googletrainingdemo4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.lang.ref.WeakReference;

/**
 * Created by heyzqt on 12/23/2017.
 *
 * use AsyncDrawble to save BitmapWorkerTask Reference
 */

public class AsyncDrawble extends BitmapDrawable {

	private final WeakReference<BitmapWorkerTask> mWeakReference;

	public AsyncDrawble(Resources res, Bitmap bitmap, BitmapWorkerTask bitmapWorkerTask) {
		super(res, bitmap);
		mWeakReference = new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
	}

	public BitmapWorkerTask getBitmapworkerTask() {
		return mWeakReference.get();
	}
}
