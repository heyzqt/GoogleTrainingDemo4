package com.heyzqt.googletrainingdemo4;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		BitmapFactory.Options mBitmapOptions = new BitmapFactory.Options();
		mBitmapOptions.inJustDecodeBounds = true;
		System.out.println("before width = " + mBitmapOptions.outWidth + ",height = " + mBitmapOptions.outHeight);
		BitmapFactory.decodeResource(getResources(), R.drawable.img, mBitmapOptions);
		System.out.println("width = " + mBitmapOptions.outWidth);
		System.out.println("height = " + mBitmapOptions.outHeight);
	}
}
