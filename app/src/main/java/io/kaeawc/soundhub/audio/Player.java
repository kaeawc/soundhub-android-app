package io.kaeawc.soundhub.audio;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import io.kaeawc.soundhub.BuildConfig;
import timber.log.Timber;

public class Player {
    private static Set<MediaPlayer> mpSet = new HashSet<>();

    public static void play(Context context, int resId) {
        try {
            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(context, Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + resId));
            mp.setAudioStreamType(AudioManager.STREAM_RING);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mpSet.remove(mp);
                    mp.stop();
                    mp.release();
                }
            });
            mp.prepare();
            mpSet.add(mp);
            mp.start();
        } catch (IOException exception) {
            Timber.d(exception, "Error playing audio resource");
        }
    }

    public static void play(FileInputStream inputStream) {
        try {

            int sampleRateInHz = 8000;
            int bufferSize = 500000;
            AudioTrack track = new AudioTrack(
                    AudioManager.STREAM_MUSIC,
                    sampleRateInHz,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_DEFAULT,
                    bufferSize,
                    AudioTrack.MODE_STREAM);

            track.write(inputStream);

            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(track);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mpSet.remove(mp);
                    mp.stop();
                    mp.release();
                }
            });
            mp.prepare();
            mpSet.add(mp);
            mp.start();
        } catch (IOException exception) {
            Timber.d(exception, "Error playing audio resource");
        }
    }

    public static void stop() {
        for (MediaPlayer mp : mpSet) {
            if (mp != null) {
                mp.stop();
                mp.release();
            }
        }
        mpSet.clear();
    }
}
