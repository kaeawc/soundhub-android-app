package io.kaeawc.soundhub;

import android.content.Context;
import android.app.Application;
import ollie.Ollie;
import timber.log.Timber;

public class App extends Application {

    public static final String DB_NAME = "SoundHub";
    public static final int DB_VERSION = 1;
    public static final int CACHE_SIZE = 1024;

    @Override
    public void onCreate() {
        Context context = getApplicationContext();

        if (context != null) {
//            Ollie.with(context)
//                    .setName(DB_NAME)
//                    .setVersion(DB_VERSION)
//                    .setLogLevel(Ollie.LogLevel.FULL)
//                    .setCacheSize(CACHE_SIZE)
//                    .init();
        }

        Timber.plant(new Timber.DebugTree());
    }
}
