package com.example.android.miwok.Activities;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwok.R;
import com.example.android.miwok.Models.Word;
import com.example.android.miwok.Adapters.WordAdapter;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    // new OnCompletionListener that releases the MediaPlayer object
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    Log.i("Colors", "AUDIOFOCUS_GAIN");
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                    Log.i("Colors", "AUDIOFOCUS_GAIN_TRAINSIENT");
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                    Log.i("Colors", "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                    mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    Log.i("Colors", "AUDIOFOCUS_LOSS");
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    Log.i("Colors", "AUDIOFOCUS_LOSS_TRANSIENT");
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    Log.e("Colors", "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                    Log.e("Colors", "AUDIOFOCUS_REQUEST_FAILED");
                    break;
                default:
                    //
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        // Create and initialize an array of English number words
        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word(R.raw.family_father, "father", "әpә", R.drawable.family_father));
        words.add(new Word(R.raw.family_mother, "mother", "әṭa", R.drawable.family_mother));
        words.add(new Word(R.raw.family_son, "son", "angsi", R.drawable.family_son));
        words.add(new Word(R.raw.family_daughter, "daughter", "tune", R.drawable.family_daughter));
        words.add(new Word(R.raw.family_older_brother, "older brother", "taachi", R.drawable.family_older_brother));
        words.add(new Word(R.raw.family_younger_brother, "younger brother", "chalitti", R.drawable.family_younger_brother));
        words.add(new Word(R.raw.family_older_sister, "older sister", "teṭe", R.drawable.family_older_sister));
        words.add(new Word(R.raw.family_younger_sister, "younger sister", "kolliti", R.drawable.family_younger_sister));
        words.add(new Word(R.raw.family_grandmother, "grandmother", "ama", R.drawable.family_grandmother));
        words.add(new Word(R.raw.family_grandfather, "grandfather", "paapa", R.drawable.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                // store the clicked word in a variable that can be used to find the sound
                Word clickedWord = (Word) adapterView.getItemAtPosition(i);

                // release the media player so that it's ready to be setup with a new sound
                releaseMediaPlayer();

                // request audio focus
                int requestAF = mAudioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // if we have audio focus...
                if (requestAF == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // setup the mediaPlayer resource with the sound for the clicked word
                    mMediaPlayer = MediaPlayer.create(getBaseContext(), clickedWord.getAudioId());

                    // start the sound
                    mMediaPlayer.start();

                    // setup a listener on the mediaPlayer so that we can release the mediaPlayer once the sound has finished
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    //release media player once the app is stopped
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}