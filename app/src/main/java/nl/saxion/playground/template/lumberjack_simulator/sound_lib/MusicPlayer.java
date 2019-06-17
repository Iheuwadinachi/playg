package nl.saxion.playground.template.lumberjack_simulator.sound_lib;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;

import nl.saxion.playground.template.R;

/**
 * Class for the music in LumberJack simulator game
 *
 * @author Michael Cornelisse
 */
public class MusicPlayer {

    private MediaPlayer mediaPlayer;
    /**
     * Constructor for music player
     *
     * @param context context
     */
    public MusicPlayer(Context context) {
        playMusic(context);

    }

    /**
     * Starts playing the music has a listener that checks if the music stops playing,
     * it restarts the song if it does.
     *
     * @param context context
     */
    private void playMusic(Context context) {

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.piano1);

        }
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    /**
     * Pauses the music
     */
    public void pauseMusic() {

        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    /**
     * Stops music
     */
    public void stop() {
        stopMusic();
    }

    /**
     * Logic handler to stop music
     */
    private void stopMusic() {

        if (mediaPlayer != null) {
            mediaPlayer.release();
            //mediaPlayer.prepareAsync(); i think we need this but not sure how to use it properly yet.
            mediaPlayer = null;
        }
    }
    //TODO maybe implement AsyncTask
}