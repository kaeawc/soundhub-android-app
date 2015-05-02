package io.kaeawc.soundhub;

import java.util.HashMap;
import java.util.Map;

public class UserPreferences {

    public static final String EMAIL = "email";
    public static final String DATA_STREAMING = "push_notifications";

    public static final String DEFAULT_EMAIL = "Enter your email here";
    public static final String DEFAULT_PUSH_NOTIFICATIONS = "w";

    public static Map<String, Object> getDefaultMap() {
        Map<String, Object> settings = new HashMap<>();
        settings.put(EMAIL, DEFAULT_EMAIL);
        settings.put(DATA_STREAMING, DEFAULT_PUSH_NOTIFICATIONS);
        return settings;
    }

    public static final Map<String, Object> defaults = getDefaultMap();
}
