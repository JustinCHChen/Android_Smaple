package example.com.test;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by justin on 8/5/17.
 */
public class TestApplication extends Application {

    private static TestApplication sInstance;
    private Resources mThemeResource;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;

        String apkPath = Environment.getExternalStorageDirectory() + "/app-debug.apk";

        File file = new File(apkPath);
        if (file.exists()) {
            Log.d("justin++", "exists");
        } else {
            Log.d("justin++", "not exists");
        }
        mThemeResource = getBundleResource(this, apkPath);
//        mThemeResource = getResourceByInstalledApp();
//        mThemeResource = getResources(this.getApplicationContext(), apkPath);

//        mThemeResource = Util.getPluginResources(this, apkPath);
    }

    public static TestApplication getInstance() {
        return sInstance;
    }

    public Resources getThemeResource() {
        return mThemeResource;
    }

    private Resources getResourceByInstalledApp() {
        try {
            return getPackageManager().getResourcesForApplication("example.com.themeresource");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Resources getBundleResource(Context context, String apkPath) {
        AssetManager assetManager = createAssetManager(apkPath);
        return new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    }

    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            AssetManager.class.getDeclaredMethod("addAssetPath", String.class).invoke(
                assetManager, apkPath);
            return assetManager;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }
}
