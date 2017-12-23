package com.heyzqt.googletrainingdemo4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by heyzqt on 12/23/2017.
 */

public class BitmapWorkerTask extends AsyncTask {

	private static final String TAG = "BitmapWorkerTask";

	private final WeakReference imageViewReference;

	private final Resources mResources;

	public String uri;

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
		uri = (String) objects[0];
		if (objects[0] != null) {
			return getImageBitmap((String) objects[0]);
//			return MainActivity.decodeSampledBitmapFromResource(mResources, (Integer) objects[0],
//					100, 100);
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

	public Bitmap getImageBitmap(String url) {
		URL imgUrl = null;
		Bitmap bitmap = null;
		try {
			imgUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) imgUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
}
