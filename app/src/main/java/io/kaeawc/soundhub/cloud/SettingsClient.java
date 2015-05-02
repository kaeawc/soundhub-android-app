package io.kaeawc.soundhub.cloud;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;
import io.kaeawc.soundhub.UserPreferences;
import io.kaeawc.soundhub.events.Result;
import io.kaeawc.soundhub.events.settings.SettingsRequest;
import io.kaeawc.soundhub.events.settings.SettingsResponse;
import timber.log.Timber;

public class SettingsClient {

    public static void request(@NonNull SettingsRequest request) {

        switch (request.getOperation()) {
            case Get:
                if (!request.getSettings().isEmpty()) {
                    get(request);
                } else {
                    Timber.w("Settings Get Request had no settings keys, returning defaults");

                    Result result = Result.Success;
                    SettingsResponse response = new SettingsResponse(request, result, UserPreferences.defaults);
                    EventBus.getDefault().post(response);
                }
                break;
            case Save:
                save(request);
                break;
            default:
                throw new IllegalArgumentException("Unsupported settings request");
        }
    }

    public static void get(@NonNull SettingsRequest request) {
        Timber.d("Fake http request to getSettings");

        Result result = Result.Success;
        SettingsResponse response = new SettingsResponse(request, result, UserPreferences.defaults);
        EventBus.getDefault().post(response);
    }

    public static void save(@NonNull SettingsRequest request) {
        Timber.d("Fake http request to saveSettings");

        Map<String, Object> settings = new HashMap<>();
        Map<String, Object> changes = request.getChanges();

        for (String key : changes.keySet()) {

            Object newValue = changes.get(key);

            if (newValue == null) {
                continue;
            }

            settings.put(key, newValue);
        }

        Result result = Result.Success;
        SettingsResponse response = new SettingsResponse(request, result, settings);
        EventBus.getDefault().post(response);
    }
}