package nl.saxion.playground.template.lumberjack_simulator.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lumberjack_simulator.Activity;
import nl.saxion.playground.template.lumberjack_simulator.Game;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.Constants;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.DataWrapper;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.JsonHelper;
import nl.saxion.playground.template.lumberjack_simulator.sound_lib.MusicPlayer;
import nl.saxion.playground.template.lumberjack_simulator.utility.GlobalApplication;


public class IntroActivity extends AppCompatActivity {

    private Button startGame;
    private JsonHelper jsonHelper;
    private Game game;

    //public static MediaPlayer mMediaPlayer;
    private static MusicPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        Typewriter writer = findViewById(R.id.typewriter);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.greenishColor));

        writer.setCharacterDelay(50);
        writer.animateText(" On a calm summer afternoon of two thousand seventy-two,\n" +
                "Degrees have reached a whole new boom.\n" +
                " \n" +
                "The lakes were dry, \n" +
                "Dust covered the moon, the end is near oh so, so, soon.\n" +
                "\n" +
                "As autumn hit a heat of forty, \n" +
                "Even Amenhotep woke in he’s tomb.\n" +
                "Gen Z has screamed a wailing cry,\n" +
                "Millennials why have you failed us, why?\n" +
                "\n" +
                "Governments of world have rose to fight the task at hand,\n" +
                "Welcome to the Disneyland. \n" +
                "\n" +
                "“Shall we ban oil, diesel - both of them?”\n" +
                "Mr. Walton grunted - “Don’t overexpand!”\n" +
                "\n" +
                "“We shall ban logging - we need the planes to fly!”\n" +
                "Mister moaned as he tipped his hat. \n" +
                " \n" +
                "“You shall listen, or everyone will starve,\n" +
                "Don’t you argue with ME, you science rat!”\n" +
                "\n" +
                "“Between I know a guy, who knows a tree that never dies.”\n" +
                "\n" +
                "Logging business has been banned! Wake up Hans!\n" +
                "Only you know the tree that never ends! \n" +
                "We have a goldmine on our hands!\n ");

        musicPlayer = new MusicPlayer(this, R.raw.keyboard_typing);
        startGame = findViewById(R.id.startGame);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicPlayer.stop();
                openActivity();
                jsonHelper = new JsonHelper(getApplicationContext());
                jsonHelper.loadConstants();
                DataWrapper dataWrapper = new DataWrapper();
                dataWrapper.setInstance(dataWrapper);
                dataWrapper.setCoins(Constants.coins + 1);
                musicPlayer = new MusicPlayer(v.getContext(), R.raw.piano1);
            }
        });


        //mMediaPlayer = new MediaPlayer();
        //mMediaPlayer = MediaPlayer.create(this, R.raw.keyboard_typing);
        //mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mMediaPlayer.start();
    }

    public void openActivity() {
        Intent intent = new Intent(this, Activity.class);
        startActivity(intent);
    }


}
