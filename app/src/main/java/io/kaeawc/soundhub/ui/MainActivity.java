package io.kaeawc.soundhub.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;

import io.kaeawc.soundhub.R;
import io.kaeawc.soundhub.audio.Player;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private Button mPlayButton;
    private Button mStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayButton = (Button) findViewById(R.id.play_stream);
        mPlayButton.setOnClickListener(onPlayClicked);

        mStopButton = (Button) findViewById(R.id.stop_stream);
        mStopButton.setOnClickListener(onStopClicked);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onPlayClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Timber.d("Play Audio");
            FileInputStream inputStream = new FileInputStream(yourByteArrayAsMp3File);
            mediaPlayer.setDataSource(rawmp3file.getFD());

            Player.play(inputStream);
        }
    };

    private View.OnClickListener onStopClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Timber.d("Stop Audio");
            Player.stop();
        }
    };
}
