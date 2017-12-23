package com.heyzqt.googletrainingdemo4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

	private ImageView mImageView;

	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	private void init() {
		mImageView = findViewById(R.id.imageview);

		BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(getResources(), mImageView);
		bitmapWorkerTask.execute(R.drawable.img);

//		mImageView.setImageBitmap(setSuitableBitmap(getResources(), R.drawable.img,
//				100, 100));

		//get a picture which width and height is near to 100px
//		mImageView.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.img,
// 100, 100));
	}

	private Bitmap setSuitableBitmap(Resources res, int resId, int reqWidth, int reqHeight) {
		//Get the ImageView's dimensions
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		//According the dimensions and reqWidth and reqHeight to calculate inSampleSize
		int tempSampleSize = calculateSampleSize(options, reqWidth, reqHeight);

		//ImageView set the bitmap, P.S. need to load the bitmap to the memory
		options.inJustDecodeBounds = false;
		options.inSampleSize = tempSampleSize;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	private int calculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		int outWidth = options.outWidth;
		int outHeight = options.outHeight;
		int sampleSize = 1;

		while (outWidth / sampleSize > reqWidth && outHeight / sampleSize > reqHeight) {
			sampleSize *= 2;
		}

		Log.i(TAG, "calculateSampleSize: sample size = " + sampleSize);
		return sampleSize;
	}

	private static int calculateInSampleSize(BitmapFactory.Options opt, int reqWidth,
			int reqHeight) {
		int inSampleSize = 1;
		int width = opt.outWidth;
		int height = opt.outHeight;

		if (width > reqWidth || height > reqHeight) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			while ((halfWidth / inSampleSize) > reqWidth
					&& (halfHeight / inSampleSize) > reqHeight) {
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
