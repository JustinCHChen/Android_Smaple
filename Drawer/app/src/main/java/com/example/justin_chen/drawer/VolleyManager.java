package com.example.justin_chen.drawer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by justin_chen on 2016/2/4.
 */
public class VolleyManager implements ImageLoader.ImageCache {
    private static final int DEFAULT_MEM_CACHE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final int IMAGE_CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final String DISK_CACHE_SUBDIR = "photo_cache";

    private static VolleyManager mInstance;

    private Context mContext;
    private Resources mResources;

    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;

    private LruCache<String, BitmapDrawable> mMemCache;
    private DiskBasedCache mDiskLruCache;

    private VolleyManager(Context context) {
        //TODO weakreference
        mContext = context;
        mResources = context.getResources();

        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, this);

        initCache();
    }

    private void initCache() {
        //Memory cache
        mMemCache = new LruCache<String, BitmapDrawable>(DEFAULT_MEM_CACHE_SIZE) {
            @Override
            protected int sizeOf(String key, BitmapDrawable value) {
                Bitmap bitmap = value.getBitmap();
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };

        //Disk cache
        File cacheDir = getDiskCacheDir(mContext, DISK_CACHE_SUBDIR);
        mDiskLruCache = new DiskBasedCache(cacheDir, IMAGE_CACHE_SIZE);
        mDiskLruCache.initialize();
    }

    public static synchronized VolleyManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyManager(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        final String cachePath = context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    @Override
    public Bitmap getBitmap(String url) {
        BitmapDrawable bitmapDrawable;
        Bitmap bitmap = null;
        bitmapDrawable = getBitmapDrawableFromMemCache(url);

        if (bitmapDrawable != null) {
            bitmap = bitmapDrawable.getBitmap();
        }

        if (bitmap == null) {
            bitmap = getBitmapFromDiskCache(url);
            addBitmapToMemCache(url, bitmap);
        }
        return bitmap;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        addBitmapToMemCache(url, bitmap);
        addBitmapToDiskCache(url, bitmap);
    }

    private void addBitmapToMemCache(String data, Bitmap bitmap) {
        if (data == null || bitmap == null || mMemCache == null) {
            return;
        }

        RecyclingBitmapDrawable drawable = new RecyclingBitmapDrawable(mResources, bitmap);
        drawable.setIsCached(true);

        mMemCache.put(data, drawable);
    }

    private void addBitmapToDiskCache(String data, Bitmap value) {
        if (data == null || value == null || mDiskLruCache == null) {
            return;
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        value.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        Cache.Entry entry = new Cache.Entry();
        entry.data = byteArray;
        mDiskLruCache.put(data, entry);
    }

    private BitmapDrawable getBitmapDrawableFromMemCache(String data) {
        BitmapDrawable memValue = null;

        if (mMemCache != null) {
            memValue = mMemCache.get(data);
        }

        return memValue;
    }

    private Bitmap getBitmapFromDiskCache(String data) {
        Bitmap bitmap = null;

        if (mDiskLruCache != null) {
            Cache.Entry entry = mDiskLruCache.get(data);
            if (entry != null) {
                byte[] result = entry.data;
                bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
            }
        }

        return bitmap;
    }
}
