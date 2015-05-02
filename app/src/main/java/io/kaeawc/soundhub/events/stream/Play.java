package io.kaeawc.soundhub.events.stream;

public class Play {

    private long timestamp;

    public Play() {
        timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
