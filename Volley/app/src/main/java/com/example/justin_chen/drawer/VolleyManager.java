package com.example.justin_chen.drawer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by justin_chen on 2016/2/4.
 */
public class VolleyManager {
    private static VolleyManager mInstance;

    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;

    private VolleyManager(Context context) {
        DrawableImageCache imageCache = new DrawableImageCache(context);

        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, imageCache);
    }

    public static synchronized VolleyManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyManager(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        mRequestQueue.add(req);
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
