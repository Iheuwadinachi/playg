package nl.saxion.playground.template.lumberjack_simulator.sound_lib;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.ArrayList;

import nl.saxion.playground.template.R;

public class SoundEffects {
    private static SoundPool soundPool;
    private static int chopSound;
    private static int chopSoundTwo;
    private int[] chopSounds = new int[2];
    //private static int coinSound;
    //private static ArrayList<Integer> sounds = new ArrayList<>();


    public SoundEffects(Context context) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build();

        chopSounds[0] = soundPool.load(context, R.raw.chopping_woord_01, 1);
        chopSounds[1] = soundPool.load(context, R.raw.chopping_wood_02, 1);
        //chopSound = soundPool.load(context,R.raw.chopping_wood_01, 1);
        //chopSoundTwo = soundPool.load(context, R.raw.chopping_wood_02, 1);
        //initSounds(context);

    }

    public void playChopSound() {
        int randomIndex = (int) (Math.random() * 2 + 1);
        if (randomIndex == 1) {
            soundPool.play(chopSound, 1.0f, 1.0f, 1, 0, 1.0f);
        } else if (randomIndex == 2) {
            soundPool.play(chopSoundTwo, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }
}
