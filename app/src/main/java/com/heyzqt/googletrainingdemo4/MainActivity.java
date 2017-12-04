package com.heyzqt.googletrainingdemo4;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

	private ImageView mImageView;

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;
		init();
	}

	private void init() {
		mImageView = findViewById(R.id.imageview);

		//test 1: show a picture through method decodeSampledBitmapFromResource()
		//get a picture which width and height is near to 100px
		//mImageView.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.img, 100, 100));

		//test 2: show a picture through BitmapAsyncTask
		BitmapAsyncTask bitmapAsyncTask = new BitmapAsyncTask(mContext, mImageView, 100, 100);
		bitmapAsyncTask.execute(R.drawable.img);
	}

	public static int calculateInSampleSize(BitmapFactory.Options opt, int reqWidth, int reqHeight) {
		int inSampleSize = 1;
		int width = opt.outWidth;
		int height = opt.outHeight;

		if (width > reqWidth || height > reqHeight) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			while ((halfWidth / inSampleSize) > reqWidth && (halfHeight / inSampleSize) > reqHeight) {
				inSampleSize *= 2;
			}
		}
		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
			int reqWidth, int reqHeight) {
		//get the dimensions of the picture
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		int width = options.outWidth;
		int height = options.outHeight;

		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}
}
