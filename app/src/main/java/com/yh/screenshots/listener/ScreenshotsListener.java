package com.yh.screenshots.listener;

import android.annotation.SuppressLint;
import android.os.FileObserver;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yh.screenshots.message.MessageWrap;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

/**
 * @author yh
 */
public class ScreenshotsListener extends FileObserver {

    private String filePath;

    public ScreenshotsListener(@NonNull File file) {
        super(file);
        this.filePath = file.getPath();
        Log.i("ScreenshotsListener", file.getPath());
    }

    @SuppressLint("ShowToast")
    @Override
    public void onEvent(int event, @Nullable String path) {
        Log.i("ScreenshotsListener", "event");
        switch (event) {
            case FileObserver.ALL_EVENTS:
                Log.d("all", "path:" + path);
                break;
            case FileObserver.CREATE:
                Log.d("Create", "path:" + filePath +"/"+ path);
                EventBus.getDefault().post(MessageWrap.getInstance( filePath +"/"+ path));
                break;
            default:
                Log.d("default", "path:" + path);
                break;

        }
    }


}
