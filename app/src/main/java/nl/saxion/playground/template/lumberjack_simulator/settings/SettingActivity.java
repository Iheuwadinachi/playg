package nl.saxion.playground.template.lumberjack_simulator.settings;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lumberjack_simulator.Activity;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.Constants;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.DataWrapper;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.JsonHandler;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.Save;
import nl.saxion.playground.template.lumberjack_simulator.intro.IntroActivity;
import nl.saxion.playground.template.lumberjack_simulator.local_lib.GlobalApplication;
import nl.saxion.playground.template.lumberjack_simulator.sound_lib.MusicPlayer;


public class SettingActivity extends AppCompatActivity {
    private TextView settingsTextView, soundTextView, musicTextView;

    private Switch themeSwitch;
    private JsonHandler jsonHandler;

    private boolean removedFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);
        jsonHandler = new JsonHandler(this);
        jsonHandler.loadConstants();
        final DataWrapper dataWrapper = new DataWrapper();
        dataWrapper.setInstance(dataWrapper);

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeGame();
            }
        });


        Button btnQuitGame = findViewById(R.id.quitButton);
        btnQuitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitGame();
            }
        });

        Button resumeGame = findViewById(R.id.resumeButton);
        resumeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        android.support.v7.widget.SwitchCompat toggle = findViewById(R.id.soundSwitch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean soundOn) {
                if (soundOn) {

                    AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
                    amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
                    amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                    amanager.setStreamMute(AudioManager.STREAM_RING, false);
                    amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
                } else {

                    AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
                    amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
                    amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                    amanager.setStreamMute(AudioManager.STREAM_RING, true);
                    amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);

                }
            }
        });



       /* themeSwitch = findViewById(R.id.themeSwitch);
        Switch themeToggle = themeSwitch;
        themeToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean dayMode) {
              background.setDayMode(false);

            }
        });
*/
        /*musicSwitch = findViewById(R.id.musicSwitch);
        toggle = musicSwitch;
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Music Switch on!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Music Switch off!", Toast.LENGTH_LONG).show();
                }
            }
        });*/


        /* themeSwitch = findViewById(R.id.themeSwitch);*/
    }

    /*public void nightMode(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        // Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "Nightmode Activated", Toast.LENGTH_SHORT);
        myToast.show();


        btnQuitGame = findViewById(R.id.quitButton);
        btnQuitGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();


                *//*AlertDialog.Builder altquit = new AlertDialog.Builder(SettingActivity.this);
                altquit.setMessage("Do you want to Quit this Game").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }

                        });

                AlertDialog alert = altquit.create();
                alert.setTitle("");
                alert.show();*//*
            }
        });
    }*/

    public void openActivity() {
        if(removedFile) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        onBackPressed();
    }

    public void removeGame(){
        JsonHandler jsonHandler = new JsonHandler(GlobalApplication.getAppContext());
        jsonHandler.removeData();
        Toast.makeText(getApplicationContext(),"Please relaunch the game to see difference",Toast.LENGTH_LONG).show();
        removedFile = true;
    }

    public void exitGame() {
        MusicPlayer musicPlayer = new MusicPlayer(this, R.raw.piano1);
        musicPlayer.stop();
        Intent intent = new Intent(this, IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
        finish();

    }
}