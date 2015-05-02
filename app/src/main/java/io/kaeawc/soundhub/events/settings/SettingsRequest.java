package io.kaeawc.soundhub.events.settings;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.kaeawc.soundhub.UserPreferences;
import io.kaeawc.soundhub.events.Operation;

public class SettingsRequest {

    private @NonNull Set<String> mSettings;
    private @NonNull Map<String, Object> mChanges;
    private Operation mOperation;

    public SettingsRequest(@NonNull Builder builder) {
        this.mSettings = builder.mSettings;
        this.mChanges = builder.mChanges;
        this.mOperation = builder.mOperation;
    }

    public Object getNewValue(String key) {
        return this.mChanges.get(key);
    }

    public Set<String> getSettings() {
        return this.mSettings;
    }

    public Map<String, Object> getChanges() {
        return this.mChanges;
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public static class Builder {

        protected Set<String> mSettings;
        protected Map<String, Object> mChanges;
        protected Operation mOperation;

        public SettingsRequest build() {
            return new SettingsRequest(this);
        }
    }

    public static class Get extends Builder {

        public Get() {

            this.mChanges = new HashMap<>();
            this.mSettings = UserPreferences.defaults.keySet();
            this.mOperation = Operation.Get;
        }

        public Get(@NonNull Set<String> settings) {

            this.mChanges = new HashMap<>();
            this.mSettings = settings;
            this.mOperation = Operation.Get;
        }

        public SettingsRequest build() {
            return new SettingsRequest(this);
        }
    }

    public static class Save extends Builder {

        public Save(@NonNull Map<String, Object> changes) {

            this.mChanges = changes;
            this.mSettings = mChanges.keySet();
            this.mOperation = Operation.Save;
        }

        public SettingsRequest build() {
            return new SettingsRequest(this);
        }
    }

    public static class Delete extends Builder {

        public Delete(@NonNull Set<String> settings) {

            this.mChanges = new HashMap<>();
            this.mSettings = settings;
            this.mOperation = Operation.Delete;
        }

        public SettingsRequest build() {
            return new SettingsRequest(this);
        }
    }
}