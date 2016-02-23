package com.example.justin_chen.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;

import java.io.File;

/**
 * Created by justin_chen on 2016/2/23.
 */
public class DrawableImageCache implements ImageLoader.ImageCache {
    private static final int DEFAULT_MEM_CACHE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final int IMAGE_CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final String DISK_CACHE_DIR = "cache_dir";

    private LruCache<String, Bitmap> mMemCache;
    private DiskBasedCache mDiskLruCache;

    public DrawableImageCache(Context context) {
        //Memory cache
        mMemCache = new LruCache<String, Bitmap>(DEFAULT_MEM_CACHE_SIZE) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        //Disk cache
        File cacheDir = getDiskCacheDir(context, DISK_CACHE_DIR);
        mDiskLruCache = new DiskBasedCache(cacheDir, IMAGE_CACHE_SIZE);
        mDiskLruCache.initialize();
    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        final String cachePath = context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = getBitmapDrawableFromMemCache(url);

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

        mMemCache.put(data, bitmap);
    }

    private void addBitmapToDiskCache(String data, Bitmap value) {
        if (data == null || value == null || mDiskLruCache == null) {
            return;
        }

        AsyncPutImageCacheTask task = new AsyncPutImageCacheTask(mDiskLruCache, data, value);
        task.execute(); // SERIAL_EXECUTOR
    }

    private Bitmap getBitmapDrawableFromMemCache(String data) {
        Bitmap memValue = null;

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
