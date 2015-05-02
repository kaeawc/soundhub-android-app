package io.kaeawc.soundhub.events.settings;

import android.support.annotation.NonNull;

import java.util.Map;

import io.kaeawc.soundhub.events.Result;

public class SettingsResponse {

    public SettingsRequest mRequest;
    public Result mResult;
    public Map<String, Object> mSettings;

    public SettingsResponse(
            @NonNull SettingsRequest request,
            @NonNull Result result,
            @NonNull Map<String, Object> settings) {

        this.mRequest = request;
        this.mResult = result;
        this.mSettings = settings;
    }

    public SettingsRequest getRequest() {
        return this.mRequest;
    }

    public Result getResult() {
        return this.mResult;
    }

    public Map<String, Object> getSettings() {
        return this.mSettings;
    }
}