package com.example.bestie.storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader extends AsyncTask<Void, Void, Bitmap> {
    private static Map<String, Bitmap> cache = new HashMap<>();

    private Context mContext;
    private String mUrl;
    private ImageView mView;

    public ImageDownloader(Context context, String url, ImageView view) {
        mContext = context;
        mUrl = url;
        mView = view;
    }

    public ImageDownloader(String url, ImageView view) {
        mUrl = url;
        mView = view;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        try {
            if (cache.containsKey(mUrl)) {
                return cache.get(mUrl);
            }
            InputStream stream = new URL(mUrl).openConnection().getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(stream);

            Thread.sleep(100);
            cache.put(mUrl, bitmap);

            return bitmap;
        } catch (IOException e) {
            return null;
        } catch (InterruptedException e) {

        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            super.onPostExecute(bitmap);
            mView.setImageBitmap(bitmap);
        }
    }
}
