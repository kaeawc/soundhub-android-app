package io.kaeawc.soundhub.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import de.greenrobot.event.EventBus;
import io.kaeawc.soundhub.cloud.SettingsClient;
import io.kaeawc.soundhub.events.settings.SettingsRequest;
import timber.log.Timber;

public class BackgroundService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private static boolean RUNNING = false;
    private static BackgroundService sInstance = null;

    public static boolean isRunning() {
        return (sInstance != null && RUNNING);
    }

    @Override
    public void onCreate() {
        Timber.i("onCreate");
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        super.onCreate();
        Timber.i("Service created");
        onStartup();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.i("onStartCommand");
        Timber.i("Received command to start");
        if (!RUNNING) {
            onStartup();
        }

        return START_STICKY;
    }

    public void onStartup() {
        Timber.i("Starting...");
        Context context = getApplicationContext();

        if (context == null) {
            Timber.i("Cannot create this service without an application context");
            return;
        }

        Timber.i("Running...");
        sInstance = this;
        RUNNING = true;
    }

    @Override
    public void onDestroy() {
        Timber.d("onDestroy");
        sInstance = null;

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

        RUNNING = false;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Timber.i("onBind");
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public BackgroundService getService() {
            Timber.i("Service request received");
            return BackgroundService.this;
        }
    }

    public void onEvent(SettingsRequest request) {

        if (request == null) {
            return;
        }

        SettingsClient.request(request);
    }
}