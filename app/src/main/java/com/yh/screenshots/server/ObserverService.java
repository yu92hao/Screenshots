package com.yh.screenshots.server;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.yh.screenshots.listener.ScreenshotsListener;

import java.io.File;

/**
 * @author yh
 */
@SuppressLint("Registered")
public class ObserverService extends Service {
    private static final String TAG = "ObserverService";
    private ScreenshotsListener screenshotsListener;

    /**
     * path地址
     */
    String path = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM + File.separator + "Screenshots" + File.separator;
//    String path = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_PICTURES + File.separator + "Screenshots" + File.separator;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("path=", path);
        screenshotsListener = new ScreenshotsListener(new File(path));
        Log.d(TAG, "onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand executed");
        screenshotsListener.startWatching();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy execute");
        screenshotsListener.stopWatching();

    }
    public static void start(Context ctx) {
        Intent i = new Intent(ctx, ObserverService.class);
        ctx.startService(i);
    }
}
